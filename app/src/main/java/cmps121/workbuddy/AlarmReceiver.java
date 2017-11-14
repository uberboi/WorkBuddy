package cmps121.workbuddy;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by Steven Huang on 11/4/2017.
 */

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent){

        Log.e("In the reciever", "test");

        //fetch extra values from intent
        Boolean extraValue = intent.getExtras().getBoolean("switch");

        Log.e("what is the value?", extraValue.toString());

        //create intent to AlarmRingService
        Intent service_intent = new Intent(context, AlarmRingService.class);

        //Pass the extra from alarm activity to AlarmRingService
        service_intent.putExtra("switch", extraValue);


        //setup intent
        Intent intent_alarmActivity = new Intent(context, Alarm.class);
        PendingIntent pending_intent_alarmActivity = PendingIntent.getActivity(context, 0, intent_alarmActivity, 0);
        //Setup notifications
        NotificationManager notManager = (NotificationManager)context.getSystemService(NOTIFICATION_SERVICE);

        NotificationCompat.Builder notification_popup = new NotificationCompat.Builder(context)
                .setContentTitle("Alarm is going off!")
                .setContentText("Click me!")
                .setContentIntent(pending_intent_alarmActivity)
                .setAutoCancel(true)
                .setSmallIcon(R.mipmap.ic_launcher_round);

        //start servuce
        context.startService(service_intent);
    }
}
