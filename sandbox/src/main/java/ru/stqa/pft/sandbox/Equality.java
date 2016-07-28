package ru.stqa.pft.sandbox;

/**
 * Created by natkin on 28.07.2016.
 */
public class Equality {

  public static void main (String[] args){
    String s1 = "firefox";
    String s2 = new String(s1);


    System.out.println(s1 == s2);
    System.out.println(s1.equals(s2));
  }
}
