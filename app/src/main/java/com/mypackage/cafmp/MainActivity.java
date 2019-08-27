package com.mypackage.cafmp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mypackage.cafmp.Data.AppData;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;

@TargetApi(26)
public class MainActivity extends AppCompatActivity {

    private MainActivityHelper mainActivityHelper = new MainActivityHelper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button button = findViewById(R.id.button_createAppoinment);
        button.setTextColor(Color.WHITE);
        button.setBackgroundColor(Color.rgb(153,0,76));


        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.rgb(233, 30, 99)));


        TextView textView_appoinment = findViewById(R.id.textView_appoinment);
        TextView selected_Date = findViewById(R.id.textView_Date);
        EditText editText_newAppoinment = findViewById(R.id.editText);


        CalendarView calendarView = findViewById(R.id.calendarView);


        selected_Date.setText(mainActivityHelper.getSelected_Date().toString());


        LocalDate localDate = LocalDate.now();
        textView_appoinment.setText(mainActivityHelper.onDayChange(localDate.getDayOfMonth(), localDate.getMonthValue() - 1
                , localDate.getYear() - 1900));
        selected_Date.setText(mainActivityHelper.getSelected_Date().toString());

        if(!textView_appoinment.getText().toString().equals(AppData.getContext().getResources().getString(R.string.noappoints))) {
                MyNotifications.showNotification("You have something to do today!",
                        textView_appoinment.getText().toString());
        }
        else {
            MyNotifications.showNotification("No tasks for today:(",
                    textView_appoinment.getText().toString());
        }



        calendarView.setOnDateChangeListener((calendarView1, i, i1, i2) -> {

            String task = mainActivityHelper.onDayChange(i2, i1, i - 1900);
            textView_appoinment.setText(task);
            selected_Date.setText(mainActivityHelper.getSelected_Date().toString());

        });

        button.setOnClickListener(view -> {
            String title = editText_newAppoinment.getText().toString();
            String appText = AppData.getContext().getResources().getString(R.string.noappoints);
            String current_App = textView_appoinment.getText().toString();


            if (title.length() < 3) {
                Toast.makeText(AppData.getContext(), "Write at least 3 characters", Toast.LENGTH_SHORT).show();
                return;
            } else if (!title.equals(appText) && !current_App.equals(appText)) {
                MyDialogInterface myDialogInterface = new MyDialogInterface(mainActivityHelper,textView_appoinment,title);
                myDialogInterface.setUpdateValues(title, mainActivityHelper.getSelected_Date(), current_App, mainActivityHelper.getSelected_Date());
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Are you sure?This operation will change appointment title.")
                        .setPositiveButton("Yes", myDialogInterface)
                        .setNegativeButton("No", myDialogInterface).show();



            } else if (current_App.equals(appText)) {
                mainActivityHelper.addAppoinment(title, mainActivityHelper.getSelected_Date());
                textView_appoinment.setText(title);
            }

        });


    }

}
