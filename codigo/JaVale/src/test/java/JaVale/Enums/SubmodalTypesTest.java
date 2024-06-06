package JaVale.Enums;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class SubmodalTypesTest {
    
    /**Teste da construção e inicialização de valores do enumerable */
    @Test
    public void testSubmodalTypesValues() {
        assertEquals(1, SubmodalTypes.BAR.getValue(), "BAR deve ter o valor de 1");
        assertEquals(2, SubmodalTypes.CAM.getValue(), "CAM deve ter o valor de 2");
        assertEquals(3, SubmodalTypes.DIV.getValue(), "DIV deve ter o valor de 3");
        assertEquals(4, SubmodalTypes.GDE.getValue(), "GDE deve ter o valor de 4");
        assertEquals(5, SubmodalTypes.GDT.getValue(), "GDT deve ter o valor de 5");
        assertEquals(6, SubmodalTypes.GFD.getValue(), "GFD deve ter o valor de 6");
        assertEquals(7, SubmodalTypes.GFE.getValue(), "GFE deve ter o valor de 7");
        assertEquals(8, SubmodalTypes.HAD.getValue(), "HAD deve ter o valor de 8");
        assertEquals(9, SubmodalTypes.MDO.getValue(), "MDo deve ter o valor de 9");
        assertEquals(10, SubmodalTypes.NAV.getValue(), "NAV deve ter o valor de 10");
        assertEquals(11, SubmodalTypes.TCC.getValue(), "TCC deve ter o valor de 11");
        assertEquals(12, SubmodalTypes.TCL.getValue(), "TCL deve ter o valor de 12");
        assertEquals(13, SubmodalTypes.MIS.getValue(), "MIS deve ter o valor de 13");
        assertEquals(14, SubmodalTypes.SUPER.getValue(), "SUPER deve ter o valor de 14");
    }
}
