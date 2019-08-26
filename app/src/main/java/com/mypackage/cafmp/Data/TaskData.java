package com.mypackage.cafmp.Data;

import java.sql.Date;

public class TaskData {

    private String str_Task;
    private Date date;

    public TaskData(String str_Task,Date date){
        this.str_Task = str_Task;
        this.date = date;
    }

    public String getStr_Task(){return str_Task;}
    public Date getDate(){return date;}
}
