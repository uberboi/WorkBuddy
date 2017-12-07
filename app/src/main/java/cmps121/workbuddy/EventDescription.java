package cmps121.workbuddy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by han.nguyen on 11/14/17.
 */

public class EventDescription extends AppCompatActivity {

    public Button delete_button;
    public Button edit_button;
    DatabaseHelper mDatabaseHelper;

    String eventName;
    String eventDescription;
    String eventDate;
    String eventTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_description);
        mDatabaseHelper = new DatabaseHelper(this);
        eventName = getIntent().getStringExtra("event_name");
        eventDescription = getIntent().getStringExtra("event_description");
        eventDate = getIntent().getStringExtra("event_date");
        eventTime = getIntent().getStringExtra("event_time");

        TextView eName = (TextView) findViewById(R.id.eventname);
        TextView eDescription = (TextView) findViewById(R.id.eventdescription);
        TextView eDate = (TextView) findViewById(R.id.eventdate);
        TextView eTime = (TextView) findViewById(R.id.eventtime);

        eName.setText(eventName);
        eDescription.setText(eventDescription);
        eDate.setText(eventDate);
        eTime.setText(eventTime);

        delete_button = (Button) findViewById(R.id.deleteEventButton);
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabaseHelper.deleteName(eventName);

                Intent intent = new Intent(EventDescription.this, MainActivity.class);
                intent.putExtra("tab_index", "2");
                startActivity(intent);

            }

        });

        edit_button = (Button) findViewById(R.id.editEventButton);
        edit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EventDescription.this, AddEventActivity.class);
                intent.putExtra("edit_button", "edit");
                intent.putExtra("name", eventName);
                intent.putExtra("description", eventDescription);
                intent.putExtra("date", eventDate);
                intent.putExtra("time", eventTime);

                startActivity(intent);
            }
        });



    }



}
