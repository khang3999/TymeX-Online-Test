
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // Init array
        int[] array = new int[]{3, 7, 1, 2, 6, 4};
        if (findMissingNumber(array) == Integer.MIN_VALUE) {
            System.out.println("Array is invalid");
        } else {
            System.out.println("Missing number in array is " + findMissingNumber(array));
        }
    }

    public static int findMissingNumber(int[] arr) {
        // -2 1 -2 0
        Arrays.sort(arr);
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] < 0) {
                return Integer.MIN_VALUE;
            }else if (arr[i] + 1 != arr[i + 1]) {
                return arr[i] + 1;
            }
        }
        return Integer.MIN_VALUE;
    }
}