package org.developers.model.Users;


import java.time.LocalDate;
import java.util.Optional;
//Миронов - Внедрение ролей{
//человек
public abstract class Person {
    public abstract String getLastname();
    public abstract String getFirstname();
    public abstract String getPatronymic();
    public abstract LocalDate getBirthday();

    public String getName(String type) {
        String result = null;
        Optional<String> typeOptional = Optional.of(type);
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
//Миронов - Внедрение ролей}
