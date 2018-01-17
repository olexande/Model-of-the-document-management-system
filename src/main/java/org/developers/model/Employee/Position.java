package org.developers.model.Employee;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;

//должность
public class Position {
    //наименование
    @Setter
    @Getter
    private String title;
    //расположение
    @Setter
    @Getter
    private TypeOfPlace place;
    //набор прав
    @Setter
    @Getter
    private LinkedHashMap<Rigths, Boolean> rigths;
}
