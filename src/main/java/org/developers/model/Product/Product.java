package org.developers.model.Product;

//товар
public interface Product<Count extends Number> {
    String getTitle();

    //закупочная цена
    double getPurchasePrice();

    //розничная цена
    double getRetailPrice();

    //увеличить остаток
    void plusBalance(Count quantity);

    //уменшить остаток
    void minusBalance(Count quantity);

    //получить текущий остаток
    Count getBalance();
}
