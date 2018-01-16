package org.developers;

public class Organization {

    private static String nameSeller;
    private static int inn;
    private static String systemTaxation;


   public Organization () {
   }

    public static String getNameSeller() {
        return nameSeller;
    }

    public static void setNameSeller(String nameSeller) {
        Organization.nameSeller = nameSeller;
    }

    public static int getINN() {
        return inn;
    }

    public static void setInn(int inn) {
        Organization.inn = inn;
    }

    public static String getSystemTaxation() {
        return systemTaxation;
    }

    public static void setSystemTaxation(String systemTaxation) {
        Organization.systemTaxation = systemTaxation;
    }

}
