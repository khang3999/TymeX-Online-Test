import models.Product;
import utils.ProductComparator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Init product list
        ArrayList<Product> productList = new ArrayList<Product>();
        productList.add(new Product("Laptop", 999.99, 5));
        productList.add(new Product("Smartphone", 499.99, 10));
        productList.add(new Product("Tablet", 299.99, 0));
        productList.add(new Product("Smartwatch", 199.99, 3));

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            System.out.println("\nMenu:");
            System.out.println("1. Calculate total inventory value");
            System.out.println("2. Find the most expensive product");
            System.out.println("3. Check product in stock");
            System.out.println("4. Sort products");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    double totalValue = calculateTotalInventoryValue(productList);
                    System.out.printf("Total inventory value: %.2f%n", totalValue);
                    break;
                case "2":
                    findTheMostExpensiveProduct(productList);
                    break;
                case "3":
                    System.out.print("Enter the product name to check: ");
                    String productName = scanner.nextLine();
                    boolean inStock = checkProductInStock(productList, productName);
                    System.out.println(inStock ? productName + " is in stock." : productName + " is not in stock.");
                    break;
                case "4":
                    System.out.print("Enter sorting order (asc/desc): ");
                    String orderBy = scanner.nextLine();
                    System.out.print("Enter sorting criteria (price/quantity): ");
                    String option = scanner.nextLine();
                    ArrayList<Product> sortedProducts = sortProduct(productList, orderBy, option);
                    System.out.println("Sorted product list by "+orderBy +" "+ option+" :");
                    sortedProducts.forEach(product -> System.out.println(product.toString()));
                    break;
                case "5":
                    System.out.println("Exiting program.");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    //Method: Calculate total inventory value
    public static double calculateTotalInventoryValue(ArrayList<Product> productList) {
        double totalValue = 0;
        for (Product p : productList) {
            totalValue += p.getPrice() * p.getQuantity();
        }
        return totalValue;
    }

    //Method: Find the most expensive product
    public static void findTheMostExpensiveProduct(ArrayList<Product> productList) {
        int indexMostExpensivePrice = 0;
        for (int i = 1; i < productList.size(); i++) {
            if (productList.get(i).getPrice() > productList.get(indexMostExpensivePrice).getPrice()) {
                indexMostExpensivePrice = i;
            }
        }
        System.out.println("The most expensive product is: "+productList.get(indexMostExpensivePrice).getName());
    }

    //Method: Check product in stock
    public static boolean checkProductInStock(ArrayList<Product> productList, String keywork) {
        for (Product p : productList) {
            if (p.getName().toLowerCase().equalsIgnoreCase(keywork)) return true;
        }
        return false;
    }

    //Method: Sort by option and orderBy
    public static ArrayList<Product> sortProduct(ArrayList<Product> products, String orderBy, String option) {
        ArrayList<Product> resultList = new ArrayList<>(products);
        // Get comparator
        Comparator<Product> comparator = ProductComparator.getComparator(option);

        if (orderBy.equalsIgnoreCase("desc")) {
            resultList.sort(comparator.reversed());
        } else if (orderBy.equalsIgnoreCase("asc")) {
            resultList.sort(comparator);
        } else {
            System.out.println("Invalid orderBy option. Cannot sort product list.");
        }
        return resultList;
    }
}