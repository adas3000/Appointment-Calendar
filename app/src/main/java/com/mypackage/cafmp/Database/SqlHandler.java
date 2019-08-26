package com.mypackage.cafmp.Database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.mypackage.cafmp.Data.AppData;
import com.mypackage.cafmp.Data.TaskData;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

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

    public List<TaskData> getAllValuesAsList() {

        try (SQLiteDatabase db = sqlHelper.getReadableDatabase()) {
            Cursor cursor = sqlHelper.getAllValues(db);

            List<TaskData> arrayList = new ArrayList<>();

            if (cursor.moveToFirst()) {
                do {
                    String title = cursor.getString(cursor.getColumnIndex(SqlHelper.columns_names[1]));
                    String str_date = cursor.getString(cursor.getColumnIndex(SqlHelper.columns_names[1]));
                    Date date = Date.valueOf(str_date);
                    arrayList.add(new TaskData(title, date));
                } while (cursor.moveToNext());
            }

            return arrayList;
        }
    }

}
