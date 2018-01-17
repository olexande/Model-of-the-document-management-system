package org.developers.model;

public class Product {

    private String productTitle;
    private double productPrice;
    private int productCount;


    public String getProductTitle() {
        return productTitle;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public int getProductCount() {
        return productCount;
    }

    public Product foundProduct (String nameProduct) {
        return new Product();
    }
}
