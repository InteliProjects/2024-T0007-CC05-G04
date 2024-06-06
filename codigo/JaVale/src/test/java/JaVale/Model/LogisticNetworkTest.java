package JaVale.Model;
import JaVale.Enums.LocationTypes;
import JaVale.Enums.SubmodalTypes;

import org.junit.Before;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LogisticNetworkTest {
    private LogisticNetwork graph;
    private final int initialVerticesAmount = 3;
    private final int initialTravelTime = 12;
    private final Location initialOrigin = new Location(LocationTypes.porto, 0, 0, 100D, "Porto de Tubarão");
    private final Location initialDestination = new Location(LocationTypes.porto, 1, 1, 100D, "Porto de Praia Mole");
    private final Location initialFinalDestination = new Location(LocationTypes.porto, 2, 2, 100D, "Porto de Vitória");
    private Route route1 = new Route(initialOrigin, initialDestination, SubmodalTypes.BAR, initialTravelTime);
    private Route route2 = new Route(initialDestination, initialFinalDestination, SubmodalTypes.DIV, initialTravelTime);

    /**Configuração inicial do teste. Inicialização de objeto do tipo LogisticNetwork a ser usado nos testes*/
    @Before
    public void setUp() {
        graph = new LogisticNetwork(initialVerticesAmount);
    }

    /**Teste de método de adicionar rota (aresta) à malha logística (grafo) */
    @Test
    public void testAddRoute() {
        graph.addRoute(route1);
        graph.addRoute(route2);
        String result = "Vertex of index 0 is connected to vertex of index 1 with maxFlow equal to 40.0\nVertex of index 1 is connected to vertex of index 2 with maxFlow equal to 40.0\n";
        assertEquals(result, graph.toString());
    }

    /**Teste de método de obter quantidade de vértices da malha logística (grafo) */
    @Test
    public void testGetVerticesAmount() {
        assertEquals(initialVerticesAmount, graph.getVerticesAmount());
    }
}
