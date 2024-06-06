package JaVale.Model;
import JaVale.Enums.LocationTypes;
import JaVale.Enums.SubmodalTypes;

import org.junit.Before;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RouteTest {
    private Route route;
    private final Location initialOrigin = new Location(LocationTypes.porto, 0, 0, 100D, "Porto de Tubarão");
    private final Location initialDestination = new Location(LocationTypes.porto, 1, 1, 100D, "Porto de Praia Mole");
    private final SubmodalTypes initialSubmodal = SubmodalTypes.GDE;
    private final int initialTravelTime = 12;

    /**Configuração inicial do teste. Inicialização de objeto do tipo Route a ser usado nos testes*/
    @Before
    public void setUp() {
        route = new Route(initialOrigin, initialDestination, initialSubmodal, initialTravelTime);
    }

    /**Teste de getter da propriedade origin */
    @Test
    public void testGetOrigin() {
        assertEquals(initialOrigin, route.getSource());
    }

    /**Teste de getter da propriedade destination */
    @Test
    public void testGetDestination() {
        assertEquals(initialDestination, route.getTarget());
    }

    /**Teste de getter da propriedade submodal */
    @Test
    public void testGetSubmodal() {
        assertEquals(initialSubmodal, route.getSubmodal());
    }

    /**Teste de getter da propriedade travelTime */
    @Test
    public void testGetTravelTime() {
        assertEquals(initialTravelTime / 24D, route.getTravelTime());
    }

    /**Teste de setter da propriedade origin */
    @Test
    public void testSetType() {
        Location newOrigin = new Location(LocationTypes.briquete, 2, 50, 50D, "BR01");
        route.setSource(newOrigin);
        assertEquals(newOrigin, route.getSource());
    }

    /**Teste de setter da propriedade destination */
    @Test
    public void testSetDestination() {
        Location newDestination = new Location(LocationTypes.cliente, 3, 0, 100D, "Porto Europa");
        route.setTarget(newDestination);
        assertEquals(newDestination, route.getTarget());
    }

    /**Teste de setter da propriedade submodal */
    @Test
    public void testSetSubmodal() {
        route.setSubmodal(SubmodalTypes.GDT);
        assertEquals(SubmodalTypes.GDT, route.getSubmodal());
    }
}
