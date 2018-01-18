package org.developers.model.Employee;

import org.developers.model.Users.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

//сотрудник
public class Employee {
    @Autowired
    public Person person;
    @Autowired
    public Position position;

    private JdbcTemplate jdbcTemplate;

    public Employee(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private Employee(Person person, Position position) {
        this.person = person;
        this.position = position;
    }

    public void addEmployee() throws EmployeeException {
        //todo: если данные Person и Position не заполнены - вызываем исключение!
        if (person != null && position != null) {
            String queryInsert = "INSERT INTO EMPLOYEE(ID_PERSON, POSITION) VALUES(?,?)";
            jdbcTemplate.update(queryInsert, person.getId() >= 1 ? person.getId() : 1, position.getPlace());
        } else
            throw new EmployeeException("не заполнены свойства \"Person\" и/или \"Position\"");
    }

    public Employee getEmployee(long id) {
        Employee employee;
        String query = "SELECT ID_PERSON, POSITION FROM EMPLOYEE WHERE ID=?";
        try {
            List<Map<String, Object>> result = jdbcTemplate.queryForList(query, id);
            ApplicationContext cxt = new ClassPathXmlApplicationContext("all-context.xml");
            Person personWork = cxt.getBean("person", Person.class);
            ApplicationContext ctxPositions = new ClassPathXmlApplicationContext("users/positions.xml");
            Position positionWork = null;
            //attention: добработать
            switch (result.get(0).get("position").toString()) {
                case "admin": {
                    positionWork = ctxPositions.getBean("admin", Position.class);
                    break;
                }
            }
            employee = new Employee(personWork.getPerson((long) result.get(0).get("id_person")), positionWork);
        } catch (IndexOutOfBoundsException ex) {
            //rule: определение правил для задания ФИО в случае ошибки запроса
            //rule: фамилия: "БЕЗ ФАМИЛИИ"
            //rule: имя: "БЕЗ ИМЕНИ"
            //rule: отчество: "БЕЗ ОТЧЕСТВА"
            //rule: день рождения: "31 декабря 3000 г."
            employee = new Employee(new Person("БЕЗ ФАМИЛИИ", "БЕЗ ИМЕНИ", "БЕЗ ОТЧЕСТВА", LocalDate.of(31, 12, 3000)), new Position());
        }
        return employee;
    }

    //todo: только администратор
    public void diffEmployee(EmployeeFiled field, Object value, long id) {
        Optional<EmployeeFiled> fieldOptional = Optional.ofNullable(field);
        Optional<Object> valueOptional = Optional.ofNullable(value);
        String queryUpdate = "UPDATE employee SET " + fieldOptional.orElse(EmployeeFiled.PERSON) + "=? WHERE ID=?";
        jdbcTemplate.update(queryUpdate, valueOptional.orElse(0), id >= 1 ? id : 1); //attention: т.е. будет ошибка
    }


    //todo: только администратор
    public void deleteEmployee(long id) {
        String queryUpdate = "DELETE FROM PERSON WHERE ID=?";
        jdbcTemplate.update(queryUpdate, id >= 1 ? id : 0); //attention: т.е. будет ошибка
    }
}
