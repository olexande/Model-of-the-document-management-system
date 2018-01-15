package org.developers.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.developers.database.PersonDAO;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.StringTokenizer;

//attention: я не знаю как перепроектировать этот класс, чтобы не проверять
//attention: в каждом методе инициализированность перееменной jdbcTemplate

public class Person implements PersonDAO {
    //типичные данные{
    @Getter
    @Setter
    private String lastname;
    @Getter
    @Setter
    private String firstname;
    @Getter
    @Setter
    private String patronymic;
    @Getter
    @Setter
    private LocalDate birthday;
    //типичные данные}

    //для работы с БД{
    private JdbcTemplate jdbcTemplate;
    @Setter
    @NonNull
    private DataSource dataSource;
    //для работы с БД}

    @Override
    public void addPerson() {
        //защитим поля класс от null-значений
        Optional<String> lastnameOptional = Optional.ofNullable(this.lastname);
        Optional<String> firstnameOptional = Optional.ofNullable(this.firstname);
        Optional<String> patronymicOptional = Optional.ofNullable(this.patronymic);
        Optional<LocalDate> birthdayOptional = Optional.ofNullable(this.birthday);

        //rule: ###################################################
        //rule: значения полей класс Person в случае null-значений:
        //rule: lastname - БЕЗ_ФАМИЛИИ
        //rule: firstanme - БЕЗ_ИМЕНИ
        //rule: patronymic - БЕЗ_ОТЧЕСТВА
        //rule: birthday - 31.12.3000
        //rule: ###################################################

        //выполняем проверку
        checkJdbcTemplate();

        String queryInsert = "INSERT INTO PERSON(LASTNAME, FIRSTNAME, PATRONYMIC, BIRTHDAY) VALUES(?,?,?,?)";
        jdbcTemplate.update(queryInsert,
                lastnameOptional.orElseGet(() -> "БЕЗ_ФАМИЛИИ"),
                firstnameOptional.orElseGet(() -> "БЕЗ_ИМЕНИ"),
                patronymicOptional.orElseGet(() -> "БЕЗ_ОТЧЕСТВА"),
                Date.valueOf(birthdayOptional.orElseGet(() -> LocalDate.of(3000, 12, 31))));
    }

    @Override
    public Person getPerson(long id) {
        String querySelect = "SELECT LASTNAME, FIRSTNAME, PATRONYMIC, BIRTHDAY FROM PERSON WHERE ID=?";

        //выполняем проверку
        checkJdbcTemplate();

        List<Map<String, Object>> result = jdbcTemplate.queryForList(querySelect, id);
        Person person = new Person();
        person.setLastname(result.get(0).get("lastname").toString());
        person.setLastname(result.get(0).get("firstname").toString());
        person.setLastname(result.get(0).get("patronymic").toString());

        //преобразуем в LocalDate{
        //attention: знаешь способ лучше? Покажи!
        StringTokenizer date = new StringTokenizer(result.get(0).get("birthday").toString(), "-");
        int[] realDate = new int[3];
        int i = 0;
        while (date.hasMoreTokens()) {
            realDate[i] = Integer.valueOf(date.nextToken());
            i++;
        }
        person.setBirthday(LocalDate.of(realDate[0], realDate[1], realDate[2]));
        //преобразуем в LocalDate}

        return person;
    }

    //attention: параметр field необходимо здавать так, как прописано в базе => соответствует имени поля в данном классе
    @Override
    public void diffPerson(String field, String value, long id) {
        //выполняем проверку
        checkJdbcTemplate();

        String queryUpdate = "UPDATE PERSON SET " + field + "=? WHERE ID=?";
        jdbcTemplate.update(queryUpdate, value, id);
    }

    @Override
    public void removePerson(long id) {
        //выполняем проверку
        checkJdbcTemplate();

        String queryDelete = "DELETE FROM PERSON WHERE ID=?";
        jdbcTemplate.update(queryDelete, id);
    }

    //проверка JDBC-шаблона
    private void checkJdbcTemplate() {
        if (jdbcTemplate == null) {
            jdbcTemplate = new JdbcTemplate();
            jdbcTemplate.setDataSource(dataSource);
        }
    }
}
