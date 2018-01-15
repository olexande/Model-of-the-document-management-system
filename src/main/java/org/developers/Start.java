package org.developers;

import org.developers.system.LogOfSystem;
import org.developers.system.LogType;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Start {
    public static void main( String[] args ){
        LogOfSystem.writeLog(LogType.EMPTY, "чушь");
    }
}
