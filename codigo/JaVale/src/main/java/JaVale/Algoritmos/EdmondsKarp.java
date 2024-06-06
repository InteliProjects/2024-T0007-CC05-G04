package JaVale.Algoritmos;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import JaVale.Model.Route;
import JaVale.Repository.JaValeRepository;
import JaVale.Model.LogisticNetwork;

/**
 * Classe EdmondsKarp implementa o algoritmo de Edmonds-Karp para encontrar o fluxo máximo em um grafo.
 * O algoritmo de Edmonds-Karp é uma implementação específica do algoritmo de Ford-Fulkerson.<p></p>
 * Propriedades:
 * <ul>
 * <li>graph : LogisticNetwork  = representação da malha logística da Vale
 * <li>verticesAmount : int     = número de vértices do grafo
 * <li>maxFlow : double         = valor do fluxo máximo
 * <li>executionTime : double   = tempo de execução do algoritmo
 * <li>maxFlow : double         = valor do fluxo máximo
 * </ul>
 *
 * Métodos públicos:<p></p>
 * <ul>
 * <li><i>solve</i> :  que inicia a execução do algoritmo
 * <li><i>getMaxFlow</i> : retorna o fluxo máximo obtido</li>
 * <li><i>getExecutionTime</i> : retorna o tempo de execução do algoritmo</li>
 * </ul>
 *
 */

public class EdmondsKarp {
    private LogisticNetwork graph;
    private int verticesAmount;
    private int[] parent;
    private double maxFlow;
    private long executionTime;


     /**
     * Construtor para a classe EdmondsKarp.
     * Inicializa as estruturas de dados usadas pelo algoritmo.
     *
     * @param vertices : int =  O número de vértices no grafo
     */
    public EdmondsKarp(LogisticNetwork graph) {
        this.graph = graph;
        this.verticesAmount = graph.getVerticesAmount();
        this.parent = new int[verticesAmount];
        this.executionTime = 0;
    }


    /**
     * Realiza uma busca em largura (BFS) no grafo a partir de um vértice fonte.
     * Usado para encontrar um caminho aumentante no grafo residual.
     *
     * @param source : int = O vértice fonte para a busca
     * @param sink : int = O vértice sumidouro para a busca
     * @return true se um caminho aumentante foi encontrado, false caso contrário : boolean
     */
    private boolean bfs(int source, int sink) {
        boolean[] visited = new boolean[verticesAmount];
        Arrays.fill(visited, false);
        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        visited[source] = true;
        parent[source] = -1;

        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (Route route : graph.getAdjacencyList()[u]) {
                int v = route.getTarget().getIndex();
                if (!visited[v] && route.remainingCapacity() > 0) {
                    parent[v] = u;
                    visited[v] = true;
                    queue.add(v);
                }
            }
        }
        return visited[sink];
    }


     /**
     * Implementa o algoritmo de Edmonds-Karp para encontrar o fluxo máximo entre um vértice fonte e um vértice sumidouro.
     *
     * @param source : int =  O vértice fonte
     * @param sink : int =  O vértice sumidouro
     * @return O valor do fluxo máximo : int
     */
    public double edmondsKarp(int source, int sink) {
        long startTime = System.nanoTime(); // Marca o início da execução
    
        double maxFlow = 0;
        while (bfs(source, sink)) {
            double pathFlow = Double.MAX_VALUE;
            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                for (Route route : graph.getAdjacencyList()[u]) {
                    if (route.getTarget().getIndex() == v && route.remainingCapacity() > 0) {
                        pathFlow = Math.min(pathFlow, route.remainingCapacity());
                        break;
                    }
                }
            }
    
            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                for (Route route : graph.getAdjacencyList()[u]) {
                    if (route.getTarget().getIndex() == v && route.remainingCapacity() > 0) {
                        route.augment(pathFlow);
                        break;
                    }
                }
            }
            maxFlow += pathFlow;
        }

        // Calcula a duração do tempo
        this.executionTime = System.nanoTime() - startTime; 
    
        return maxFlow;
    }
    

    /**
     * @return maxFlow: retorna o fluxo máximo encontrado pelo algoritmo de Edmonds-Karp
     */
    public double getMaxFlow() {
        return maxFlow;
    }

    /**
     * @return executionTime: retorna o tempo de execução do algoritmo de Edmonds-Karp
     */
    public double getExecutionTime() {
        return this.executionTime / 1_000_000.0;
    }

    /**
        * Método principal para executar o algoritmo de Edmonds-Karp.
        * Este método cria várias instâncias de localizações que representam origens e destinos.
        * Cada localização é identificada por um tipo de localização, um índice, uma capacidade e uma string vazia.
        * O tipo de localização e o índice são usados para identificar a localização, enquanto a capacidade é usada no algoritmo de Edmonds-Karp.
        * A string vazia pode ser usada para armazenar informações adicionais sobre a localização, se necessário.
        *
        * @param args : String[] = Argumentos da linha de comando. Não utilizado neste método.
    */
    public static void main(String[] args) {
        //Instância do grafo para a rodagem de testes
        JaValeRepository repository = new JaValeRepository();
        LogisticNetwork graph = repository.getLogisticNetwork();

        int source = 0;
        int sink = graph.getVerticesAmount() - 1;

         // Inicializa EdmondsKarp com o número de vértices apropriado
         EdmondsKarp ek = new EdmondsKarp(graph);
    
        System.out.println("Max Flow: " + ek.edmondsKarp(source, sink));
    }
}

