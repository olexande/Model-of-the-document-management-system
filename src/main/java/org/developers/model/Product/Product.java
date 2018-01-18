package org.developers.model.Product;

//товар
public interface Product {
    String getTitle();

    //закупочная цена
    double getPurchasePrice();

    //розничная цена
    double getRetailPrice();
}
