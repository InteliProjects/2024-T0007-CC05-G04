package JaVale.Repository;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Test;
import JaVale.Enums.SubmodalTypes;
import JaVale.Model.Location;
import JaVale.Model.Route;

public class JaValeRepositoryTest {
    /**Teste de getter e setter de origem */
    @Test
    public void testOriginGetterSetter() {
        Route route = new Route(null, null, null, 0);
        Location origin = new Location(); 
        origin.setDescription("Origem Teste");
        route.setSource(origin);
        assertEquals(origin, route.getSource(), "O getter ou setter de origin não está funcionando corretamente");
    }

    /**Teste de getter e setter de destino */
    @Test
    public void testDestinationGetterSetter() {
        Route route = new Route(null, null, null, 0);
        Location destination = new Location(); 
        destination.setDescription("Destino Teste");
        route.setTarget(destination);
        assertEquals(destination, route.getTarget(), "O getter ou setter de destination não está funcionando corretamente");
    }

    /**Teste de getter e setter de submodal */
    @Test
    public void testSubmodalGetterSetter() {
        Route route = new Route(null, null, null, 0);
        route.setSubmodal(SubmodalTypes.GDE); 
        assertEquals(SubmodalTypes.GDE, route.getSubmodal(), "O getter ou setter de submodal não está funcionando corretamente");
    }

}
