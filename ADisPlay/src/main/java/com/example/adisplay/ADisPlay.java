package com.example.adisplay;

import android.util.Log;

import com.example.interfacemodule.Display;
import com.google.auto.service.AutoService;

@AutoService(Display.class)
public class ADisPlay implements Display {

    @Override
    public void display() {
        Log.e("test diaplay", "display A");
    }
}
