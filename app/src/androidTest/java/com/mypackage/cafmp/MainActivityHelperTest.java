package com.mypackage.cafmp;

import android.util.Log;

import com.mypackage.cafmp.Data.AppData;
import com.mypackage.cafmp.Data.TaskData;
import com.mypackage.cafmp.Database.SqlHelper;

import org.junit.After;
import org.junit.Before;

import org.junit.Test;

import java.sql.Date;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.stream.Stream;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class MainActivityHelperTest {

    private MainActivityHelper mainActivityHelper = new MainActivityHelper();



    @Before
    public void setup(){
        mainActivityHelper.addAppoinment("task1",Date.valueOf("2019-08-27"));
        mainActivityHelper.addAppoinment("task2",Date.valueOf("2019-08-28"));
        mainActivityHelper.addAppoinment("task3",Date.valueOf("2019-08-29"));
    }
    @Test
    public void dateTestifEqualsThenOk(){

        Date date = mainActivityHelper.getSelected_Date();
        assertTrue(date.toString().equals("2019-08-27"));
    }


    @Test
    public void ifEqualsThenOk(){

        String str_1 = mainActivityHelper.onDayChange(27,8-1,2019-1900);
        mainActivityHelper.getSelected_Date().toString();
        String str_2 = mainActivityHelper.onDayChange(28,8-1,2019-1900);
        mainActivityHelper.getSelected_Date().toString();
        String str_3 = mainActivityHelper.onDayChange(29,8-1,2019-1900);
        mainActivityHelper.getSelected_Date().toString();

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
        assertEquals("2019-08-27",taskDataArrayList.get(0).getDate().toString());
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


    @Test
    public void ifTodayThenOk(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.of(2019,8,27);
        Log.d("date",dtf.format(localDate));

        assertEquals(27,localDate.getDayOfMonth());
        assertEquals(8,localDate.getMonth().getValue());
        assertEquals(2019,localDate.getYear());
        assertEquals("2019-08-27",localDate.toString());
    }

    @Test
    public void ifTodayTaskEqualsThenOk(){

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.now();

        String task = mainActivityHelper.onDayChange(localDate.getDayOfMonth(),localDate.getMonthValue(),localDate.getYear());

        assertEquals("task1",task);
    }




    @After
    public void onFinish(){
        AppData.getContext().deleteDatabase(SqlHelper.DB_NAME);
    }

}
