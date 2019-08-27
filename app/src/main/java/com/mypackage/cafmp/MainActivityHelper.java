package com.mypackage.cafmp;

import android.annotation.TargetApi;
import android.util.Log;
import android.widget.CalendarView;
import android.widget.TextView;

import com.mypackage.cafmp.Data.AppData;
import com.mypackage.cafmp.Data.TaskData;
import com.mypackage.cafmp.Database.SqlHandler;

import java.sql.Date;
import java.text.DateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Stream;

@TargetApi(26)
public class MainActivityHelper {

    private SqlHandler sqlHandler = SqlHandler.getInstance();
    private ArrayList<TaskData> taskDataArrayList = sqlHandler.getAllValuesAsList();
    private Date selected_Date;


    public MainActivityHelper() {
        long milis = System.currentTimeMillis();
        selected_Date = new Date(milis);
    }

    public Date getSelected_Date(){return selected_Date;}

    public String onDayChange(int day, int month, int year) {

        selected_Date = new Date(year,month,day);


        Stream<TaskData> taskDataStream = taskDataArrayList.stream();

        TaskData taskData = taskDataStream
                .filter(x->x.getDate().equals(selected_Date))
                .findFirst()
                .orElse(null);

        if(taskData==null)
            return AppData.getContext().getResources().getString(R.string.noappoints);

        return taskData.getStr_Task();
    }

    public void updateDb(){
        taskDataArrayList = sqlHandler.getAllValuesAsList();
    }


    public void addAppoinment(String title, Date date) {
        sqlHandler.insertDataIntoDb(title, date);
        taskDataArrayList = sqlHandler.getAllValuesAsList();
    }


    public ArrayList<TaskData> getTaskDataArrayList(){return sqlHandler.getAllValuesAsList();}//only for tests



    public void addAppoinment(String title, int year, int month, int day) {
        sqlHandler.insertDataIntoDb(title, new Date(year, month, day));
        taskDataArrayList = sqlHandler.getAllValuesAsList();
    }



}
