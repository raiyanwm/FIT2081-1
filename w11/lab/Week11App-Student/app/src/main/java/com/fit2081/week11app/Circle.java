package com.fit2081.week11app;

public class Circle extends Shape {
    int x, y, r;

    public Circle(int x, int y, int r) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.type=Shape.CIRCLE;
    }
}
