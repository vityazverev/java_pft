package ru.stqa.pft.sandbox;

public class Task2 {

  public static void main(String[] args) {
    Point p1 = new Point(1, 1);
    Point p2 = new Point(5, 6);

    System.out.println("Расстояние между двумя точками с координатами (x1 = " + p1.getX()
            + ", y1 = " + p1.getY() + "; x2 = " + p2.getX() +
            ", y2 = " + p2.getY() + ") = " + p1.distanceTo(p2));
  }

}
