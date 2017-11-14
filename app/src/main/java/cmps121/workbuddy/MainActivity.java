package cmps121.workbuddy;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TabHost;

public class MainActivity extends AppCompatActivity {

    //FragmentManager fragmentManager = getFragmentManager();
    //FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
    //CalendarTab calendarTab = new CalendarTab();
    private FragmentTabHost tabHost;
    public Button add_event;

    private PopupWindow eventwindow;
    private RelativeLayout mRelativeLayout;
    public void setAdd_event(){
            add_event = (Button)findViewById(R.id.Addevent);
        add_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextpage = new Intent(MainActivity.this, AddEventActivity.class);

                startActivity(nextpage);
            }

        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setAdd_event();



        tabHost = (FragmentTabHost)findViewById(R.id.tabHost);
        tabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        tabHost.addTab(tabHost.newTabSpec("Calendar").setIndicator("Calendar"),
                CalendarTab.class, null);
        tabHost.addTab(tabHost.newTabSpec("To-Do").setIndicator("To-Do"),
                ToDoTab.class, null);
        tabHost.addTab(tabHost.newTabSpec("Events").setIndicator("Events"),
                EventsTab.class, null);
    }


    //Move to Alarm activity on button click
    public void setAlarm(View view){
        Intent intent = new Intent(this, Alarm.class);
        Button alarmButton = (Button) findViewById(R.id.setAlarmButton);
        startActivity(intent);
    }

  
}
