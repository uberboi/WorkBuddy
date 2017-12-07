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

public class todoDescription extends AppCompatActivity {

    public Button delete_button;
    todoDatabaseHelper mDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todo_description);
        mDatabaseHelper = new todoDatabaseHelper(this);
        final String eventName = getIntent().getStringExtra("event_name");
        String eventDescription = getIntent().getStringExtra("event_description");
        String eventDate = getIntent().getStringExtra("event_date");
       // String eventTime = getIntent().getStringExtra("event_time");


        System.out.println(eventName);
        System.out.println(eventDescription);
        System.out.println(eventDate);
        TextView eName = findViewById(R.id.todoeventname);
        TextView eDescription = findViewById(R.id.todoeventdescription);
        TextView eDate = findViewById(R.id.todoeventdate);

        eName.setText(eventName);
        eDescription.setText(eventDescription);
        eDate.setText(eventDate);


        /*

        TextView eDescription = (TextView) findViewById(R.id.todoeventdescription);
        TextView eDate = (TextView) findViewById(R.id.todoeventdate);
        //TextView eTime = (TextView) findViewById(R.id.todoeventtime);

        eName.setText(eventName);
        eDescription.setText(eventDescription);
        eDate.setText(eventDate);
        //eTime.setText(eventTime);
*/
        delete_button = (Button) findViewById(R.id.deletetodoEventButton);
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabaseHelper.deleteName(eventName);

                Intent intent = new Intent(todoDescription.this, MainActivity.class);
                intent.putExtra("tab_index", "2");
                startActivity(intent);

            }

        });

    }



}
