package org.developers;

import org.developers.model.Users.Person;
import org.developers.system.Initialization;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Start {
    //rule: указание папки "установки"
    public static String pathToApplication = "M:\\";

    public static void main(String[] args) {
        //rule: здесь это должно быть всегда
        Initialization.init(pathToApplication);
        //rule: ############################

        ApplicationContext ctx = new ClassPathXmlApplicationContext("all-context.xml");


    }
}
