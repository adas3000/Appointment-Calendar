package com.mypackage.cafmp;

import com.mypackage.cafmp.Data.AppData;
import com.mypackage.cafmp.Database.SqlHelper;

import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import org.junit.Assert;

import java.sql.Date;
import java.util.ArrayList;

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



    @After
    public void onFinish(){
        AppData.getContext().deleteDatabase(SqlHelper.DB_NAME);
    }



}
