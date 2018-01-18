package org.developers.model.Users;

import lombok.Getter;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.StringTokenizer;

//персона
public class Person {
    @Getter
    private String lastname;
    @Getter
    private String firstname;
    @Getter
    private String patronymic;
    @Getter
    private LocalDate birthday;

    @Getter
    private long id;

    private JdbcTemplate jdbcTemplate;

    public Person(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Person(String lastname, String firstname, String patronymic, LocalDate birthday) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.patronymic = patronymic;
        this.birthday = birthday;
    }

    private Person(String lastname, String firstname, String patronymic, LocalDate birthday, long id) {
        this(lastname, firstname, patronymic, birthday);
        this.id = id;
    }

    public void add(String lastname, String firstname, String patronymic, LocalDate birthday) {
        Optional<String> lastnameOptional = Optional.ofNullable(lastname);
        Optional<String> firstnameOptional = Optional.ofNullable(firstname);
        Optional<String> patronymicOptional = Optional.ofNullable(patronymic);
        Optional<LocalDate> birthdayOptional = Optional.ofNullable(birthday);
        String query = "INSERT INTO PERSON(LASTNAME, FIRSTNAME, PATRONYMIC, BIRTHDAY) VALUES(?,?,?,?)";
        jdbcTemplate.update(query, lastnameOptional.orElse("ФАМИЛИЯ"), firstnameOptional.orElse("ИМЯ"), patronymicOptional.orElse("ОТЧЕСТВО"), Date.valueOf(birthdayOptional.orElse(LocalDate.of(3000, 12, 12))));
    }

    public Person getPerson(long id) {
        Person person;
        String query = "SELECT ID, LASTNAME, FIRSTNAME, PATRONYMIC, BIRTHDAY FROM PERSON WHERE ID=?";
        try {
            List<Map<String, Object>> result = jdbcTemplate.queryForList(query, id);
            //attention: можешь лучше? Сделай!{
            //todo: преобразование строки в LocalDate
            StringTokenizer dateToken = new StringTokenizer(result.get(0).get("birthday").toString(), "-");
            int[] dateArray = new int[3];
            int i = 0;
            while (dateToken.hasMoreTokens()) {
                dateArray[i] = Integer.valueOf(dateToken.nextToken());
                i++;
            }
            //attention: можешь лучше? Сделай!}
            person = new Person(result.get(0).get("lastname").toString(), result.get(0).get("firstname").toString(), result.get(0).get("patronymic").toString(), LocalDate.of(dateArray[0], dateArray[1], dateArray[2]), (Long) result.get(0).get("id"));
        } catch (IndexOutOfBoundsException ex) {

            //rule: определение правил для задания ФИО в случае ошибки запроса
            //rule: фамилия: "БЕЗ ФАМИЛИИ"
            //rule: имя: "БЕЗ ИМЕНИ"
            //rule: отчество: "БЕЗ ОТЧЕСТВА"
            //rule: день рождения: "31 декабря 3000 г."

            person = new Person("БЕЗ ФАМИЛИИ", "БЕЗ ИМЕНИ", "БЕЗ ОТЧЕСТВА", LocalDate.of(31, 12, 3000));
        }
        return person;
    }

    //todo: только администратор
    public void diffPerson(FieldOfPerson field, String value, long id) {
        Optional<FieldOfPerson> fieldOptional = Optional.ofNullable(field);
        Optional<String> valueOptional = Optional.ofNullable(value);
        String queryUpdate = "UPDATE PERSON SET " + fieldOptional.orElse(FieldOfPerson.LASTNAME).toString() + "=? WHERE ID=?";
        jdbcTemplate.update(queryUpdate, valueOptional.orElse("DEFAULT"), id >= 1 ? id : 1);
    }

    //todo: только администратор
    public void deletePerson(FieldOfPerson fieldSearch, String value) {
        Optional<FieldOfPerson> fieldSearchOptional = Optional.ofNullable(fieldSearch);
        Optional<String> valueOptional = Optional.ofNullable(value);
        String queryUpdate = "DELETE FROM PERSON WHERE " + fieldSearchOptional.orElse(FieldOfPerson.LASTNAME).toString() + "=?";
        jdbcTemplate.update(queryUpdate, valueOptional.orElse("DEFAULT"));
    }

    //для более удобного представления имени
    public String getName(String type) {
        String result = null;
        Optional<String> typeOptional = Optional.ofNullable(type);
        switch (typeOptional.orElse("full")) {
            case "full": {
                result = getLastname() + " " + getFirstname() + " " + getPatronymic();
                break;
            }
            case "abbr": {
                result = getLastname() + " " + getFirstname().substring(0, 1) + ". " + getPatronymic().substring(0, 1) + ".";
                break;
            }
            default:
                result = getLastname() + " " + getFirstname() + " " + getPatronymic();
        }
        return result;
    }
}
