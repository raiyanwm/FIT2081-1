package com.fit2081.week11app;

public class Rectangle extends Shape {
    int left,top, width, height, color;

    public Rectangle(int left, int top, int width, int height, int color) {
        this.left = left;
        this.top = top;
        this.width = width;
        this.height = height;
        this.type =Shape.RECTANGLE;
        this.color = color;
    }
}
