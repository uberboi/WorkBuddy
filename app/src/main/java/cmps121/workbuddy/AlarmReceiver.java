package cmps121.workbuddy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

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

        //start servuce
        context.startService(service_intent);

    }
}
