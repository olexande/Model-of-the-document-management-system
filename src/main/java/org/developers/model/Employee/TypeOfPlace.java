package org.developers.model.Employee;

public enum TypeOfPlace {
    OFFICE("офис"),
    MARKET("торговая точка");

    private String place;

    TypeOfPlace(String place){
        this.place = place;
    }
}
