package ru.stqa.pft.sandbox;

/**
 * Created by Виктор on 09.04.2017.
 */


public class Collections {

  public static void main(String[] args){
    String[] langs = {"Java", "C#", "Pyton", "PHP"};

    for (String l : langs){
      System.out.println("Я хочу выучить " + l);
    }
  }
}
