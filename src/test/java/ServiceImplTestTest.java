import dto.DTOToy;
import models.Toy;
import models.TypeToy;
import org.junit.Test;
import service.ToyService;
import service.impl.ServiceImpl;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ServiceImplTestTest {

    @Test
    public void addToy_Test() {
        ServiceImpl toyService = new ServiceImpl();
        DTOToy toyToAdd = new DTOToy("Doll", TypeToy.FEMALE, 10.0, 20);
        assertDoesNotThrow(() -> toyService.addToy(toyToAdd));
    }

    @Test
    public void showByType_Test() {
        ServiceImpl toyService = new ServiceImpl();
        assertDoesNotThrow(() -> {
            Map<TypeToy, Integer> toyMap = toyService.showByType();
            assertNotNull(toyMap);
            assertFalse(toyMap.isEmpty());
        });
    }

    @Test
    public void totalCount_Test() {
        ServiceImpl toyService = new ServiceImpl();
        assertDoesNotThrow(() -> {
            int totalCount = toyService.totalCount();
            assertTrue(totalCount >= 0);
        });
    }

    @Test
    public void totalValue_Test() throws Exception {
        ServiceImpl toyService = new ServiceImpl();
        double totalValue = toyService.totalValue();
        assertTrue(totalValue >= 0);
    }

    @Test
    public void decreaseQuantity() {
        ServiceImpl toyService = new ServiceImpl();
        String toyName = "Doll";
        int quantity = 1;
        assertDoesNotThrow(() -> toyService.decreaseTotal(toyName, quantity));
        assertDoesNotThrow(() -> {
            int newQuantity = toyService.filterByPrice(0)
                    .stream()
                    .filter(dto -> dto.name().equalsIgnoreCase(toyName))
                    .findFirst()
                    .orElseThrow(() -> new Exception("Toy not found"))
                    .quantity();
            assertEquals(4, newQuantity);
        });
    }

    @Test
    public void increaseTotal_Test() {
        ServiceImpl toyService = new ServiceImpl();
        String toyName = "Doll";
        int quantity = 1;
        assertDoesNotThrow(() -> toyService.increaseTotal(toyName, quantity));

        assertDoesNotThrow(() -> {
            int newQuantity = toyService.filterByPrice(0)
                    .stream()
                    .filter(dtoToy -> dtoToy.name().equalsIgnoreCase(toyName)) // Usamos name() en lugar de getName()
                    .findFirst()
                    .orElseThrow()
                    .quantity(); // Usamos quantity() en lugar de getQuantity()
            assertEquals(6, newQuantity); // Asumiendo que inicialmente había 5 muñecas
        });
    }

    @Test
    public void mxToy_Test() {
        ServiceImpl toyService = new ServiceImpl();
        assertDoesNotThrow(() -> {
            Map.Entry<TypeToy, Integer> mostCommonToy = toyService.mxToy();
            assertNotNull(mostCommonToy);
        });
    }

    @Test
    public void mnToy_Test() {
        ServiceImpl toyService = new ServiceImpl();
        assertDoesNotThrow(() -> {
            Map.Entry<TypeToy, Integer> leastCommonToy = toyService.minToy();
            assertNotNull(leastCommonToy);
        });
    }

    @Test
    public void filterByPrice_Test() {
        ServiceImpl toyService = new ServiceImpl();
        assertDoesNotThrow(() -> {
            List<DTOToy> filteredToys = toyService.filterByPrice(5.0);
            assertNotNull(filteredToys);
            assertFalse(filteredToys.isEmpty());
        });
    }

    @Test
    public void sortByQuantity_Test() {
        ServiceImpl toyService = new ServiceImpl();
        assertDoesNotThrow(() -> {
            List<DTOToy> sortedToys = toyService.sortByQuantity();
            assertNotNull(sortedToys);
            assertFalse(sortedToys.isEmpty());
        });
    }

    @Test
    public void AllToys_Test() {
        ServiceImpl toyService = new ServiceImpl();
        assertDoesNotThrow(() -> {
            List<DTOToy> allToys = toyService.AllToys();
            assertNotNull(allToys);
            assertFalse(allToys.isEmpty());
        });
    }

    @Test
    public void findByName_Test() {

        ServiceImpl toyService = new ServiceImpl();
        String toyName = "Doll";

        // Act
        try {
            Toy foundToy = toyService.findByName(toyName);
            assertNotNull(foundToy);
            assertEquals(toyName, foundToy.getName());
        } catch (Exception e) {
            fail("Unexpected exception: " + e.getMessage());

        }
    }
}