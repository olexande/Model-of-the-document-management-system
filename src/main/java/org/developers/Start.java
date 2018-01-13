package org.developers;

import org.developers.business_logic.model.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Start {
    public static void main( String[] args ){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("all-context.xml");

//        User storeManager = ctx.getBean("store-manager", User.class);
//        storeManager.getRights().forEach((key, value)->System.out.println(key + " -> " + value));
    }
}
