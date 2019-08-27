package com.mypackage.cafmp;

import android.annotation.TargetApi;


import com.mypackage.cafmp.Data.AppData;
import com.mypackage.cafmp.Data.TaskData;
import com.mypackage.cafmp.Database.SqlHandler;


import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.stream.Stream;

@TargetApi(26)
public class MainActivityHelper {

    private SqlHandler sqlHandler = SqlHandler.getInstance();
    private ArrayList<TaskData> taskDataArrayList = sqlHandler.getAllValuesAsList();
    private LocalDate selected_Date;
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public MainActivityHelper() {
        selected_Date = LocalDate.now();
    }

    public Date getSelected_Date(){return new Date(selected_Date.getYear(),selected_Date.getMonthValue(),selected_Date.getDayOfMonth());}

    public String onDayChange(int day, int month, int year) {

        selected_Date = LocalDate.of(year,month,day);

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
