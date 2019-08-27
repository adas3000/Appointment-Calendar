package com.mypackage.cafmp;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;

import androidx.core.app.NotificationCompat;

import com.mypackage.cafmp.Data.AppData;

@TargetApi(26)
public class MyNotifications {


    public static void showNotification(String title, String content) {

        String chanel_str = "chanel1";

        NotificationChannel channel1 = new NotificationChannel(chanel_str, title, NotificationManager.IMPORTANCE_HIGH);
        channel1.setDescription(content);

        NotificationManager manager = AppData.getContext().getSystemService(NotificationManager.class);
        manager.createNotificationChannel(channel1);


        Notification notification = new NotificationCompat.Builder(AppData.getContext(), chanel_str)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(title)
                .setContentText(content)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_REMINDER)
                .build();

        manager.notify(1, notification);
    }


}
