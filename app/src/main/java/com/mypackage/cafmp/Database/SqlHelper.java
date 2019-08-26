package com.mypackage.cafmp.Database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mypackage.cafmp.Data.AppData;

public class SqlHelper extends SQLiteOpenHelper {


    public static final String DB_NAME = "Task_Db1";
    public static final String DB_TABLE_NAME = "task";
    public static final String columns_names[] = {"id", "title", "date"};

    public SqlHelper() {
        super(AppData.getContext(), DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table task(id INTEGER primary key autoincrement,title varchar,date TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public Cursor getAllValues(SQLiteDatabase sqLiteDatabase) {
        return sqLiteDatabase.query(DB_TABLE_NAME, columns_names, null, null, null, null, null);
    }


}
