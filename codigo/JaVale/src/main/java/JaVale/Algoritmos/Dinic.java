package JaVale.Algoritmos;
import JaVale.Model.Route;
import JaVale.Model.LogisticNetwork;
import JaVale.Repository.JaValeRepository;

import java.util.Arrays;
import java.util.Deque;
import java.util.ArrayDeque;


//Código baseado na implementação de William Fiset para o algoritmo de dinic, com adaptações feitas para se adequar aos dados da Vale
/**<b>Calcula o fluxo máximo de um grafo por meio do algoritmo de Dinic. Código baseado na implementação de William Fiset para o algoritmo de dinic, com adaptações feitas para se adequar aos dados da Vale</b><p></p>
 * Propriedades:
 * <ul>
 * <li>level : int[] = níveis de cada nó, em relação à fonte
 * <li>s : int = índice da fonte
 * <li>t : int  = índice do sorvedouro
 * <li>graph : LogisticNetwork = representação da malha logística da Vale
 * <li>N : int = número de vértices do grafo
 * <li>maxFlow : double = valor do fluxo máximo
 * </ul>
 *
 * Métodos públicos:<p></p>
 * <ul>
 * <li><i>solve</i> :  que inicia a execução do algoritmo
 * <li><i>getMaxFlow</i> : retorna o fluxo máximo obtido</li>
 * </ul>
 *
 */
public class Dinic {
    private int[] level;
    private int s;
    private int t;
    LogisticNetwork graph;
    private int N;
    private double maxFlow;
    private long executionTime;

    /**<b> Construtor da classe </b>
     * @param graph : LogisticNetwork = grafo da malha logística da Vale
     * @param s : int = índice do nó da fonte
     * @param t : int = índice do nó do sorvedouro
     */
    public Dinic(LogisticNetwork graph, int s, int t) {
        this.graph = graph;
        this.s = s;
        this.t = t;
        N = graph.getVerticesAmount();
        this.level = new int[graph.getVerticesAmount()];
        this.executionTime = 0;
    }

    /**
     * Executa o algoritmo, definindo o valor do fluxo máximo para o grafo
     */
    public void solve() {
        long startTime = System.nanoTime();

        int[] next = new int[graph.getVerticesAmount()];
        long INF = Long.MAX_VALUE / 2;
        while (bfs()) {
            Arrays.fill(next, 0);
            for (double f = dfs(s, next, INF); f != 0; f = dfs(s, next, INF)) {
                maxFlow += f;
            }
        }

        this.executionTime = System.nanoTime() - startTime;
    }


    /**
     * @return boolean: true se há um caminho entre o sorvedouro e a fonte, false caso contrário
     *
     */
    private boolean bfs() {
        Arrays.fill(this.level, -1);
        this.level[s] = 0;
        Deque<Integer> q = new ArrayDeque<>(N);
        q.offer(s);
        while (!q.isEmpty()) {
            int node = q.poll();
            for (Route route : graph.getAdjacencyList()[node]) {
                double cap = route.remainingCapacity();
                if (cap > 0 && this.level[route.getTarget().getIndex()] == -1) {
                    this.level[route.getTarget().getIndex()] = this.level[node] + 1;
                    q.offer(route.getTarget().getIndex());
                }
            }
        }
        return this.level[t] != -1;
    }

    /**
     * Executa a busca por profundidade para que possa incrementar o fluxo máximo
     * @return flow: retorna o valor do fluxo a ser incrementado ao chegar ao sorvedouro <p></p>
     * bottleNeck: retorna o valor do gargalo da rota encontrada, para se calcular o fluxo máximo <p></p>
     * 0: caso as arestas do caminho já estejam saturadas
     */
    private double dfs(int at, int[] next, double flow) {
        if (at == t) return flow;
        int numRoutes = graph.getAdjacencyList()[at].size();
        

        for (; next[at] < numRoutes; next[at]++) {
            Route route = graph.getAdjacencyList()[at].get(next[at]);
            double cap = route.remainingCapacity();
            if (cap > 0 && this.level[route.getTarget().getIndex()] == this.level[at] + 1) {
                double bottleNeck = dfs(route.getTarget().getIndex(), next, Math.min(flow, cap));
                if (bottleNeck > 0) {
                    route.augment(bottleNeck);
                    return bottleNeck;
                }
            }
        }
        return 0;
    }

    /**
     * @return maxFlow: retorna o fluxo máximo encontrado pelo método <i>solve</i>
     */
    public double getMaxFlow() {
        return maxFlow;
    }


    /**
     * @return executionTime: retorna o tempo de execução do algoritmo, em milissegundos
     */
    public double getExecutionTime() {
        return this.executionTime / 1_000_000.0;
    }


    public static void main(String[] args) {
        //Instância do grafo para a rodagem de testes
        JaValeRepository repository = new JaValeRepository();
        LogisticNetwork network = repository.getLogisticNetwork();

        int source = 0;
        int sink = network.getVerticesAmount() - 1;

        Dinic dinic = new Dinic(network, source, sink);
        dinic.solve();
        System.out.println("Fluxo máximo: " + dinic.getMaxFlow());
    }
}
