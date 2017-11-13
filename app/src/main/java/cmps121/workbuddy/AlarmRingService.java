package cmps121.workbuddy;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Steven Huang on 11/4/2017.
 */

public class AlarmRingService extends Service {

    MediaPlayer mp;
    Boolean isRunning = false;
    private int startId;

    @Nullable
    @Override
    public IBinder onBind(Intent intent){
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("LocalService", "Received start id " + startId + ": " + intent);

        //setup intent
        Intent intent_alarmActivity = new Intent(this.getApplicationContext(), Alarm.class);
        PendingIntent pending_intent_alarmActivity = PendingIntent.getActivity(this, 0, intent_alarmActivity, 0);
        //Setup notifications
        NotificationManager notManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

        NotificationCompat.Builder notification_popup = new NotificationCompat.Builder(this)
                .setContentTitle("Alarm is going off!")
                .setContentText("Click to turn off")
                .setContentIntent(pending_intent_alarmActivity)
                .setAutoCancel(true)
                .setSmallIcon(R.mipmap.ic_launcher_round);

        //Oreo requires channel for notification as well as running service in foreground for background services
        if(Build.VERSION.SDK_INT >=26) {
            String channelId = "channel1";
            int importance = NotificationManager.IMPORTANCE_LOW;
            NotificationChannel Channel = new NotificationChannel(channelId, "notification channel", importance);
            Channel.enableLights(true);
            notManager.createNotificationChannel(Channel);
            notification_popup.setChannelId(channelId);

            NotificationCompat.Builder foregroundNotification = new NotificationCompat.Builder(this)
                    .setContentTitle("Alarm is being accessed")
                    .setContentText("Click to change")
                    .setContentIntent(pending_intent_alarmActivity)
                    .setAutoCancel(true)
                    .setSmallIcon(R.mipmap.ic_launcher_round)
                    .setChannelId(channelId);
            startForeground(002, foregroundNotification.build());
        }


        //fetch extra boolean values
        Boolean state = intent.getExtras().getBoolean("switch");
        assert state != null;
        Log.e("Ringtone state:", state.toString());
        if (state){
            startId = 1;
            Log.e("startId:", state.toString());
        }else{
            startId = 0;
            Log.e("startId:", state.toString());
        }

        //if alarm is not on and switch is turned on
        // start alarm
        if(!this.isRunning && startId ==1){
            Log.e("alarm is turned on", this.isRunning.toString());
            mp = MediaPlayer.create(this, Settings.System.DEFAULT_ALARM_ALERT_URI);
            mp.start();
            this.isRunning = true;
            this.startId = 0;

            notManager.notify(001, notification_popup.build());

        //if alarm is ringing and switch is turned off
        //turn off alarm
        }else if(this.isRunning && startId == 0){
            mp.stop();
            mp.reset();
            this.isRunning = false;
            this.startId = 0;

         //if alarm is off and switch is turned off
         //do nothing
        }else if(!this.isRunning && startId == 0){
            this.isRunning = false;
            this.startId = 0;

         //if alarm is on and switch is turned on
         //do nothing
        }else if(this.isRunning && startId == 1){
            this.isRunning = true;
            this.startId = 1;
        }
        //mp = MediaPlayer.create(this, Settings.System.DEFAULT_ALARM_ALERT_URI);
        //mp.start();
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        this.isRunning = false;
    }
}
