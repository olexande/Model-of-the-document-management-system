package org.developers.model.Product;

import java.util.ArrayList;

//Интерфейс работы с БД
/*desc: Набор этих абстрактных методов НЕ ДОЛЖЕН меняться*/
public interface ProductDAO {
    //добавить товар в БД
    void add();

    //получение id (кода)
    long getId();

    //получение продукта по id (коду)
    Product getProduct(long id);

    //получение списка всех товаров
    ArrayList<Product> getAll();
}
