package cmps121.workbuddy;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.provider.CalendarContract;
import android.widget.Toast;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * Created by han.nguyen on 11/14/17.
 */

public class AddtodoActivity extends AppCompatActivity {

    todoDatabaseHelper mDatabaseHelper;
    Boolean update = false;

    public Button done_button;
    EditText nameText;
    EditText descriptionText;
    EditText dateText;

    String eventName;
    String eventDescription;
    String eventDate;

    String oldEventName;
    String oldEventDescription;
    String oldEventDate;

    final int MY_PERMISSION_REQUEST_WRITE_CALENDAR = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_todo);

        nameText = (EditText)findViewById(R.id.Name);
        descriptionText = (EditText)findViewById(R.id.Description);
        dateText = (EditText) findViewById(R.id.Date);

        Intent receivedIntent = getIntent();
        Bundle extras = receivedIntent.getExtras();
        if(extras == null){
            Log.e("intent","null extras");
        }else{
            Log.e("intent", "no null extras");
            update = true;
            eventName = extras.getString("name");
            eventDescription = extras.getString("description");
            eventDate = extras.getString("date");

            nameText.setText(eventName);
            descriptionText.setText(eventDescription);
            dateText.setText(eventDate);

            oldEventName = nameText.getText().toString();
            oldEventDescription = descriptionText.getText().toString();
            oldEventDate = dateText.getText().toString();

        }

        mDatabaseHelper = new todoDatabaseHelper(this);
        done_button = (Button) findViewById(R.id.Done);
        done_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventName = nameText.getText().toString();
                eventDescription = descriptionText.getText().toString();
                eventDate = dateText.getText().toString();
               // String eventTime = timeText.getText().toString();
                if(update == true) {
                    Log.e("update", "updating record");
                    mDatabaseHelper.updateData(eventName, eventDescription, eventDate,
                            oldEventName, oldEventDescription, oldEventDate);

                }else{
                    mDatabaseHelper.addData(eventName, eventDescription, eventDate);
                }

                Intent intent = new Intent(AddtodoActivity.this, MainActivity.class);
                intent.putExtra("tab_index", "1");
                startActivity(intent);

            }

        });

    }
}
