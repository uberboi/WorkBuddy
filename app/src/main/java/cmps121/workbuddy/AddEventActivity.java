package cmps121.workbuddy;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.provider.CalendarContract;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * Created by han.nguyen on 11/14/17.
 */

public class AddEventActivity extends AppCompatActivity {

    public Button done_button;
    final int MY_PERMISSION_REQUEST_WRITE_CALENDAR = 2;

    //Save to file to save info. Based on Professor's sample code
    public void init() {
        /*Intent calIntent = new Intent(Intent.ACTION_INSERT);
        calIntent.setData(CalendarContract.Events.CONTENT_URI);
        startActivity(calIntent);*/
        done_button = (Button) findViewById(R.id.Done);
        done_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                    EditText editText = (EditText)findViewById(R.id.Name);
                    EditText editText2 = (EditText)findViewById(R.id.Description);
                    String text = editText.getText().toString();
                    String text2 = editText2.getText().toString();




               /* ContentResolver cr = getContentResolver();
                ContentValues contentValues = new ContentValues();

                Calendar beginTime = Calendar.getInstance();
                beginTime.set(2017, 11, 14, 9, 30);

                Calendar endTime = Calendar.getInstance();
                endTime.set(2017, 11, 14, 7, 35);

                ContentValues values = new ContentValues();
                values.put(CalendarContract.Events.DTSTART, beginTime.getTimeInMillis());
                values.put(CalendarContract.Events.DTEND, endTime.getTimeInMillis());
                values.put(CalendarContract.Events.TITLE, "Tech Stores");
                values.put(CalendarContract.Events.DESCRIPTION, "Successful Startups");
                values.put(CalendarContract.Events.CALENDAR_ID, 2);
                values.put(CalendarContract.Events.EVENT_TIMEZONE, "Europe/London");
                values.put(CalendarContract.Events.EVENT_LOCATION, "London");
                values.put(CalendarContract.Events.GUESTS_CAN_INVITE_OTHERS, "1");
                values.put(CalendarContract.Events.GUESTS_CAN_SEE_GUESTS, "1");

                if (ActivityCompat.checkSelfPermission(AddEventActivity.this, Manifest.permission.WRITE_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(AddEventActivity.this, new String[]{Manifest.permission.WRITE_CALENDAR}, MY_PERMISSION_REQUEST_WRITE_CALENDAR);
                }

                cr.insert(CalendarContract.Events.CONTENT_URI, values);*/

                Intent calIntent = new Intent(Intent.ACTION_INSERT);
                calIntent.setType("vnd.android.cursor.item/event");
                calIntent.putExtra(CalendarContract.Events.TITLE, text);
                //calIntent.putExtra(CalendarContract.Events.EVENT_LOCATION, "My Beach House");
                calIntent.putExtra(CalendarContract.Events.DESCRIPTION, text2);
                GregorianCalendar calDate = new GregorianCalendar(CalendarTab.year,CalendarTab.month,CalendarTab.day);
                calIntent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);
                calIntent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,
                        calDate.getTimeInMillis());
                calIntent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME,
                        calDate.getTimeInMillis());
                startActivity(calIntent);

                /*Intent donepage = new Intent(AddEventActivity.this, MainActivity.class);

                startActivity(donepage);*/
            }

        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_event);
        if (ActivityCompat.checkSelfPermission(AddEventActivity.this, Manifest.permission.WRITE_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(AddEventActivity.this, new String[]{Manifest.permission.WRITE_CALENDAR}, MY_PERMISSION_REQUEST_WRITE_CALENDAR);
        }
        init();

    }
}