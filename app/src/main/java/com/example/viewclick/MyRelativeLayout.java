package com.example.viewclick;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

public class MyRelativeLayout extends RelativeLayout {

    int count = 0;

    public MyRelativeLayout(Context context) {
        super(context);
    }

    public MyRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean a = super.onTouchEvent(event);
        Log.e("click", "MyRelativeLayout   onTouchEvent" + "   " + a);
        return a;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean a = super.dispatchTouchEvent(ev);
        Log.e("click", "MyRelativeLayout   dispatchTouchEvent" + "   " + a);
        return a;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean a = super.onInterceptTouchEvent(ev);
        Log.e("click", "MyRelativeLayout   onInterceptTouchEvent" + "   " + a);
        return a;
    }
}
