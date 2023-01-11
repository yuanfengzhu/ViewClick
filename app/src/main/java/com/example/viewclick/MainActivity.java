
package com.example.viewclick;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.example.interfacemodule.Display;

import org.json.JSONArray;
import org.json.JSONException;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.ServiceLoader;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends Activity {
private CircleProgressView progressView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressView= findViewById(R.id.pv);
        progressView.setMaxStepNum(100);
        progressView.update(30);
    }


    final static int MAXIMUM_CAPACITY = 1 << 30;

    public static class Person {
        public String name;
        public String label;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Person person = (Person) o;
            return Objects.equals(name, person.name) &&
                    Objects.equals(label, person.label);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, label);
        }
    }

    public static void main(String[] args) throws JSONException {

//        System.out.println(getDataString(list));
//        String  a="++++++++";
//        System.out.println(getEncryptionNo("rtyui"));
//      AT at=new AT();
//      System.out.println(at.a);
//      System.out.println(at.b);
//      System.out.println(at.c);
//      System.out.println(at.d);
//        sort();
       String a="123456789123456.1";
       System.out.println(Long.parseLong(a));
    }

    public static  class  AT{
        boolean a;
        int b;
        double c;
        String d;
    }

    public static void prln(List<A> a) {
        for (A c : a) {
            System.out.println(c);
        }
    }


    public static class B extends A {

    }


    public static abstract class A {
        int a;

        @Override
        public String toString() {
            return "A{" +
                    "a=" + a +
                    '}';
        }
    }



    private static boolean setCheck(List<String> beanList) {

        boolean hasFind = false;
        for (String id : beanList) {
                hasFind = true;
                break;
        }
        return hasFind;
    }

}
