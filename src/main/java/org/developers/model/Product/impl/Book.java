package org.developers.model.Product.impl;

import lombok.AllArgsConstructor;
import org.developers.model.Product.Product;
import org.developers.model.Product.ProductDAO;

import java.util.List;

@AllArgsConstructor
public class Book implements Product, ProductDAO {
    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public double getPurchasePrice() {
        return 0;
    }

    @Override
    public double getRetailPrice() {
        return 0;
    }

    @Override
    public void plusBalance(Number quantity) {

    }

    @Override
    public void minusBalance(Number quantity) {

    }

    @Override
    public Number getBalance() {
        return null;
    }

    @Override
    public void add() {

    }

    @Override
    public long getId() {
        return 0;
    }

    @Override
    public Product getProduct(long id) {
        return null;
    }

    @Override
    public List<Product> getAll() {
        return null;
    }
}
