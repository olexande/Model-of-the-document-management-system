package org.developers.model.Product.impl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.developers.model.Product.Product;

public class Book implements Product {

    private String title;
    private double purchasePrice;
    private double retailPrice;

    @Getter
    private String titleOfBook;
    @Getter
    private String author;
    @Getter
    private short numberPage;
    @Getter
    private String publishingHouse;
    @Getter
    private String typeOfBinding;

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public double getPurchasePrice() {
        return purchasePrice;
    }

    @Override
    public double getRetailPrice() {
        return retailPrice;
    }
}
