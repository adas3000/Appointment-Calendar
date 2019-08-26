package com.mypackage.cafmp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CalendarView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Calendar calendar = Calendar.getInstance();

        Button button = findViewById(R.id.button_createAppoinment);
        button.setTextColor(Color.WHITE);
        button.setBackgroundColor(Color.RED);


        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.rgb(233, 30, 99)));


        CalendarView calendarView = findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener((calendarView1, i, i1, i2) -> {



        });


    }
}
