package org.developers.model.Product.impl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.developers.model.Product.Product;
import org.developers.model.Product.ProductDAO;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
public class Book implements Product, ProductDAO {

    //для SQL
    private JdbcTemplate jdbcTemplate;
    @Setter
    private long id;

    //данные товара
    private String titleOfProduct;
    private BigDecimal purchasePrice;
    private BigDecimal retailPrice;
    private int balance;

    //данные книги
    @Setter
    @Getter
    private String titleOfBook;
    @Setter
    @Getter
    private String author;
    @Setter
    @Getter
    private short numberOfPage;
    @Setter
    @Getter
    private String publishingHouse;
    @Setter
    @Getter
    private TypeOfBinding binding;

    private Book(String titleOfProduct,
                 BigDecimal purchasePrice,
                 BigDecimal retailPrice,
                 int balance,
                 String titleOfBook,
                 String author,
                 short numberOfPage,
                 String publishingHouse,
                 String binding) {

        Optional<String> titleOfProductOptional = Optional.ofNullable(titleOfProduct);
        this.titleOfProduct = titleOfProductOptional.orElse("БЕЗ НАЗВАНИЯ (ТОВАР)");

        Optional<BigDecimal> purchasePriceOptional = Optional.ofNullable(purchasePrice);
        this.purchasePrice = purchasePriceOptional.orElse(new BigDecimal(0));

        Optional<BigDecimal> retailPriceOptional = Optional.ofNullable(retailPrice);
        this.retailPrice = retailPriceOptional.orElse(new BigDecimal(0));

        this.balance = balance;

        Optional<String> titleOfBookOptional = Optional.ofNullable(titleOfBook);
        this.titleOfBook = titleOfBookOptional.orElse("БЕЗ НАЗВАНИЯ (КНИГА)");

        Optional<String> authorOptional = Optional.ofNullable(author);
        this.author = authorOptional.orElse("БЕЗ АВТОАР");

        this.numberOfPage = numberOfPage;

        Optional<String> publishingHouseOptional = Optional.ofNullable(publishingHouse);
        this.publishingHouse = publishingHouseOptional.orElse("БЕЗ ИЗДАТЕЛЬСТВА");

        switch (binding.toLowerCase()) {
            case "hard": {
                this.binding = TypeOfBinding.HARD;
                break;
            }
            default:
                this.binding = TypeOfBinding.EMPTY;
        }
    }


    public Book(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public String getTitle() {
        return titleOfProduct;
    }

    @Override
    public BigDecimal getPurchasePrice() {
        return purchasePrice;
    }

    @Override
    public BigDecimal getRetailPrice() {
        return retailPrice;
    }

    @Override
    public void plusBalance(int quantity) {
        quantity = getBalance() + (quantity >= 1 ? quantity : 0);
        String queryUpdate = "UPDATE PRODUCT SET BALANCE=? WHERE ID=(SELECT ID_PRODUCT FROM BOOK WHERE book.id=?)";
        try {
            jdbcTemplate.update(queryUpdate, quantity, id);
        } catch (IndexOutOfBoundsException ex) {
            //attention: лог об ошибке
        }
    }

    @Override
    public void minusBalance(int quantity) {
        quantity = getBalance() - (quantity >= 1 ? quantity : 0);
        String queryUpdate = "UPDATE PRODUCT SET BALANCE=? WHERE ID=(SELECT ID_PRODUCT FROM BOOK WHERE book.id=?)";
        try {
            jdbcTemplate.update(queryUpdate, quantity, id);
        } catch (IndexOutOfBoundsException ex) {
            //attention: лог об ошибке
        }
    }

    @Override
    public int getBalance() {
        String query = "SELECT PRODUCT.BALANCE FROM PRODUCT INNER JOIN BOOK ON PRODUCT.ID=BOOK.id_product AND book.id=?;";
        try {
            List<Map<String, Object>> result = jdbcTemplate.queryForList(query, id);
            this.balance = (int) result.get(0).get("balance");
        } catch (IndexOutOfBoundsException ex) {
            this.balance = -1;
        }
        return this.balance;
    }

    @Override
    public void add() {

    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public Book getProduct(long id) {
        Book book;
        String query = "SELECT PRODUCT.TITLE AS TITLE_OF_PRODUCT, PRODUCT.PURCHASE_PRICE, PRODUCT.RETAIL_PRICE, " +
                "PRODUCT.BALANCE, BOOK.TITLE AS TITLE_OF_BOOK, BOOK.AUTHOR, BOOK.NUMBER_PAGE, BOOK.DIMENSIONS, " +
                "BOOK.TYPE_OF_BINDING, BOOK.PUBLISHING_HOUSE FROM BOOK INNER JOIN PRODUCT ON BOOK.ID_PRODUCT=PRODUCT.ID AND BOOK.ID=?";
        try {
            List<Map<String, Object>> result = jdbcTemplate.queryForList(query, id);
            book = new Book(result.get(0).get("title_of_product").toString(),
                    (BigDecimal) result.get(0).get("purchase_price"),
                    (BigDecimal) result.get(0).get("retail_price"),
                    (int) result.get(0).get("balance"),
                    result.get(0).get("title_of_book").toString(),
                    result.get(0).get("author").toString(),
                    Short.valueOf(result.get(0).get("number_page").toString()),
                    result.get(0).get("publishing_house").toString(),
                    result.get(0).get("type_of_binding").toString());
        } catch (IndexOutOfBoundsException ex) {
            book = new Book(null,
                    null,
                    null,
                    0,
                    null,
                    null,
                    (short) 0,
                    null,
                    null);
        }
        return book;
    }

    @Override
    public List<Product> getAll() {
        ArrayList<Product> result = new ArrayList<>();
        String queryAll = "SELECT\n" +
                "  PRODUCT.TITLE AS TITLE_OF_PRODUCT,\n" +
                "  PRODUCT.PURCHASE_PRICE,\n" +
                "  PRODUCT.retail_price,\n" +
                "  PRODUCT.BALANCE,\n" +
                "  BOOK.TITLE AS TITLE_OF_BOOK,\n" +
                "  BOOK.AUTHOR,\n" +
                "  BOOK.NUMBER_PAGE,\n" +
                "  BOOK.PUBLISHING_HOUSE,\n" +
                "  BOOK.TYPE_OF_BINDING\n" +
                "FROM PRODUCT\n" +
                "  INNER JOIN BOOK ON PRODUCT.ID = BOOK.ID_PRODUCT;";
        List<Map<String, Object>> buf = jdbcTemplate.queryForList(queryAll);
        buf.forEach(record -> {
            result.add(new Book(record.get("title_of_product").toString(),
                    (BigDecimal) record.get("purchase_price"),
                    (BigDecimal) record.get("retail_price"),
                    (int) record.get("balance"),
                    record.get("title_of_book").toString(),
                    record.get("author").toString(),
                    Short.valueOf(record.get("number_page").toString()),
                    record.get("publishing_house").toString(),
                    record.get("type_of_binding").toString()));
        });
        return result;
    }
}
