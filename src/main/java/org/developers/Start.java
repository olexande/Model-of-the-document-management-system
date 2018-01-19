package org.developers;

import org.developers.model.Users.Person;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Start {
    public static void main( String[] args ){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("all-context.xml");
        Person personWork = ctx.getBean("person", Person.class);
        System.out.println(personWork.getPerson(1).getFirstname());
    }
}
