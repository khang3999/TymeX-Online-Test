import models.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    private ArrayList<Product> productList;

    @BeforeEach
    public void setUp() {
        productList = new ArrayList<>();
        productList.add(new Product("Laptop", 999.99, 5));
        productList.add(new Product("Smartphone", 499.99, 10));
        productList.add(new Product("Tablet", 299.99, 0));
        productList.add(new Product("Smartwatch", 199.99, 3));
    }

    @Test
    public void testCalculateTotalInventoryValue() {
        double totalValue = Main.calculateTotalInventoryValue(productList);
        assertEquals(10599.82, totalValue, 0.01);
    }

    @Test
    public void testFindTheMostExpensiveProduct() {
        // Capture output using custom PrintStream
        final String expectedOutput = "The most expensive product is: Laptop";
        String result = Main.findTheMostExpensiveProduct(productList);
        assertEquals(expectedOutput, result);
    }

    @Test
    public void testCheckProductInStock_Exists() {
        assertTrue(Main.checkProductInStock(productList, "Laptop"), "Product 'Laptop' should be in stock.");
    }

    @Test
    public void testCheckProductInStock_NotExists() {
        assertFalse(Main.checkProductInStock(productList, "Desktop"), "Product 'Desktop' should not be in stock.");
    }

    @Test
    public void testSortProductByPriceAsc() {
        ArrayList<Product> sortedProducts = Main.sortProduct(productList, "asc", "price");
        assertEquals("Smartwatch", sortedProducts.get(0).getName(), "First product should be Smartwatch.");
        assertEquals("Laptop", sortedProducts.get(sortedProducts.size() - 1).getName(), "Last product should be Laptop.");
    }

    @Test
    public void testSortProductByQuantityDesc() {
        ArrayList<Product> sortedProducts = Main.sortProduct(productList, "desc", "quantity");
        assertEquals("Smartphone", sortedProducts.get(0).getName(), "First product should be Smartphone.");
        assertEquals("Tablet", sortedProducts.get(sortedProducts.size() - 1).getName(), "Last product should be Tablet.");
    }

    @Test
    public void testSortProductInvalidOrderBy() {
        ArrayList<Product> sortedProducts = Main.sortProduct(productList, "random", "price");
        assertEquals(productList, sortedProducts, "Sorted product list should remain the same for invalid orderBy option.");
    }

    @Test
    public void testSortProductInvalidOption()  {
        assertThrows(IllegalArgumentException.class, () -> {
            ArrayList<Product> sortedProducts = Main.sortProduct(productList, "asc", "weight");
        });

    }

    // Helper method to capture output
    private String captureOutput(Runnable task) {
        java.io.ByteArrayOutputStream outContent = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(outContent));
        task.run();
        System.setOut(System.out);
        return outContent.toString();
    }
}
