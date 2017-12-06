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
    DatabaseHelper mDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_description);
        mDatabaseHelper = new DatabaseHelper(this);
        final String eventName = getIntent().getStringExtra("event_name");
        String eventDescription = getIntent().getStringExtra("event_description");
        String eventDate = getIntent().getStringExtra("event_date");

        TextView eName = (TextView) findViewById(R.id.eventname);
        TextView eDescription = (TextView) findViewById(R.id.eventdescription);
        TextView eDate = (TextView) findViewById(R.id.eventdate);

        eName.setText(eventName);
        eDescription.setText(eventDescription);
        eDate.setText(eventDate);

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

    }



}
