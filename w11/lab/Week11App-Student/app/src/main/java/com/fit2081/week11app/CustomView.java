package com.fit2081.week11app;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CustomView extends View {
    public Canvas canvas;

    ArrayList<Shape> shapes = new ArrayList<>();


    public CustomView(Context context) {
        super(context);
        invalidate();
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setupPaint();
        invalidate();


    }

    // defines paint and canvas
    private Paint drawPaint;

    // Setup paint with color and stroke styles
    private void setupPaint() {
        drawPaint = new Paint();
        drawPaint.setColor(Color.GREEN);
        drawPaint.setAntiAlias(true);
        drawPaint.setStrokeWidth(5);
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (int i = 0; i < shapes.size(); i++) {
           int shapeId = shapes.get(i).type;
            switch (shapeId) {
                case Shape.CIRCLE:
                    Circle c = (Circle) shapes.get(i);
                    canvas.drawCircle(c.x, c.y, c.r, drawPaint);
                    break;
                case Shape.RECTANGLE:
                    Rectangle r = (Rectangle) shapes.get(i);
                    canvas.drawRect(r.left, r.top, r.width, r.height, drawPaint);
                    break;
            }
        }



    }

    public void setShapes(ArrayList<Shape> newShapes) {
        this.shapes = newShapes;
        invalidate();
    }

    public void addShape(Shape newShape) {
        this.shapes.add(newShape);
        invalidate();
    }

    public void clearShapes(){
        shapes.clear();
        invalidate();
    }




}
