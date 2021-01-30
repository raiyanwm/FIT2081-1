package com.fit2081.week11app;

public class Circle extends Shape {
    int x, y, r,color;

    public Circle(int x, int y, int r, int color) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.type=Shape.CIRCLE;
        this.color = color;
    }
}
