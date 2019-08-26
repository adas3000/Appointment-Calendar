package com.mypackage.cafmp.Database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.mypackage.cafmp.Data.AppData;

import java.sql.Date;

public class SqlHandler {


    private static final SqlHandler instance = new SqlHandler();

    public static SqlHandler getInstance() {
        return instance;
    }

    private SqlHelper sqlHelper = new SqlHelper();

    private SqlHandler() {
    }


    public void insertDataIntoDb(String title, Date date) {

        try (SQLiteDatabase db = sqlHelper.getWritableDatabase()) {

            ContentValues contentValues = new ContentValues();
            contentValues.put(SqlHelper.columns_names[1], title);
            contentValues.put(SqlHelper.columns_names[2], date.toString());
            db.insert(SqlHelper.DB_TABLE_NAME, null, contentValues);
        }
    }

    public Cursor getAllValues(){
        try(SQLiteDatabase db = sqlHelper.getReadableDatabase()){
            return sqlHelper.getAllValues(db);
        }
    }

}
