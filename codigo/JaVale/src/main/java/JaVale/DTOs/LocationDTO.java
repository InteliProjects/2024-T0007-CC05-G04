package JaVale.DTOs;
import JaVale.Enums.LocationTypes;
import lombok.Getter;
import lombok.Setter;


/**<b>Transfere os dados de uma localidade entre diferentes camadas da aplicação</b><p></p>
 * Propriedades:
 * <ul>
 * <li>type : LocationTypes = tipo da localidade
 * <li>index : int = índice na matriz de adjacência e identificador único
 * <li>storageMax : double = capacidade máxima de armazenamento do nó
 * <li>description : String = descrição da localidade
 * </ul>
 * 
 * Métodos públicos:
 * <ul>
 * <li><i>Getters</i> e <i>setters</i>
 * </ul>
 */
@Getter
@Setter
public class LocationDTO {
    private LocationTypes type;
    private int id;
    private int i;
    private double storageMax;
    private String description;

    /**
     * Verifica se um objeto é igual a este LocationDTO.
     * 
     * @param other : Object = O objeto a ser comparado com este LocationDTO.
     * @return true se o objeto for igual a este LocationDTO, false caso contrário.
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

        return this.i == ((LocationDTO) other).i;
    }

    /**
     * Retorna o hashcode deste LocationDTO, que é o seu identificador único.
     * 
     * @return O hashcode deste LocationDTO.
     */
    @Override
    public int hashCode() {
        return this.id;
    }
}
