package org.developers.model.Employee;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.developers.model.Users.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.Optional;

//сотрудник
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Employee {
    @Autowired
    public Person person;
    @Autowired
    public Position position;

    private JdbcTemplate jdbcTemplate;

    public Employee(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void addEmployee(){
        //attention: если данные Person и Position не заполнены - вызываем исключение!
    }

    //attention: на реализацию
//    public Employee getEmployee(long id){
//        return new Employee(new Person());
//    }

    //todo: только администратор
    public void diffEmployee(Person person, long id) {
        Optional<Person> fieldOptional = Optional.ofNullable(person);
        //attention: создать фейковоого Person
        String queryUpdate = "UPDATE PERSON SET " + fieldOptional.orElse(FieldOfEmployee.LASTNAME).toString() + "=? WHERE ID=?";
        jdbcTemplate.update(queryUpdate, valueOptional.orElse("DEFAULT"), id >= 1 ? id : 1);
    }

    public void diffEmployee(String query) {
        if (query != null)
            jdbcTemplate.update(query);
        //запись в лог
    }

    //todo: только администратор
    public void deleteEmployee(Person person) {
        Optional<Person> fieldOptional = Optional.ofNullable(person);
        //attention: создать фейковоого Person
        String queryUpdate = "DELETE FROM PERSON WHERE " + fieldSearchOptional.orElse(FieldOfEmployee.LASTNAME).toString() + "=?";
        jdbcTemplate.update(queryUpdate, valueOptional.orElse("DEFAULT"));
    }

    public void deleteEmployeen(String query) {
        if (query != null)
            jdbcTemplate.update(query);
        //запись в лог
    }
}
