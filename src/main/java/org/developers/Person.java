package org.developers;

import lombok.Getter;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Person {
    @Getter
    private String lastname;
    @Getter
    private String firstname;
    @Getter
    private String patronymic;
    @Getter
    private String birthday;

    private JdbcTemplate jdbcTemplate;

    public Person(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
    }

    Person(String lastname, String firstname, String patronymic, String birthday){
        this.lastname = lastname;
        this.firstname = firstname;
        this.patronymic = patronymic;
        this.birthday = birthday;
    }

    public void add(String lastname, String firstname, String patronymic, LocalDate birthday) {
        Optional<String> lastnameOptional = Optional.of(lastname);
        Optional<String> firstnameOptional = Optional.of(firstname);
        Optional<String> patronymicOptional = Optional.of(patronymic);
        Optional<LocalDate> birthdayOptional = Optional.of(birthday);
        String query = "INSERT INTO PERSON(LASTNAME, FIRSTNAME, PATRONYMIC, BIRTHDAY) VALUES(?,?,?,?)";
        jdbcTemplate.update(query, lastnameOptional.orElse("ФАМИЛИЯ"), firstnameOptional.orElse("ИМЯ"), patronymicOptional.orElse("ОТЧЕСТВО"), Date.valueOf(birthdayOptional.orElse(LocalDate.of(3000, 12, 12))));
    }

    public Person getPerson(long id){
        Person person = null;
        String query = "SELECT LASTNAME, FIRSTNAME, PATRONYMIC, BIRTHDAY FROM PERSON WHERE ID=?";
        List<Map<String, Object>> result = jdbcTemplate.queryForList(query, id);
        person = new Person(result.get(0).get("lastname").toString(), result.get(0).get("firstname").toString(), result.get(0).get("patronymic").toString(), result.get(0).get("birthday").toString());
        //Optional<Person> personOptional = Optional.of(person);
        return person;
    }

//    public ArrayList<Person> getPerson(String type, String str){
//
//    }
}
