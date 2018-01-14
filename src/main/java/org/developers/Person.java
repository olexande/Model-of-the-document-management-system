package org.developers;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import javax.sql.DataSource;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Person {
    private JdbcTemplate jdbcTemplate;

    public Person(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
    }

    public void add(String lastnameP, String firstnameP, String patronymicP, LocalDate birthdayP) {
        String query = "INSERT INTO PERSON(LASTNAME, FIRSTNAME, PATRONYMIC, BIRTHDAY) VALUES(?,?,?,?)";
        jdbcTemplate.update(query, lastnameP, firstnameP, patronymicP, Date.valueOf(birthdayP));
    }
}
