package JaVale.DTOs;

import lombok.Getter;
import lombok.Setter;
import JaVale.Enums.SubmodalTypes;

/**<b>Transfere os dados de uma rota entre diferentes camadas da aplicação</b><p></p>
 * Propriedades:
 * <ul>
 * <li>source : Location = nó de origem
 * <li>target : Location = nó de destino
 * <li>flow : double = fluxo na aresta
 * <li>submodal : SubmodalTypes = tipo de submodal
 * <li>travelTime : double = tempo de travessia da aresta, em dias
 * </ul>
 * 
 * Métodos públicos:
 * <ul>
 * <li><i>Getters</i> e <i>setters</i>
 * </ul>
 */
@Getter
@Setter
public class RouteDTO {
    private int source;
    private int target;
    private double flow;
    private SubmodalTypes submodal;
    private double travelTime;
}

