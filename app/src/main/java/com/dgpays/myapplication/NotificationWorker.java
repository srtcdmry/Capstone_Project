package com.dgpays.myapplication;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;


import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.dgpays.myapplication.ui.bag.BagFragment;

import java.util.concurrent.TimeUnit;


public class NotificationWorker extends Worker {
    private static final String WORK_RESULT = "work_result";
    public NotificationWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }
    @NonNull
    @Override
    public Result doWork() {
        Data taskData = getInputData();
        String taskDataString = taskData.getString(BagFragment.MESSAGE_STATUS);
        showNotification("Products in the basket\n", taskDataString != null ? taskDataString : "Please click to complete the shopping !");
        Data outputData = new Data.Builder().putString(WORK_RESULT, "Jobs Finished").build();
            return Result.success(outputData);

    }

    public static void oneOfRequest(){
        OneTimeWorkRequest oneTimeWorkRequest = new OneTimeWorkRequest.Builder(NotificationWorker.class)
                .setInitialDelay(10, TimeUnit.SECONDS)
                .setConstraints(setCons())
                .build();

        WorkManager.getInstance().enqueue(oneTimeWorkRequest);

    }

    public static  void periodicRequest(){
        PeriodicWorkRequest periodicWorkRequest = new PeriodicWorkRequest.Builder(NotificationWorker.class,10,TimeUnit.SECONDS)
                .setInitialDelay(5,TimeUnit.SECONDS)
                .setConstraints(setCons())
                .build();

        WorkManager.getInstance().enqueueUniquePeriodicWork("periodic", ExistingPeriodicWorkPolicy.REPLACE, periodicWorkRequest);
    }

    public static void cancelPeriodicRequest(){
        WorkManager.getInstance().cancelAllWorkByTag("periodic");
    }

    public static Constraints setCons(){
        Constraints constraints = new Constraints.Builder().build();
        return constraints;
    }

    private void showNotification(String task, String desc) {
        String channelId = "task_channel";
        String channelName = "task_name";

        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(),0,intent,0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), channelId)
                .setContentTitle(task)
                .setContentText(desc)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);
        NotificationManager manager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

        manager.notify(4, builder.build());
    }
}