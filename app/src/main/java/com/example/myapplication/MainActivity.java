
package com.example.myapplication;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public static int color = Color.BLUE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void buttomRed() {
        color = Color.RED;
    }

    public void buttomBlue() {
        color = Color.BLUE;
    }

    public void buttomGreen() {
        color = Color.GREEN;
    }
}
