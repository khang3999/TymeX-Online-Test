package utils;

import models.Product;

import java.util.Comparator;

public class ProductComparator {
    public  static Comparator<Product> getComparator(String option){
        String o = option.toLowerCase();
        switch (o) {
            case "price":
                return Comparator.comparingDouble(Product::getPrice);
            case "quantity":
                return Comparator.comparingDouble(Product::getQuantity);
            default:
                throw new IllegalArgumentException("Invalid criteria sort");
        }
    }
}
