package org.developers.model.Product;

import java.math.BigDecimal;

//товар
public interface Product<Count extends Number> {
    String getTitle();

    //закупочная цена
    BigDecimal getPurchasePrice();

    //розничная цена
    BigDecimal getRetailPrice();

    //увеличить остаток
    void plusBalance(Count quantity);

    //уменшить остаток
    void minusBalance(Count quantity);

    //получить текущий остаток
    Count getBalance();
}
