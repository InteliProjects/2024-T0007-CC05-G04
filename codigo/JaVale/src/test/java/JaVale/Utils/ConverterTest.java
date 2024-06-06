package JaVale.Utils;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConverterTest {
    private final double batchInTons = 1000.0;

    /**Teste da conversão de lotes para toneladas com 60 lotes */
    @Test
    public void testFromBatchesToTons() {
        assertEquals(batchInTons * 60, Converter.fromBatchesToTons(60));
    }

    /**Teste da conversão de toneladas para lotes com 60000 toneladas */
    @Test
    public void testFromTonsToBatches() {
        assertEquals(60, Converter.fromTonsToBatches(batchInTons * 60));
    }

    /**Teste da conversão de horas para dias com 12 horas */
    @Test
    public void testFromHoursToDays() {
        assertEquals(0.5, Converter.fromHoursToDays(12));
    }
}
