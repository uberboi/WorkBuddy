package cmps121.workbuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    //Move to Alarm activity on button click
    public void setAlarm(View view){
        Intent intent = new Intent(this, Alarm.class);
        Button alarmButton = (Button) findViewById(R.id.setAlarmButton);
        startActivity(intent);
    }
}
