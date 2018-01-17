package org.developers.model.Employee;

import org.developers.model.Users.Person;
import org.springframework.beans.factory.annotation.Autowired;

//сотрудник
public class Employee {
    @Autowired
    public Person person;
    @Autowired
    public Position position;
}
