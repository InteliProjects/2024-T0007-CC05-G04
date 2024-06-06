package JaVale.Model;

import JaVale.Enums.LocationTypes;;

/**<b>Representa localidade (nó) na malha logística da Vale</b>
 * Propriedades:
 * <ul>
 * <li>type : LocationTypes = tipo do nó
 * <li>index : int = índice na lista de adjacência
 * <li>id : int = identificador único do nó
 * <li>storageMax : double = capacidade máxima de armazenamento do nó
 * <li>description : String = descrição do nó
 * <li>isDestination : boolean = identifica se o vértice é destino de alguma rota
 * </ul>
 * 
 * Métodos públicos:<p></p>
 * <ul>
 * <li><i>Getters</i> e <i>setters</i>
 * <li><i>assignIndex</i> : atribui um índices ao vértice
 * <li><i>resetIndex</i> : reinicia a contagem de índices
 * </ul>
 */
public class Location {
    private LocationTypes type;
    private int index;
    private int id;
    private boolean isDestination;
    private double storageMax;
    private String description;

    // O primeiro índice (considerando que o índice 0 pertence à superfonte)
    private static int i = 1;

    /**
     * Usa-se este método sempre que for necessário recriar um grafo, a fim de reiniciar os índices dos vértices.
     */
    public static void resetIndex() {
        i = 1;
    }


    /**
     * @param type : LocationTypes = tipo do nó
     * @param index : int = índice na lista de adjacência
     * @param id : int = identificador único do nó
     * @param isDestination : boolean = identifica se o nó é destino de alguma rota
     * @param storageMax : double = capacidades máxima de armazenamento do nó
     * @param description : String = descrição do nó
     * 
     */
    public Location(LocationTypes type, int index, int id, double storageMax, String description) {
        this.type = type;
        this.index = index;
        this.id = id;
        this.storageMax = storageMax;
        this.description = description;
    }

    /**
     * Construtor de objeto Location nulo.
     */
    public Location() {
        this.type = null;
        this.index = 0;
        this.id = 0;
        this.storageMax = 0;
        this.description = "";
        this.isDestination = false;
    }


    /**
     * Atribui um índice da lista de adjacência do grafo ao objeto
     */
    public void assignIndex() {
        this.index = i;
        i++;
    }


    /**
     * @return id do nó : int
    */
    public int getId() {
        return this.id;
    }


    /**
     * @return é destino : boolean
    */
    public boolean getIsDestination() {
        return this.isDestination;
    }


    /**
     * @param isDestination : boolean
    */
    public void setIsDestination(boolean isDestination) {
        this.isDestination = isDestination;
    }


    /**
     * @param id : int = novo identificador do nó.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return tipo do nó : LocationTypes
    */
    public LocationTypes getType() {
        return this.type;
    }


    /**
     * @param type : LocationTypes = novo tipo do nó.
     */
    public void setType(LocationTypes type) {
        this.type = type;
    }


    /**
     * @return índice na lista de adjacência : int
     */
    public int getIndex() {
        return this.index;
    }


    /**
     * @param index : int = novo índice na lista de adjacência.<p></p>
     * Substitui o valor apenas se index > 0.
     */
    public void setIndex(int index) {
        if (index > 0) {
            this.index = index;
        }
    }


    /**
     * @return capacidade máxima de armazenamento do nó : double
     */
    public double getStorageMax() {
        return this.storageMax;
    }


    /**
     * @param storageMax : double = nova capacidade máxima de armazenamento do nó.
     */
    public void setStorageMax(double storageMax) {
        this.storageMax = storageMax;
    }


     /**
     * @return descrição do nó : String
     */
    public String getDescription() {
        return this.description;
    }

     /**
     * @param description : String = nova descrição do nó.<p></p>  
     * @return descrição do nó : String
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Compara este objeto Location com outro objeto (serão iguais se tiverem ids iguais)
     * @param other : Object = objeto a ser comparado
     * @return resultado da comparação : boolean
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (other == null) {
            return false;
        }

        if (other.getClass() != this.getClass()) {
            return false;
        }

        return this.id == ((Location) other).getId();
    }

    /**
     * Retorna o hashcode deste objeto Location, que é o seu identificador único.
     * @return O hashcode deste objeto Location.
     */
    @Override
    public int hashCode() {
        return this.id;
    }
}
