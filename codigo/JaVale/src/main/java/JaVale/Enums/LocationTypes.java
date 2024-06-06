package JaVale.Enums;

/** <b>Tipos de submodal</b><p></p>
 * 
 * Valores enumerados:
 * <ul>
 * <li>fornecedor {value : 1} = nó fornecedor
 * <li>beneficiamento {value : 2} = nó usina de beneficiamento
 * <li>pelotizacao {value : 3} = nó usina de pelotização
 * <li>briquete {value : 4} = nó usina de briquete
 * <li>porto {value : 5} = nó porto
 * <li>cliente {value : 6} = nó cliente
 * <li>patio {value : 7} = nó cliente
 * <li>super_fonte {value : 8} = nó superfonte
 * <li>super_sorvedouro {value : 9} = nó super-sorvedouro
 * </ul>
 * 
 * Métodos públicos:<p></p>
 * <ul>
 * <li>getValue : int
 * </ul>
*/
public enum LocationTypes {
    fornecedor (1),
    beneficiamento (2),
    pelotizacao (3),
    briquete (4),
    porto (5),
    cliente (6),
    patio (7),
    mina (8),
    super_fonte (9),
    super_sorvedouro (10);

    private final int value;

    /**
     * @param value : valor do tipo da localização desejada : int
     * atualiza o tipo de localização atual de acordo com o <i>value</i>
     */
    private LocationTypes(int value) {
        this.value = value;
    }


    /**
     * @return valor : int
     */
    public int getValue() {
        return this.value;
    }
}