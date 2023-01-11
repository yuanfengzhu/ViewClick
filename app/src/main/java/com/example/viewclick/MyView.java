package com.example.viewclick;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class MyView extends TextView {


    int count = 0;

    public MyView(Context context) {
        super(context);
    }


    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean a = super.onTouchEvent(event);
        Log.e("click", "MyView   onTouchEvent" + "   " + a);
        return a;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        boolean a = super.dispatchTouchEvent(event);
        Log.e("click", "MyView   dispatchTouchEvent" + "   " + a);
        return a;
    }
}
