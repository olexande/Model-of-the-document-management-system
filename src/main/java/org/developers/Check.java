package org.developers;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Check extends Organization{

    Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;
    

    private static String nameSeller = Organization.getNameSeller();
    private static int inn = Organization.getINN();
    private static String systemTaxation = Organization.getSystemTaxation();
    private List productList = new ArrayList();
    private String pointSell = new PointSell().getAdressPoint();


    public Check(int idProduct) throws SQLException {
       try {
           Class.forName("org.postgresql.driver");

           String url = "jdbc:postgresql://localhost:5432/postgres";
           con = DriverManager.getConnection(url, "admin", "admin");
           stmt = con.createStatement();
           rs = stmt.executeQuery("SELECT * FROM person");


           System.out.println(rs);
       }
       catch (ClassNotFoundException e) {
           System.out.println("Соединение не найдено!");
       }
       catch (SQLException e) {
           System.out.println("Введенные данные не корректны");
       }
        finally {
           con.close();
           stmt.close();
           rs.close();
       }
    }



}
