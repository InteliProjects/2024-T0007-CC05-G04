package JaVale.Algoritmos;

import JaVale.Model.LogisticNetwork;
import JaVale.Repository.JaValeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import JaVale.Algoritmos.EdmondsKarp;

/**
 * Classe de testes para o algoritmo de Edmonds-Karp.
 * Testa a funcionalidade do algoritmo em calcular o fluxo máximo em grafos com diferentes configurações.
 */
public class EdmondsKarpTest {

    private LogisticNetwork network;

    /**
     * Configuração inicial para os testes.
     * Prepara uma rede logística de teste antes de cada teste.
     */
    @BeforeEach
    public void setUp() {
        // Inicializa o repositório e configura a rede logística para testes
        JaValeRepository repository = new JaValeRepository();
        network = repository.getLogisticNetwork();
    }

    /**
     * Testa o cálculo do fluxo máximo em um grafo simples.
     */
    @Test
    public void testSimpleNetworkFlow() {
        EdmondsKarp ek = new EdmondsKarp(network);
        int source = 0; // Índice do vértice fonte
        int sink = network.getVerticesAmount() - 1; // Índice do vértice sumidouro
        
        double expectedMaxFlow = 5000; // Valor esperado do fluxo máximo
        assertEquals(expectedMaxFlow, ek.edmondsKarp(source, sink), "O fluxo máximo calculado não é o esperado para um grafo simples.");
    }
}
