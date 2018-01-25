package org.developers.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cashbox {

    private Employee saler;
    private Map<Product, Integer> list = new LinkedHashMap<>();;  // список продуктов
    private int numberCheck = 0;
    private int productCout = 0;
    private double totalSum = 0;
    private Map<Integer, Check> checkList; // список чеков

    public Cashbox(Employee saler) {
        this.saler = saler;
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


    private void addProduct(String name, int count) {
        Product product = new Product();
        if (product.getProductTitle().equals(name)) {
            totalSum += product.getProductPrice();
            productCout += count;
            list.put(product.foundProduct(name), count);
        }
        else
            System.out.println("товар не найден");
    }

    private void pay(Check check) {

    }

    private boolean verification(Check check) {
        return true;
    }

    private void closedShifts(Map<Integer, Check> checkList){
        double rezultSumm = 0;
        int rezultCount = 0;
        for (Map.Entry<Integer, Check> check: checkList.entrySet()) {
            rezultSumm += check.getValue().getTotalSum();
            rezultCount += check.getKey();
        }

        if (rezultSumm == totalSum && rezultCount == productCout) {
            // создается лог
            checkList.clear();
            list.clear();
            numberCheck = 0;
            productCout = 0;
        }
        else
            System.out.println("Суммы не сходятся, есть проблемные чеки");
    }
}
