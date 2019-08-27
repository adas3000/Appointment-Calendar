package com.mypackage.cafmp;


import com.mypackage.cafmp.Data.AppData;
import com.mypackage.cafmp.Data.TaskData;
import com.mypackage.cafmp.Database.SqlHandler;
import com.mypackage.cafmp.Database.SqlHelper;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class SqlTests {

    private SqlHandler sqlHandler;
    ArrayList<TaskData> taskDataArrayList;

    @Before
    public void setup() {
        sqlHandler = SqlHandler.getInstance();
        sqlHandler.insertDataIntoDb("Task1", Date.valueOf("2019-08-26"));
        sqlHandler.insertDataIntoDb("Task2", Date.valueOf("2019-08-27"));
        sqlHandler.insertDataIntoDb("Task3", Date.valueOf("2019-08-28"));
        sqlHandler.insertDataIntoDb("Task4", Date.valueOf("2019-08-29"));
        taskDataArrayList = sqlHandler.getAllValuesAsList();
    }


    @Test
    public void ifNoExceptionWhilePuttingDataThenOk() {
        sqlHandler.insertDataIntoDb("Task1", Date.valueOf("2019-08-26"));
    }

    @Test
    public void ifEqualsThenOk() {
        assertEquals("Task1", taskDataArrayList.get(0).getStr_Task());
        assertEquals("Task2", taskDataArrayList.get(1).getStr_Task());
        assertEquals("Task3", taskDataArrayList.get(2).getStr_Task());
        assertEquals("Task4", taskDataArrayList.get(3).getStr_Task());

        assertEquals("2019-08-26", taskDataArrayList.get(0).getDate().toString());
        assertEquals("2019-08-27", taskDataArrayList.get(1).getDate().toString());
        assertEquals("2019-08-28", taskDataArrayList.get(2).getDate().toString());
        assertEquals("2019-08-29", taskDataArrayList.get(3).getDate().toString());
    }

    @Test
    public void ifEveryDataEqualsThenok() {


        String str_date = taskDataArrayList.get(0).getDate().toString();

        assertEquals("2019-08-26", str_date);

      /*  assertEquals(26,day);
        assertEquals(8,month);
        assertEquals(2019,year);*/
    }


    @Test
    public void updateTestIfEqualsAndAfterUpdateEqualsThenOk() {


        assertEquals("Task1",taskDataArrayList.get(0).getStr_Task());

        sqlHandler.updateDb("NewTask",Date.valueOf("2019-08-26"),"Task1",
                Date.valueOf("2019-08-26"));

        taskDataArrayList = sqlHandler.getAllValuesAsList();
        assertEquals("NewTask",taskDataArrayList.get(0).getStr_Task());



    }


    @After
    public void onFinish() {
        AppData.getContext().deleteDatabase(SqlHelper.DB_NAME);
    }


}
