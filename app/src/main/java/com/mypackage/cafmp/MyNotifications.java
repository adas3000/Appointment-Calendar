package com.mypackage.cafmp;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;

import androidx.core.app.NotificationCompat;

import com.mypackage.cafmp.Data.AppData;

@TargetApi(26)
public class MyNotifications {

    private NotificationChannel channel1;
    private NotificationManager manager;
    private Notification notification;
    private String chanel_str = "chanel1";
    private String title, content;

    public MyNotifications(String title, String content) {
        this.title = title;
        this.content = content;

        channel1 = new NotificationChannel(chanel_str, this.title, NotificationManager.IMPORTANCE_HIGH);
        channel1.setDescription(this.content);

        manager = AppData.getContext().getSystemService(NotificationManager.class);
        manager.createNotificationChannel(channel1);
    }


    public void showNotification() {

        notification = new NotificationCompat.Builder(AppData.getContext(),chanel_str)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(title)
                .setContentText(content)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_REMINDER)
                .build();
        
        manager.notify(1,notification);
    }


}
