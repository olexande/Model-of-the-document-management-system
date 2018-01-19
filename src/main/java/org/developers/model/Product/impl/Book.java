package org.developers.model.Product.impl;

import lombok.AllArgsConstructor;
import lombok.Setter;
import org.developers.model.Product.Product;
import org.developers.model.Product.ProductDAO;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;

@AllArgsConstructor
public class Book implements Product, ProductDAO {

    private JdbcTemplate jdbcTemplate;

    @Setter
    /*наименование товара*/
    private String title;

    //данные книги
    private String titleOFBook;
    private String author;
    private short numberOfPage;
    private String publishigHouse;


    public Book(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public String getTitle() {
        return title;
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
    public ArrayList<Product> getAll() {
        return null;
    }
}
