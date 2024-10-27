import models.Product;
import utils.ProductComparator;

import java.util.ArrayList;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        // Init product list
        ArrayList<Product> productList = new ArrayList<Product>();
        productList.add(new Product("Laptop", 999.99, 5));
        productList.add(new Product("Smartphone", 499.99, 10));
        productList.add(new Product("Tablet", 299.99, 0));
        productList.add(new Product("Smartwatch", 199.99, 3));

        double resutl = calculateTotalInventoryValue(productList);
        System.out.println(String.format("%.2f",resutl));
        findTheMostExpensiveProduct(productList);
        System.out.println(checkProductInStock(productList,"HeadPhone"));
        System.out.println(checkProductInStock(productList,"Laptop"));

        System.out.println(sortProduct(productList,"asc","quantity" ));

    }

    //Method: Calculate total inventory value
    public static double calculateTotalInventoryValue(ArrayList<Product> productList){
        double totalValue = 0;
        for(Product p : productList){
            totalValue += p.getPrice() * p.getQuantity();
        }
        return totalValue;
    }

    //Method: Find the most expensive product
    public static void findTheMostExpensiveProduct(ArrayList<Product> productList){
        int indexMostExpensivePrice = 0;
        for (int i = 1; i < productList.size(); i++) {
            if (productList.get(i).getPrice() > productList.get(indexMostExpensivePrice).getPrice()){
                indexMostExpensivePrice = i;
            }
        }
        System.out.println(productList.get(indexMostExpensivePrice).getName());
    }

    //Method: Check product in stock
    public static boolean checkProductInStock(ArrayList<Product> productList, String keywork){
        for (Product p: productList){
            if (p.getName().equals(keywork)) return true;
        }
        return false;
    }

    //Method: Sort by option and orderBy
    public static ArrayList<Product> sortProduct(ArrayList<Product> products,String orderBy, String option){
        ArrayList<Product> resultList = new ArrayList<>(products);
        // Get comparator
        Comparator<Product> comparator = ProductComparator.getComparator(option);

        if (orderBy.equals("desc")){
            resultList.sort(comparator.reversed());
        } else if (orderBy.equals("asc")){
            resultList.sort(comparator);
        } else {
            System.out.println("Invalid orderBy option. Cannot sort product list.");
        }
        return resultList;
    }
}