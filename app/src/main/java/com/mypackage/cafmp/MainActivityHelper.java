package com.mypackage.cafmp;

import android.annotation.TargetApi;
import android.widget.CalendarView;

import com.mypackage.cafmp.Data.TaskData;
import com.mypackage.cafmp.Database.SqlHandler;

import java.util.ArrayList;
import java.util.stream.Stream;

@TargetApi(24)
public class MainActivityHelper {

    private SqlHandler sqlHandler = SqlHandler.getInstance();
    private ArrayList<TaskData> taskDataArrayList = sqlHandler.getAllValuesAsList();


    public MainActivityHelper(){}


    public String onDayChange(CalendarView calendarView,int day,int month,int year){


        Stream<TaskData> taskDataStream = taskDataArrayList.stream();
        taskDataStream
                .filter(g->g.getDate().getDay()==day)
                .filter(g->g.getDate().getMonth()==month)
                .filter(g->g.getDate().getYear()==year);

        return taskDataStream.findFirst().toString();
    }

    




}
