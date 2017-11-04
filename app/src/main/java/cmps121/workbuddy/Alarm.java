package cmps121.workbuddy;

import android.app.AlarmManager;
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
        Calendar calendar = Calendar.getInstance();

        final TextView t = (TextView) findViewById(R.id.textView);

        //initialize switch
        Switch switchAlarm =(Switch) findViewById(R.id.switchAlarm);
        switchAlarm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    t.setText("on");
                } else {
                    t.setText("off");
                }
            }
        });
    }



}
