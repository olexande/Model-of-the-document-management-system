package org.developers.model.Users.impl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.developers.model.Users.Person;
import org.developers.model.Users.Position;
import org.developers.model.Users.Role;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.LocalDate;
import java.util.Optional;

@AllArgsConstructor
public class Employee extends Person {

    private String lastname;
    private String firstname;
    private String patronymic;
    private LocalDate birthday;
    @Getter
    private Role role;
    @Getter
    private Position position;

    //todo: имеет право выполнять только админситратор
    public void diffRole(Role role) {
        Optional<Role> roleOptional = Optional.of(role);
        ApplicationContext cxtOfRole = new ClassPathXmlApplicationContext("users/roles.xml");
        this.role = roleOptional.orElse(cxtOfRole.getBean("untitled", Role.class));
    }

    //todo: имеет право выполнять только директор
    public void diffPosition(Position position) {
        Optional<Position> positionOptional = Optional.of(position);
        ApplicationContext cxtOfRole = new ClassPathXmlApplicationContext("users/positions.xml");
        this.position = positionOptional.orElse(cxtOfRole.getBean("untitled", Position.class));
    }

    @Override
    public String getLastname() {
        return lastname;
    }

    @Override
    public String getFirstname() {
        return firstname;
    }

    @Override
    public String getPatronymic() {
        return patronymic;
    }

    @Override
    public LocalDate getBirthday() {
        return birthday;
    }
}
