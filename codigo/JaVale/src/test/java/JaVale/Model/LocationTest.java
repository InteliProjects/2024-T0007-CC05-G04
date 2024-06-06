package JaVale.Model;
import JaVale.Enums.LocationTypes;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocationTest {
    private Location node;
    private final LocationTypes initialType = LocationTypes.porto;
    private final int initialIndex = 1;
    private final String initialDescription = "Porto de Tubarão";
    private final double initialStorageMax = 100D;

    /*
     * Inicializa um objeto Location antes de cada teste.
     *
     */
    @Before
    public void setUp() {
        node = new Location(initialType, initialIndex, initialIndex, initialStorageMax, initialDescription);
    }

    /*
     * Testes de métodos da classe Location.
     *
     */
    @Test
    public void testGetType() {
        assertEquals(initialType, node.getType());
    }

    /*
     * Testa a alteração do tipo de localidade.
     *
     */
    @Test
    public void testGetIndex() {
        assertEquals(initialIndex, node.getIndex());
    }

    /*
     * Testa a alteração do índice de localidade.
     *
     */
    @Test
    public void testSetIndex() {
        int newIndex = 2;
        node.setIndex(newIndex);
        assertEquals(newIndex, node.getIndex());
    }

    /*
     * Testa a alteração do índice de localidade para um valor negativo.
     *
     */
    @Test
    public void testSetIndexNegative() {
        int newIndex = -1;
        node.setIndex(newIndex);
        assertEquals(initialIndex, node.getIndex());
    }

    /*
     * Testa a alteração do índice de localidade para zero.
     *
     */
    @Test
    public void testGetStorageMax() {
        assertEquals(initialStorageMax, node.getStorageMax());
    }

    /*
     * Testa a alteração da capacidade máxima de armazenamento.
     *
     */
    @Test
    public void testSetStorageMax() {
        double newStorageMax = 200D;
        node.setStorageMax(newStorageMax);
        assertEquals(newStorageMax, node.getStorageMax());
    }

    /*
     * Testa a alteração da capacidade máxima de armazenamento para um valor negativo.
     *
     */
    @Test
    public void testGetDescription() {
        assertEquals(initialDescription, node.getDescription());
    }

    /*
     * Testa a alteração da descrição da localidade.
     *
     */
    @Test
    public void testSetDescription() {
        String newDescription = "Porto de Vitória";
        node.setDescription(newDescription);
        assertEquals(newDescription, node.getDescription());
    }

    /*
     * Testa a alteração da descrição da localidade para null.
     *
     */
    @Test
    public void testSetDescriptionNull() {
        String newDescription = null;
        node.setDescription(newDescription);
        assertEquals(initialDescription, node.getDescription());
    }

    /*
     * Testa a alteração da descrição da localidade para uma string vazia.
     *
     */
    @Test
    public void testSetDescriptionEmpty() {
        String newDescription = "";
        node.setDescription(newDescription);
        assertEquals(initialDescription, node.getDescription());
    }

    /*
     * Testes de métodos equals e hashCode da classe Location.
     *
     */
    @Test
    public void equals() {
        Location node2 = new Location(initialType, initialIndex, initialIndex, initialStorageMax, initialDescription);
        assertEquals(node, node2);
    }

    /*
     * Testa a comparação de um objeto Location com null.
     *
     */
    @Test
    public void equalsDifferentType() {
        Location node2 = new Location(LocationTypes.porto, initialIndex, initialIndex, initialStorageMax, initialDescription);
        assertEquals(node, node2);
    }

    /*
     * Testa a comparação de um objeto Location com um objeto de outra classe.
     *
     */
    @Test
    public void equalsDifferentIndex() {
        Location node2 = new Location(initialType, 2, initialIndex, initialStorageMax, initialDescription);
        assertEquals(node, node2);
    }

    /*
     * Testa a comparação de um objeto Location com outro objeto Location com índices diferentes.
     *
     */
    @Test
    public void hashCodeTest() {
        Location node2 = new Location(initialType, initialIndex, initialIndex, initialStorageMax, initialDescription);
        assertEquals(node.hashCode(), node2.hashCode());
    }

    /*
     * Testa a comparação de um objeto Location com outro objeto Location com índices diferentes.
     *
     */
    @Test
    public void hashCodeDifferentType() {
        Location node2 = new Location(LocationTypes.porto, initialIndex, initialIndex, initialStorageMax, initialDescription);
        assertEquals(node.hashCode(), node2.hashCode());
    }

    /*
     * Testa a comparação de um objeto Location com outro objeto Location com índices diferentes.
     *
     */
    @Test
    public void hashCodeDifferentIndex() {
        Location node2 = new Location(initialType, 2, initialIndex, initialStorageMax, initialDescription);
        assertEquals(node.hashCode(), node2.hashCode());
    }

}