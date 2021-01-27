package com.gmail.nedgladstone.shapes;

public class Rectangle {
    private double height;
    private double width;

    public Rectangle(double height, double width) {
        if ((height < 0.0) || (width < 0.0)) {
            throw new IllegalArgumentException("Dimensions may not be negative");
        }
        this.height = height;
        this.width = width;
    }

    public String toString() {
        return "Rectangle: height=" + height + ", width=" + width;
    }

    public double getArea() {
        return height * width;
    }
}
