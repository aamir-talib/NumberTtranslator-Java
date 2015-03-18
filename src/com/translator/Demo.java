package com.translator;

public class Demo {

  public static void main(String[] args) {
    Object[] params = {-205, 0, 5, 9, 10, 11, 15, 19, 29, 30, 78, 90, 99, 100, 555, 999, 1000, 1001, 1100, 4587541, -4587541, "4565465465465"};
    
    for(Object p:params){
      System.out.println(p + ": " + NumberTranslator.translate(p.toString()));
    }
  }

  
}
