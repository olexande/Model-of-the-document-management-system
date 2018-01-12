package org.developers.business_logic.model;

import java.io.Serializable;
import java.time.LocalDate;
//Миронов - Внедрение  документов и отчётов

//описание любого документа
public interface Document extends Serializable {
    //todo: правила наименования документа: "Имя документа №[номер_документа] от [дата_проводки]"
    //наименование документа
    String getTitle();

    //получение номера
    String getNumber();

    //получение даты
    LocalDate getDate();

    //сохранение
    void save();

    //проводка
    void write();

    //удаление
    void delete();

    //подпись
    //todo: правила подписи ещё не определены
    void signature(Object o1, Object o2);
}
