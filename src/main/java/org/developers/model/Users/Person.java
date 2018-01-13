package org.developers.model.Users;


import java.time.LocalDate;
import java.util.Optional;
//Миронов - Внедрение ролей{
//человек
public abstract class Person {
    abstract String getLastname();
    abstract String getFirstname();
    abstract String getPatronymic();
    abstract LocalDate getBirthday();

    public String getName(String type) {
        String result = null;
        Optional<String> typeOptional = Optional.of(type);
        switch (typeOptional.orElse("full")) {
            case "full": {
                result = getLastname() + " " + getFirstname() + " " + getPatronymic();
                break;
            }
            case "abbr": {
                result = getLastname() + " " + getFirstname().substring(1, 1) + ". " + getPatronymic().substring(1, 1) + ".";
                break;
            }
            default:
                result = getLastname() + " " + getFirstname() + " " + getPatronymic();
        }
        return result;
    }
}
//Миронов - Внедрение ролей}
