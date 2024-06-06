package JaVale.DTOs;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**<b>Transfere dados da malha logística da Vale entre as diferentes camadas da aplicação</b><p></p>
 * Propriedades:
 * <ul>
 * <li>verticesAmount : int = quantidade de vértices do grafo
 * <li>maxFlow : double = fluxo máximo calculado para esse grafo
 * <li>executionTime : double = tempo de execução do algoritmo de cálculo do fluxo máximo
 * <li>locations : List<Location> = lista de vértices
 * <li>routes : List<Route> = lista de rotas
 * </ul>
 * 
 * Métodos públicos:
 * <ul>
 * <li><i>Getters</i> e <i>setters</i>
 * </ul>
 */
@Getter
@Setter
public class LogisticNetworkDTO {
    private int verticesAmount;
    private double maxFlow;
    private double executionTime;
    private List<LocationDTO> locations;
    private List<RouteDTO> routes;
}
