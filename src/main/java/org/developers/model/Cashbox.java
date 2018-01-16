package org.developers.model;

import java.util.LinkedHashMap;
import java.util.Map;

public class Cashbox {

    private Employee saler;
    private Map<Product, Integer> list;
    private double totalSum;

    public Cashbox(Employee saler) {
        this.saler = saler;
        list = new LinkedHashMap<>();
    }

    public void addProduct(Product product, int count) {
        list.put(product, count);
    }

    public void total() {
        list.forEach((productSum, count) -> {
            totalSum += productSum.getPrice() * count;
            System.out.println(productSum.getTitle() + "\t" + productSum.getPrice() + "руб.\t" + productSum.getPrice() * count + " руб.");
        });
        System.out.println("Общая сумма: " + totalSum + " руб.");
        //вывод общей суммы
    }

    public void pay() {

    }

    public void closeCheck() {
        //вывод чека на экран
        //формировнаие файла XML

        diifToBase();
        totalSum = 0;
        list.clear();
    }

    private void diifToBase(){

    }
}
