package JaVale.Enums;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class LocationTypesTest {

    /**Teste da construção e inicialização de valores do enumerable */
    @Test
    public void testLocationTypesValues() {
        assertEquals(1, LocationTypes.fornecedor.getValue(), "fornecedor deve ter o valor de 1");
        assertEquals(2, LocationTypes.beneficiamento.getValue(), "beneficiamento deve ter o valor de 2");
        assertEquals(3, LocationTypes.pelotizacao.getValue(), "pelotizacao deve ter o valor de 3");
        assertEquals(4, LocationTypes.briquete.getValue(), "briquete deve ter o valor de 4");
        assertEquals(5, LocationTypes.porto.getValue(), "porto deve ter o valor de 5");
        assertEquals(6, LocationTypes.cliente.getValue(), "cliente deve ter o valor de 6");
        assertEquals(7, LocationTypes.patio.getValue(), "cliente deve ter o valor de 7");
        assertEquals(8, LocationTypes.super_fonte.getValue(), "super_fonte deve ter o valor de 8");
        assertEquals(9, LocationTypes.super_sorvedouro.getValue(), "super_sorvedouro deve ter o valor de 9");
    }
}
