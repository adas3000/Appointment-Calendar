package com.mypackage.cafmp;

import android.util.Log;

import com.mypackage.cafmp.Data.AppData;
import com.mypackage.cafmp.Data.TaskData;
import com.mypackage.cafmp.Database.SqlHelper;

import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import org.junit.Assert;

import java.sql.Date;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.stream.Stream;

import static junit.framework.TestCase.assertEquals;

public class MainActivityHelperTest {

    private MainActivityHelper mainActivityHelper = new MainActivityHelper();

    @Before
    public void setup(){
        mainActivityHelper.addAppoinment("task1",Date.valueOf("2019-08-27"));
        mainActivityHelper.addAppoinment("task2",Date.valueOf("2019-08-28"));
        mainActivityHelper.addAppoinment("task3",Date.valueOf("2019-08-29"));
    }


    @Test
    public void ifEqualsThenOk(){

        String str_1 = mainActivityHelper.onDayChange(27,8,2019);
        String str_2 = mainActivityHelper.onDayChange(28,8,2019);
        String str_3 = mainActivityHelper.onDayChange(29,8,2019);




        assertEquals("task1",str_1);
        assertEquals("task2",str_2);
        assertEquals("task3",str_3);



    }

    @Test
    public void getDataAndIfEqualsAndNoExceptnioThenOk(){


        ArrayList<TaskData> taskDataArrayList = mainActivityHelper.getTaskDataArrayList();

        for(TaskData taskData : taskDataArrayList){
            Log.d("task_name:",taskData.getStr_Task());
            Log.d("task_date:",taskData.getDate().toString());
        }

        assertEquals("task1",taskDataArrayList.get(0).getStr_Task());
        assertEquals("2019-08-27",taskDataArrayList.get(0).getDate());
    }


    @Test
    public void streamTestIfEqualsThenOk(){
        ArrayList<TaskData> taskDataArrayList = mainActivityHelper.getTaskDataArrayList();
        Stream<TaskData> taskDataStream = taskDataArrayList.stream();
        int day = 27,month = 8,year = 2019;


        GregorianCalendar gregorianCalendar = new GregorianCalendar(year,month-1,day);

        Date date = new Date(gregorianCalendar.getTime().getTime());

        Log.d("date",date.toString());

        Stream<TaskData> filteredArray = taskDataStream
                .filter(g -> g.getDate().toString().equals(date.toString()));


        assertEquals(1,filteredArray.count());
    }


    @Test
    public void ifStringEqualsThenOk(){
        ArrayList<TaskData> taskDataArrayList = mainActivityHelper.getTaskDataArrayList();
        Stream<TaskData> taskDataStream = taskDataArrayList.stream();
        int day = 27,month = 8,year = 2019;
        GregorianCalendar gregorianCalendar = new GregorianCalendar(year,month-1,day);

        Date date = new Date(gregorianCalendar.getTime().getTime());

       String result = taskDataStream
                .filter(g -> g.getDate().toString().equals(date.toString()))
               .findFirst().get().getStr_Task();


        assertEquals("task1",result);

    }





    @After
    public void onFinish(){
        AppData.getContext().deleteDatabase(SqlHelper.DB_NAME);
    }



}
