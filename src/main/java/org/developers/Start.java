package org.developers;import org.developers.model.Users.Role;import org.springframework.context.ApplicationContext;import org.springframework.context.support.ClassPathXmlApplicationContext;public class Start {    public static void main(String[] args){        ApplicationContext ctx = new ClassPathXmlApplicationContext("all-context.xml");        Role user = ctx.getBean("admin", Role.class);        System.out.println(user.getName());        user.getRights().forEach((key, value)->System.out.println(key.getAbbr() + " -> " + value));    }}