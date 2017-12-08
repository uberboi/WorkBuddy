package cmps121.workbuddy;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.ToggleButton;

public class Alarm extends AppCompatActivity {

    AlarmManager alarm_manager;
    TimePicker alarm_picker;
    Context context;
    PendingIntent pendingIntent;
    public Button calendar_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        this.context = this;

        //initialize alarm manager
        alarm_manager = (AlarmManager)getSystemService(ALARM_SERVICE);
        //initialize time picker
        alarm_picker = (TimePicker)findViewById(R.id.timePicker);

        //create an instance of a calender
        final Calendar calendar = Calendar.getInstance();

        final TextView t = (TextView) findViewById(R.id.textView);

        //Intent for alarm reciever
        final Intent alarmIntent = new Intent(this.context, AlarmReceiver.class);

        //initialize switch
        Switch switchAlarm =(Switch) findViewById(R.id.switchAlarm);
        switchAlarm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    //setting calendar to hour and minute of picked alarm
                    calendar.set(Calendar.HOUR_OF_DAY, alarm_picker.getHour());
                    calendar.set(Calendar.MINUTE, alarm_picker.getMinute());

                    int hour = alarm_picker.getHour();
                    int minute = alarm_picker.getMinute();
                    String hour_string = String.valueOf(hour);
                    String minute_string = String.valueOf(minute);

                    if (hour > 12){
                        hour_string = String.valueOf(hour - 12);
                    }
                    if (minute < 10){
                        minute_string = "0" + String.valueOf(minute);
                    }

                    t.setText("Alarm set to: " + hour_string + ":" + minute_string);

                    //boolean value for switch value in alarm
                    alarmIntent.putExtra("switch", true);

                    //create pending intent to delay alarm until specified time
                    pendingIntent = PendingIntent.getBroadcast(Alarm.this, 0, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);

                    //set the alarm manager
                    alarm_manager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);

                } else {
                    //Cancel the alarm
                    alarm_manager.cancel(pendingIntent);
                    t.setText("off");

                    //tells alarm switch is off
                    alarmIntent.putExtra("switch", false);

                    //stop ringtone
                    sendBroadcast(alarmIntent);
                }
            }
        });

        calendar_button = (Button) findViewById(R.id.calendarButton);
        calendar_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Alarm.this, MainActivity.class);
                startActivity(intent);

            }

        });
    }



}
