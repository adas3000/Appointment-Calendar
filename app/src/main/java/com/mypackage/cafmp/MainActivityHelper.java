package com.mypackage.cafmp;

import android.annotation.TargetApi;
import android.widget.CalendarView;

import com.mypackage.cafmp.Data.AppData;
import com.mypackage.cafmp.Data.TaskData;
import com.mypackage.cafmp.Database.SqlHandler;

import java.sql.Date;
import java.util.ArrayList;
import java.util.stream.Stream;

@TargetApi(24)
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
                .filter(x->x.getDate().toString().equals(selected_Date.toString()))
                .findFirst()
                .orElse(null);

        if(taskData==null)
            return AppData.getContext().getResources().getString(R.string.noappoints);

        return taskData.getStr_Task();
    }


    public void addAppoinment(String title, Date date) {
        sqlHandler.insertDataIntoDb(title, date);
    }

    public ArrayList<TaskData> getTaskDataArrayList(){return sqlHandler.getAllValuesAsList();}//only for tests



    public void addAppoinment(String title, int year, int month, int day) {
        sqlHandler.insertDataIntoDb(title, new Date(year, month, day));
    }


}
