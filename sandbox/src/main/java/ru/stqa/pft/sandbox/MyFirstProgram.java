package ru.stqa.pft.sandbox;

public class MyFirstProgram {

  public static void main(String[] args) {
    hello("world");
    hello("user");
    hello("Victor");

    Square s = new Square(5);
    System.out.println("Площадь квадрата со стороной " + s.l + " = " + s.area());

    Rectangle r = new Rectangle(4, 6);
    System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " + r.area());

    double x1 = 2;
    double y1 = 2;
    double x2 = 5;
    double y2 = 5;
    System.out.println("Длина от точни P1 до точки P2" + " = "  + Math.sqrt((x1-x2) * (x1-x2) + (y1-y2) * (y1-y2)));

  }

  public static void hello(String somebody) {
    System.out.println("Hello, " + somebody + "!");

  }

  }
 /* public static void distance() {
    double x1 = 2;
    double y1 = 2;
    double x2 = 5;
    double y2 = 5;
    System.out.println("Длина от точни P1 до точки P2" + " = "  + ((x1-x2) * (x1-x2) + (y1-y2) * (y1-y2)));
    }*/



