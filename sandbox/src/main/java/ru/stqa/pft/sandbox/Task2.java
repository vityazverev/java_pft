package ru.stqa.pft.sandbox;

/**
 * Created by Виктор on 15.03.2017.
 */
public class Task2 {

  public static void main(String[] args) {
    Point p1 = new Point(1, 2);
    Point p2 = new Point(5, 6);
    System.out.println("Расстояние между двумя точками = " + distance(p1, p2));
  }


    public static double distance(Point p1, Point p2){
      return Math.sqrt(( p1.x - p2.x) * ( p1.x - p2.x) + ( p1.x - p2.x) * ( p1.x -  p2.x));
    }

}