package org.developers;

import org.developers.model.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Start {
    public static void main(String[] args){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("all-context.xml");
        User user = ctx.getBean("user", User.class);
        System.out.println(user.getName());
    }
}
