package com.mypackage.cafmp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;

import com.mypackage.cafmp.Data.AppData;

import java.sql.Date;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private MainActivityHelper mainActivityHelper = new MainActivityHelper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Button button = findViewById(R.id.button_createAppoinment);
        button.setTextColor(Color.WHITE);
        button.setBackgroundColor(Color.RED);


        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.rgb(233, 30, 99)));


        TextView textView_appoinment = findViewById(R.id.textView_appoinment);
        TextView selected_Date = findViewById(R.id.textView_Date);
        EditText editText_newAppoinment = findViewById(R.id.editText);


        CalendarView calendarView = findViewById(R.id.calendarView);


        selected_Date.setText(mainActivityHelper.getSelected_Date().toString());


        calendarView.setOnDateChangeListener((calendarView1, i, i1, i2) -> {

            String task = mainActivityHelper.onDayChange(i2, i1 , i-1900);
            textView_appoinment.setText(task);
            selected_Date.setText(mainActivityHelper.getSelected_Date().toString());

        });

        button.setOnClickListener(view -> {
            String title = editText_newAppoinment.getText().toString();

            if(title.isEmpty()) return;

            if (textView_appoinment.getText().toString()
                    .equals(AppData.getContext().getResources().getString(R.string.noappoints))){
                mainActivityHelper.addAppoinment(title, mainActivityHelper.getSelected_Date());
            }

        });


    }
}
