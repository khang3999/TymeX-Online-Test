import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class MainTest {
    @Test
    public void testFindMissingNumberWithValidArray() {
        // Test array with a missing number
        int[] array = {3, 7, 1, 2, 6, 4};
        assertEquals(5, Main.findMissingNumber(array));
    }

    @Test
    public void testFindMissingNumberWithNoMissingNumber() {
        // Test array with no missing number
        int[] array = {1, 2, 3, 4, 5, 6, 7};
        assertEquals(Integer.MIN_VALUE, Main.findMissingNumber(array));
    }

    @Test
    public void testFindMissingNumberWithSingleElement() {
        // Test array with only one element
        int[] array = {1};
        assertEquals(Integer.MIN_VALUE, Main.findMissingNumber(array));
    }

    @Test
    public void testFindMissingNumberWithEmptyArray() {
        // Test empty array
        int[] array = {};
        assertEquals(Integer.MIN_VALUE, Main.findMissingNumber(array));
    }

    @Test
    public void testFindMissingNumberWithNegativeNumbersV1() {
        // Test array with negative numbers and a missing number
        int[] array = {-3, -2, -5, -4, -1};
        assertEquals(Integer.MIN_VALUE, Main.findMissingNumber(array));
    }

    @Test
    public void testFindMissingNumberWithNegativeNumbersV2() {
        // Test array with negative numbers and a missing number
        int[] array = { 0, -2, 2, 1};
        assertEquals(Integer.MIN_VALUE, Main.findMissingNumber(array));
    }
    @Test
    public void testFindMissingNumberWithNegativeNumbersV3() {
        // Test array with negative numbers and a missing number
        int[] array = { -1, 3, 2, 1};
        assertEquals(Integer.MIN_VALUE, Main.findMissingNumber(array));
    }
}

