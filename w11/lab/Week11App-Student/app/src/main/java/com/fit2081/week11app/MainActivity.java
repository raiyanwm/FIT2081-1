package com.fit2081.week11app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;
import androidx.core.view.ScaleGestureDetectorCompat;

import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener{
    CustomView view;
    int shape2Draw = Shape.CIRCLE;
    final String DEBUG = "DRAWING";
    private GestureDetectorCompat myGestureDetector;
    private ScaleGestureDetector myScaleDetector;

    int x,y;
    int r = 100, w = 100, h = 150;

    int initialX, initialY;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view = findViewById(R.id.myView);

        GestureListener gestureListener = new GestureListener();
        myGestureDetector = new GestureDetectorCompat(this, gestureListener);
        myGestureDetector.setOnDoubleTapListener(gestureListener); //do we really need this one?

        ScaleListener scaleListener = new ScaleListener();
        myScaleDetector = new ScaleGestureDetector(this, scaleListener);


        view.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event){
        x = (int) event.getX();
        y = (int) event.getY();
        myGestureDetector.onTouchEvent(event);
        myScaleDetector.onTouchEvent(event);
        Log.d(DEBUG,"onTouch: " + x + " " + y);
        return true;
    }

    private class GestureListener extends GestureDetector.SimpleOnGestureListener{
        @Override
        public void onLongPress(MotionEvent e) {
            view.clearShapes();
            Log.d(DEBUG,"Cleared Shapes");
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            switch (shape2Draw) {
                case Shape.CIRCLE:
                    shape2Draw = Shape.RECTANGLE;
                    Toast.makeText(getApplicationContext(),"Rectangle", Toast.LENGTH_SHORT).show();
                    break;
                case Shape.RECTANGLE:
                    shape2Draw = Shape.CIRCLE;
                    Toast.makeText(getApplicationContext(),"Circle", Toast.LENGTH_SHORT).show();
                    break;
            }
            Log.d(DEBUG,"Shape Changed");
            return true;
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            draw();
            Log.d(DEBUG,"Drew Shape");
            return true;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            draw();
            Log.d(DEBUG,"Drawing Continuously");
            return true;
        }


        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            view.changeColour();
            Log.d(DEBUG,"Change Color");
            return true;
        }
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener{
        @Override
        public boolean onScaleBegin(ScaleGestureDetector detector) {
            initialX = x;
            initialY = y;
            Log.d(DEBUG,"onScale Begin");
            return true;
        }

        @Override
        public void onScaleEnd(ScaleGestureDetector detector) {
            switch (shape2Draw){
                case Shape.CIRCLE:
                    r *= detector.getScaleFactor();
                    break;
                case Shape.RECTANGLE:
                    w *= detector.getScaleFactor();
                    h *= detector.getScaleFactor();
                    break;
            }
            x = initialX;
            y = initialY;
            draw();
            Log.d(DEBUG,"onScale End");
        }

    }

    private void draw(){
        switch (shape2Draw){
            case Shape.CIRCLE:
                view.addShape(new Circle(x, y, r, view.getColor()));
                break;
            case Shape.RECTANGLE:
                view.addShape(new Rectangle(x,y,w,h, view.getColor()));
                break;
        }
    }
}
