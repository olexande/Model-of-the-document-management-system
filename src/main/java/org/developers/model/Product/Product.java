package org.developers.model.Product;

import java.math.BigDecimal;

//товар
public interface Product {
    String getTitle();

    //закупочная цена
    BigDecimal getPurchasePrice();

    //розничная цена
    BigDecimal getRetailPrice();

    //увеличить остаток
    void plusBalance(int quantity);

    //уменшить остаток
    void minusBalance(int quantity);

    //получить текущий остаток
    int getBalance();
}
