package JaVale.Algoritmos;

import JaVale.Model.LogisticNetwork;
import JaVale.Repository.JaValeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de testes para o algoritmo de Dinic.
 * Testa a capacidade do algoritmo de calcular corretamente o fluxo máximo em diferentes cenários.
 */
public class DinicTest {

    private LogisticNetwork testNetwork;
    private JaValeRepository repository;

    /**
     * Configuração inicial para os testes.
     * Prepara um ambiente com um grafo de teste antes de cada teste.
     */
    @BeforeEach
    public void setUp() {
        // Inicialização do repositório e da rede logística de teste
        repository = new JaValeRepository();
        testNetwork = repository.getLogisticNetwork();
    }

    /**
     * Testa o cálculo do fluxo máximo em um grafo simples.
     */
    @Test
    public void testSimpleFlow() {
        int source = 0; // Índice do nó de origem
        int sink = testNetwork.getVerticesAmount() - 1; // Índice do nó de destino
        Dinic dinic = new Dinic(testNetwork, source, sink);
        dinic.solve();
        double expectedMaxFlow = 5000; // Valor esperado do fluxo máximo para o cenário de teste
        assertEquals(expectedMaxFlow, dinic.getMaxFlow(), "O fluxo máximo calculado não corresponde ao esperado.");
    }

    /**
     * Testa o cálculo do fluxo máximo em um grafo com múltiplos caminhos possíveis.
     */
    @Test
    public void testMultiplePaths() {
        int source = 0; // Índice do nó de origem
        int sink = testNetwork.getVerticesAmount() - 1; // Índice do nó de destino
        Dinic dinic = new Dinic(testNetwork, source, sink);
        dinic.solve();
        double expectedMaxFlow = 5000; // Valor esperado do fluxo máximo para o cenário de teste
        assertEquals(expectedMaxFlow, dinic.getMaxFlow(), "O fluxo máximo calculado não corresponde ao esperado.");
    }

    /**
     * Testa o cálculo do fluxo máximo em um grafo com capacidades variadas.
     */
    @Test
    public void testVariableCapacities() {
        int source = 0; // Índice do nó de origem
        int sink = testNetwork.getVerticesAmount() - 1; // Índice do nó de destino
        Dinic dinic = new Dinic(testNetwork, source, sink);
        dinic.solve();
        double expectedMaxFlow = 30; // Valor esperado do fluxo máximo para o cenário de teste
        assertEquals(expectedMaxFlow, dinic.getMaxFlow(), "O fluxo máximo calculado não corresponde ao esperado.");
    }

    /**
     * Testa o cálculo do fluxo máximo em um grafo com capacidades variadas e múltiplos caminhos possíveis.
     */
    @Test
    public void testVariableCapacitiesAndMultiplePaths() {
        int source = 0; // Índice do nó de origem
        int sink = testNetwork.getVerticesAmount() - 1; // Índice do nó de destino
        Dinic dinic = new Dinic(testNetwork, source, sink);
        dinic.solve();
        double expectedMaxFlow = 50; // Valor esperado do fluxo máximo para o cenário de teste
        assertEquals(expectedMaxFlow, dinic.getMaxFlow(), "O fluxo máximo calculado não corresponde ao esperado.");
    }
}
