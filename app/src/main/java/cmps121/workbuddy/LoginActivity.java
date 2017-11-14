package cmps121.workbuddy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.OutputStreamWriter;
import java.util.ArrayList;

/**
 * Created by han.nguyen on 11/14/17.
 */

public class LoginActivity extends AppCompatActivity {

    public Button login_button;



    //Save to file to save info. Based on Professor's sample code
    public void init() {
        login_button = (Button) findViewById(R.id.Login);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    // open myfilename.txt for writing
                    OutputStreamWriter out=new OutputStreamWriter(openFileOutput("login.txt",MODE_APPEND));
                    // write the contents to the file

                    EditText editText = (EditText)findViewById(R.id.Email);
                    EditText editText2 = (EditText)findViewById(R.id.Password);
                    String text = editText.getText().toString();
                    String text2 = editText2.getText().toString();


                    // close the file

                    out.close();

                    Toast.makeText(getApplicationContext(),"Login Saved",Toast.LENGTH_LONG).show();
                }

                catch (java.io.IOException e) {

                    //do something if an IOException occurs.
                    Toast.makeText(getApplicationContext(),"Sorry Text could not be saved",Toast.LENGTH_LONG).show();


                }


                Intent donepage = new Intent(LoginActivity.this, MainActivity.class);

                startActivity(donepage);
            }

        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

}
