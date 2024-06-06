package JaVale.Model;

import JaVale.Enums.SubmodalTypes;
import JaVale.Utils.Converter;

/**<b>Representa uma rota de transporte de minério (aresta) na malha logística da Vale</b>
 * Propriedades:
 * <ul>
 * <li>source : Location = nó de origem
 * <li>target : Location = nó de destino
 * <li>submodal : SubmodalTypes = tipo de submodal
 * <li>travelTime : double = tempo de travessia da rota, em dias
 * <li>maxFlow : double = capacidade máxima de fluxo suportada por essa aresta
 * <li>flow : double = fluxo atual que passa pela aresta
 * <li>residual : Route = rota do grafo residual
 * </ul>
 * 
 * Métodos públicos:<p></p>
 * <ul>
 * <li><i>Getters</i> e <i>setters</i>
 * <li><i>augment</i> : incrementa o fluxo atual da aresta
 * <li><i>remainingCapacity</i> : retorna a capacidade residual da rota
 * </ul>
 */
public class Route {
    private Location source;
    private Location target;
    private SubmodalTypes submodal;
    private double travelTime;
    private double maxFlow;
    private Route residual;
    private double flow;


    /**
     * Construtor da classe.
     * @param source : Location = nó de origem
     * @param target : Location = nó de destino
     * @param submodal : SubmodalTypes = tipo do submodal
     * @param travelTime : int = horas de tempo de travessia
     */
    public Route(Location source, Location target, SubmodalTypes submodal, int travelTime) {
        this.source = source;
        this.target = target;
        this.submodal = submodal;
        this.travelTime = Converter.fromHoursToDays(travelTime);
    }

    /**
     * Construtor PROVISÓRIO da classe. (ainda não temos o parâmetro travelTime)
     * @param source : Location = nó de origem
     * @param target : Location = nó de destino
     * @param submodal : SubmodalTypes = tipo do submodal
     * @param maxFlow : double = capacidade máxima de fluxo da aresta
     */
    public Route(Location source, Location target, SubmodalTypes submodal, double maxFlow) {
        this.source = source;
        this.target = target;
        this.submodal = submodal;
        this.maxFlow = maxFlow;
        this.travelTime = 0;
        this.flow = 0;
    }

    /**
     * @return nó de origem : Location 
     */
    public Location getSource() {
        return this.source;
    }

    /**
      * @param : define nó de origem : Location
      */
    public void setSource(Location source) {
        this.source = source;
    }
     /**
      * @return nó de destino : Location 
      */
    public Location getTarget() {
        return this.target;
    }

    /**
      * @param : define nó de destino : Location
      */
    public void setTarget(Location target) {
        this.target = target;
    }


    /**
      * @return fluxo na aresta : double 
      */
      public double getFlow() {
        return this.flow;
    }


    /**
      * @return tipo do submodal : SubmodalTypes 
      */
      public SubmodalTypes getSubmodal() {
        return this.submodal;
      }

    /**
      * @param : define submodal : SubmodalTypes
      */
    public void setSubmodal(SubmodalTypes submodal) {
        this.submodal = submodal;
    }

     /**
     * @return tempo de travessia da aresta em dias : double
     */
    public double getTravelTime() {
        return this.travelTime;
    }

    /**
     * @return rota residual da aresta em questão : Route
     */
    public Route getResidual() {
        return this.residual;
    }

    /**
     * @param : Route = rota residual nova
     * atualiza a rota residual com o objeto r
     */
    public void setResidual(Route r) {
        this.residual = r;
    }

    /**
     * @param : fluxo a ser incrementado : double
     */
    public void augment(double flow) {
        this.flow += flow;
        this.residual.flow -= flow;
    }
    /**
     * @return capacidade residual da rota : double
     */
    public double remainingCapacity() {
        return maxFlow - flow;
    }
}
