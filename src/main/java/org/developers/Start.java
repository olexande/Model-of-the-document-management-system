package org.developers;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;

public class Start {
    public static void main( String[] args ) throws SQLException {
        Organization.setNameSeller("Торговля");
        Organization.setInn(111111111);
        Organization.setSystemTaxation("orm");


        Check check1 = new Check(1232414);
        System.out.println(check1.toString());



    }
}
