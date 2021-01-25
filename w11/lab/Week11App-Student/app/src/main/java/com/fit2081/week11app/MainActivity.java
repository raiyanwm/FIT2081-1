package com.fit2081.week11app;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{
    CustomView view;
    int shape2Draw = Shape.CIRCLE;
//    int shape2Draw = Shape.RECTANGLE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view = findViewById(R.id.myView);
        view.addShape(new Circle(10,20,100));
        view.addShape(new Rectangle(10,20,100,100));
        view.clearShapes();
    }
}
