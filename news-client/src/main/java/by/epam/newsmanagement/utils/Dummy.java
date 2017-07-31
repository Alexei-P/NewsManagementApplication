package by.epam.newsmanagement.utils;

// import oracle.jdbc.OracleDriver;

import oracle.jdbc.driver.OracleDriver;

public class Dummy {
  public static void main(String[] args) {
    Class clazz = null;
    try {
      clazz = Class.forName("oracle.jdbc.driver.OracleDriver");
    } catch (ClassNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    System.out.println(clazz);
    OracleDriver oracleDriver = null;
    try {
      clazz.newInstance();
    } catch (InstantiationException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
