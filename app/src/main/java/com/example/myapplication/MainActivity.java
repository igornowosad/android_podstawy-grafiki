
package com.example.myapplication;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public static int color = Color.BLUE;
    public static boolean clear = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void buttonRed(View view) {
        color = Color.RED;
    }

    public void buttonBlue(View view) {
        color = Color.BLUE;
    }

    public void buttonGreen(View view) {
        color = Color.GREEN;
    }

    public void buttonClear(View view) {
        clear = true;
    }
}
