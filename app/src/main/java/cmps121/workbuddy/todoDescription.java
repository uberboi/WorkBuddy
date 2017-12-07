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
    public Button edit_button;
    todoDatabaseHelper mDatabaseHelper;

    String todoeventName;
    String todoeventDescription;
    String todoeventDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todo_description);
        mDatabaseHelper = new todoDatabaseHelper(this);
        todoeventName = getIntent().getStringExtra("todoevent_name");
        todoeventDescription = getIntent().getStringExtra("todoevent_description");
        todoeventDate = getIntent().getStringExtra("todoevent_date");

        TextView eName = findViewById(R.id.todoeventname);
        TextView eDescription = findViewById(R.id.todoeventdescription);
        TextView eDate = findViewById(R.id.todoeventdate);

        eName.setText(todoeventName);
        eDescription.setText(todoeventDescription);
        eDate.setText(todoeventDate);

        delete_button = (Button) findViewById(R.id.deletetodoEventButton);
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabaseHelper.deleteName(todoeventName);

                Intent intent = new Intent(todoDescription.this, MainActivity.class);
                intent.putExtra("tab_index", "1");
                startActivity(intent);

            }

        });

        edit_button = (Button) findViewById(R.id.edittodoEventButton);
        edit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(todoDescription.this, AddtodoActivity.class);
                intent.putExtra("edit_button", "edit");
                intent.putExtra("name", todoeventName);
                intent.putExtra("description", todoeventDescription);
                intent.putExtra("date", todoeventDate);

                startActivity(intent);
            }
        });


    }



}
