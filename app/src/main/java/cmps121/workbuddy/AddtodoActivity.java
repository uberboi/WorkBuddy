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

    public Button done_button;
    final int MY_PERMISSION_REQUEST_WRITE_CALENDAR = 2;

    //Save to file to save info. Based on Professor's sample code
    public void init() {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_event);
        /*if (ActivityCompat.checkSelfPermission(AddtodoActivity.this, Manifest.permission.WRITE_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(AddtodoActivity.this, new String[]{Manifest.permission.WRITE_CALENDAR}, MY_PERMISSION_REQUEST_WRITE_CALENDAR);
        }*/
        //init();
        mDatabaseHelper = new todoDatabaseHelper(this);
        done_button = (Button) findViewById(R.id.Done);
        done_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText nameText = (EditText)findViewById(R.id.Name);
                EditText descriptionText = (EditText)findViewById(R.id.Description);
                EditText dateText = (EditText) findViewById(R.id.Date);
               // EditText timeText = (EditText) findViewById(R.id.Time);
                String eventName = nameText.getText().toString();
                String eventDescription = descriptionText.getText().toString();
                String eventDate = dateText.getText().toString();
               // String eventTime = timeText.getText().toString();

                mDatabaseHelper.addData(eventName, eventDescription, eventDate);

                Intent intent = new Intent(AddtodoActivity.this, MainActivity.class);
                intent.putExtra("tab_index", "2");
                startActivity(intent);

            }

        });

    }
}
