package ru.stqa.pft.sandbox;

public class Point {

  double x;
  double y;

  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public double getX() {
    return x;
  }

  public double getY() {
    return y;
  }

  public double distanceTo(Point p2) {
    double sum = (this.x - p2.x) * (this.x - p2.x) + (this.y - p2.y) * (this.y - p2.y);
    return Math.sqrt(sum);
  }

}

