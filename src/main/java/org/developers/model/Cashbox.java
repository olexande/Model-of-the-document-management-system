package org.developers.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cashbox {

    private Employee saler;
    private Map<Product, Integer> list;  // список продуктов
    private int numberCheck = 0;
    private int productCout = 0;
    private double totalSum = 0;
    private Map<Integer, Check> checkList; // список чеков

    public Cashbox(Employee saler) {
        this.saler = saler;
        list = new LinkedHashMap<>();
    }

    private void selling() throws IOException {

        // реализация простого консольного интерфеса для теста реализации.
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String productNameMethod;
            int productCountMethod;

            productNameMethod = reader.readLine();
            if (productNameMethod.length() == 0) {
                break;
            }
            productCountMethod = Integer.parseInt(reader.readLine());
            addProduct(productNameMethod, productCountMethod);
        }
        Check check = new Check(numberCheck, list, productCout, totalSum);
        pay(check);
        if(verification(check)) {
            numberCheck++;
            checkList.put(numberCheck, check);
        }
        else
            System.out.println("Оплата чека не завершена");

    }

    public void addProduct(String name, int count) {
        list.put(new Product().foundProduct(name), count);
    }

    public void pay(Check check) {

    }

    public boolean verification(Check check) {
        return true;
    }


    private void diffToBase(){

    }
}
