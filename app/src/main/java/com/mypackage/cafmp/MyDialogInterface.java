package com.mypackage.cafmp;
import android.content.DialogInterface;
import android.widget.TextView;

import com.mypackage.cafmp.Database.SqlHandler;

import java.sql.Date;


public class MyDialogInterface implements DialogInterface.OnClickListener {

    private SqlHandler sqlHandler = SqlHandler.getInstance();
    private String newTitle,whereTitle;
    private Date newDate,whereDate;
    private MainActivityHelper mainActivityHelper;
    private TextView textView;
    private String textView_str;

    public MyDialogInterface(MainActivityHelper mainActivityHelper, TextView textView_setApp,String str){
        this.mainActivityHelper = mainActivityHelper;
        this.textView = textView_setApp;
        this.textView_str = str;
    }


    @Override
    public void onClick(DialogInterface dialogInterface, int i) {

        if(i==DialogInterface.BUTTON_POSITIVE){

            if(newTitle!=null && whereTitle != null && newDate != null && whereDate != null){
                sqlHandler.updateDb(newTitle,newDate,whereTitle,whereDate);
                mainActivityHelper.updateDb();
                textView.setText(textView_str);
            }
        }
        else return ;
    }

    public void setUpdateValues(String newTitle, Date newDate,String whereTile,Date whereDate){
        this.newTitle = newTitle;
        this.newDate = newDate;
        this.whereTitle = whereTile;
        this.whereDate = whereDate;
    }


}
