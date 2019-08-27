package com.mypackage.cafmp;
import android.content.DialogInterface;

import com.mypackage.cafmp.Database.SqlHandler;

import java.sql.Date;


public class MyDialogInterface implements DialogInterface.OnClickListener {

    private boolean update = false;
    private SqlHandler sqlHandler = SqlHandler.getInstance();
    private String newTitle,whereTitle;
    private Date newDate,whereDate;

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {

        if(i==DialogInterface.BUTTON_POSITIVE){

            if(update && newTitle!=null && whereTitle != null && newDate != null && whereDate != null){
                sqlHandler.updateDb(newTitle,newDate,whereTitle,whereDate);
            }
        }
    }

    public void setUpdateValues(String newTitle, Date newDate,String whereTile,Date whereDate){
        this.newTitle = newTitle;
        this.newDate = newDate;
        this.whereTitle = whereTile;
        this.whereDate = whereDate;
    }

    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }
}
