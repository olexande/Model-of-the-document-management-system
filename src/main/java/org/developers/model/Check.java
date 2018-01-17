package org.developers.model;

import java.util.Collections;
import java.util.Map;


public class Check {
    private String pointSellName;
    private String pointSellAdress;
    private Map<Product, Integer> productList;
    private int numberCheck;
    private int productCout;
    private double totalSum;
    private boolean payment;


    public Check (int numerChek, Map<Product, Integer> productList, int count, double totalSum) {
        this.numberCheck = numerChek;
        pointSellName = ServiceData.getTitle();
        pointSellAdress = ServiceData.getAdress();
        this.productList = Collections.synchronizedMap(productList);
        this.productCout = count;
        this.totalSum = totalSum;

    }

    public String getPointSellName() {
        return pointSellName;
    }

    public String getPointSellAdress() {
        return pointSellAdress;
    }

    public Map<Product, Integer> getProductList() {
        return productList;
    }

    public int getProductCout() {
        return productCout;
    }

    public double getTotalSum() {
        return totalSum;
    }

    public boolean isPayment() {
        return payment;
    }

    public void setPayment(boolean payment) {
        this.payment = payment;
    }
}
