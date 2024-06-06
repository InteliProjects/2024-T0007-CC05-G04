package JaVale.Enums;

/** <b>Tipos de submodal</b><p></p>
 * 
 * Valores enumerados:
 * <ul>
 * <li>RAILROAD {value : 1, maxCargo : 84} = submodal ferroviário
 * <li>ROAD {value : 2, maxCargo : 50} = submodal rodoviário
 * <li>CONVEYOR_BELT {value : 3, maxCargo : 20} = submodal correia transportadora
 * <li>NAVAL {value : 4, maxCargo : 70} = submodal naval
 * <li>MINERODUCT {value : 5, maxCargo : 20} = submodal mineroduto
 * </ul>
 * 
 * Métodos públicos:<p></p>
 * <ul>
 * <li>getValue : int
 * <li>getMaxCargo : int
 * </ul>
*/
public enum SubmodalTypes {
    BAR (1),
	CAM (2),
	DIV (3),
    GDE (4),
	GTU (5),
	GDT (6),
    GFD (7),
    GFE (8),
    HAD (9),
    MDO (10),
    NAV (11),
    TCC (12), 
    TCL (13),
    MIS (14),
    SUPER (15);


    private final int value;

    /**
     * @param value : valor do tipo da localização desejada : int
     * atualiza o tipo de localização atual de acordo com o <i>value</i>
     */
    private SubmodalTypes(int value) {
        this.value = value;
    }
    
    /**
     * @return valor : int
     */
    public int getValue() {
        return this.value;
    }

}