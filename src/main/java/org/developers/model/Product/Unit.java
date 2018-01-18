package org.developers.model.Product;

public enum Unit {
    UNIT("шт."),
    KG("кг.");

    private String unit;

    Unit(String unit) {
        this.unit = unit;
    }
}
