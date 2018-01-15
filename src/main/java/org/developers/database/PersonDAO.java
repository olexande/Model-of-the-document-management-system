package org.developers.database;

import org.developers.model.Person;

//работа с БД
public interface PersonDAO {
    //добавление записи
    void addPerson();

    //получение записи
    Person getPerson(long id);

    //изменение записи
    //todo: только директор
    void diffPerson(String field/*изменяемое поле*/, String value/*значение*/, long id/*конкретизация кортежа*/);

    //удаление записи
    //todo: только администратор
    void removePerson(long id);
}
