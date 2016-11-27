package com.company;

public class Vector2D {
    double x, y;

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2D add(Vector2D v) {
        return new Vector2D(v.x + x, v.y + y);
    }

    public Vector2D scale(double d) {
        return new Vector2D(x * d, y * d);
    }

    public Vector2D rotate(double angle) {
        return new Vector2D(x * Math.cos(angle) + y * Math.sin(angle), x * (-Math.sin(angle)) + y * Math.cos(angle));
    }

    public double length() {
        return Math.sqrt(x * x + y * y);
    }

    @Override
    public String toString() {
        return "Vector2D{" + "x=" + x + ", y=" + y + '}';
    }

    public Vector2D normalize() {
        return new Vector2D(x / length(), y / length());
    }
}
