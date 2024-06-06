package JaVale.Model;

import java.util.ArrayList;

import JaVale.Utils.Converter;

/**<b>Representa a malha logística (grafo) da Vale</b><p></p>
 * Propriedades:
 * <ul>
 * <li>verticesAmount : int = quantidade de vértices do grafo
 * <li>adjacencyList : ArrayList<Route>[] = lista de adjacência
 * </ul>
 * 
 * Métodos públicos:
 * <ul>
 * <li>addRoute(Route) : void
 * </ul>
 */
public class LogisticNetwork {
    private final int verticesAmount;
    private ArrayList<Route>[] adjacencyList;

    /**
     * @param verticesAmount : int = número de vértices ou nós do grafo
     */
    public LogisticNetwork(int verticesAmount) {
        this.verticesAmount = verticesAmount;
        this.adjacencyList = (ArrayList<Route>[]) new ArrayList[verticesAmount];

        for (int i = 0; i < verticesAmount; i++) {
            this.adjacencyList[i] = new ArrayList<Route>();
        }
    }

    /**
     * @param V : índice do vértice ao qual se deseja retornar os nós adjacentes : int
     * @return adjV : lista contando os nós adjacentes em relação ao nó de índice V : ArrayList<Integer>
     */
    public ArrayList<Integer> adjV(int V) {
        ArrayList<Integer> adjV = new ArrayList<>();
        for (Route adj : adjacencyList[V]) {
            adjV.add(adj.getTarget().getIndex());
        }
        return adjV;
    }

    /**
     * Adiciona uma rota ao grafo.
     * 
     * @param route : Route = rota a ser adicionada
     */
    public void addRoute(Route route) {
        Location source = route.getSource();
        Location target = route.getTarget();

        Location sourceResidual = new Location(target.getType(), target.getIndex(), target.getId(), target.getStorageMax(), target.getDescription());
        Location targetResidual = new Location(source.getType(), source.getIndex(), source.getId(), source.getStorageMax(), source.getDescription());
        route.setResidual(new Route(targetResidual, sourceResidual, route.getSubmodal(), Converter.fromDaysToHours(route.getTravelTime())));
        
        this.adjacencyList[source.getIndex()].add(route);
    }

    /**
     * @return quantidade de vértices : int
     */
    public int getVerticesAmount() {
        return this.verticesAmount;
    }


    /**
     * @return lista de adjacência : ArrayList<Route>[]
     */
    public ArrayList<Route>[] getAdjacencyList() {
        return this.adjacencyList;
    }


    /**
     * Sobrescrição do método de conversão para String para testes e depuração.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.verticesAmount; i++) {
            
            ArrayList<Route> list = this.adjacencyList[i];
            sb.append("Vertex of index ");
            sb.append(i + " ");
            try {sb.append("(id: " + adjacencyList[i].get(0).getSource().getId() + ")");}
            catch(Exception e) {
                sb.append("NDA");
            }
                
            sb.append("Graph index ");
            sb.append(" is connected to vertex of index ");
            for (int j = 0; j < list.size(); j++) {
                sb.append(list.get(j).getTarget().getIndex());
                sb.append(" (id: " + list.get(j).getTarget().getId() + ")");
                sb.append(", ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}