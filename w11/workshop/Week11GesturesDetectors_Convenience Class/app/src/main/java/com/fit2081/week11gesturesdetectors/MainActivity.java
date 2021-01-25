
package com.fit2081.week11gesturesdetectors;

        import androidx.appcompat.app.AppCompatActivity;
        import androidx.core.view.GestureDetectorCompat;

        import android.os.Bundle;
        import android.view.GestureDetector;
        import android.view.MotionEvent;
        import android.view.ScaleGestureDetector;
        import android.view.View;
        import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener{

    TextView tV;
    private GestureDetectorCompat mDetector;
    private ScaleGestureDetector mScaleDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tV = findViewById(R.id.textview_id);

        MyScaleListener MyScaleListener=new MyScaleListener();
        mScaleDetector = new ScaleGestureDetector(this, MyScaleListener);

        MyGestureListener myGestureListener = new MyGestureListener();
        mDetector = new GestureDetectorCompat(this, myGestureListener);
        mDetector.setOnDoubleTapListener(myGestureListener);

        View myLayout = findViewById(R.id.myLayout);
        myLayout.setOnTouchListener(this);


    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        mDetector.onTouchEvent(event);
        mScaleDetector.onTouchEvent(event);

        return true;
    }

    private class MyGestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            tV.setText("onSingleTapConfirmed");
            return true;
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            tV.setText("onDoubleTap");
            return true;
        }

        @Override
        public boolean onDoubleTapEvent(MotionEvent e) {
            tV.setText("onDoubleTapEvent");

            return true;
        }

        @Override
        public boolean onDown(MotionEvent e) {
            tV.setText("onDown");

            return true;
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            tV.setText("onSingleTapUp");

            return true;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            tV.setText("onScroll");

            return true;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            tV.setText("onLongPress");

        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            tV.setText("onFling");

            return true;
        }

    }

    private class MyScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener{
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            tV.setText("onScale");

            return true;
        }

        @Override
        public boolean onScaleBegin(ScaleGestureDetector detector) {
            tV.setText("onScaleBegin");

            return true;
        }

        @Override
        public void onScaleEnd(ScaleGestureDetector detector) {
            tV.setText("onScaleEnd");

        }
    }


}

