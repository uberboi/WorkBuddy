package cmps121.workbuddy;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.OutputStreamWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cmps121.workbuddy.model.Assignment;
import cmps121.workbuddy.rest.AssignmentApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.ContentValues.TAG;
/**
 * Created by han.nguyen on 11/14/17.
 */

public class LoginActivity extends AppCompatActivity {

    public Button login_button;
    public Button help_button;

    DatabaseHelper mDatabaseHelper;


    private String eventDate;
    private String eventDescription;
    private String eventName;
    private String eventTime;
    String ACCESS_TOKEN = "";


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

                    EditText editText = (EditText)findViewById(R.id.AccessToken);
                    ACCESS_TOKEN = editText.getText().toString();
                    Log.d(TAG,ACCESS_TOKEN);
                    // close the file

                    out.close();
                }

                catch (java.io.IOException e) {

                    //do something if an IOException occurs.
                    Toast.makeText(getApplicationContext(),"Sorry Text could not be saved",Toast.LENGTH_LONG).show();


                }


                if(ACCESS_TOKEN.equals("")){
                    Toast.makeText(getApplicationContext(), "Please enter access token",Toast.LENGTH_LONG).show();

                }else {


                    Toast.makeText(getApplicationContext(),"Login Saved",Toast.LENGTH_LONG).show();
                    //mDatabaseHelper.addData(ACCESS_TOKEN, "", "", "");
                    addCanvasData();

                    Intent donepage = new Intent(LoginActivity.this, MainActivity.class);

                    startActivity(donepage);
                }
            }

        });

        help_button = (Button) findViewById(R.id.Help);
        help_button.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                Uri uri = Uri.parse("https://guides.instructure.com/s/2204/m/4214/l/724194-how-do-i-obtain-an-api-access-token-for-an-account");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }

        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mDatabaseHelper = new DatabaseHelper(getApplicationContext());

        init();
    }


    private void addCanvasData(){
        Retrofit retrofit = null;
        String BASE_URL = "https://canvas.ucsc.edu/ ";

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        AssignmentApiService assignmentapiservice = retrofit.create(AssignmentApiService.class);

        Call<List<Assignment>> call = assignmentapiservice.getAssignments(ACCESS_TOKEN);
        call.enqueue(new Callback<List<Assignment>>() {
            @Override
            public void onResponse(Call<List<Assignment>> call, Response<List<Assignment>> response) {
               Integer i = 0;
               for(i = 0; i < 7; i++) {
                   setPost(response.body().get(i));
               }
               /* while(response.body().get(i) != null ) {
                    setPost(response.body().get(i));
                    i++;
                }*/
            }

            @Override
            public void onFailure(Call<List<Assignment>> call, Throwable throwable) {
                Log.e(TAG, throwable.toString());
            }
        });


    }
    private void setPost(Assignment assign) {
        Log.e("blah", assign.getName().toString());

        eventName = (assign.getName().toString());
        eventDescription = (assign.getDescription().toString());
        eventDate = "";
        eventTime = "";
        String test;
        if(assign.getDueAt()!=null) {
            test = (assign.getDueAt().toString());
            SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            SimpleDateFormat dfTime = new SimpleDateFormat("HH:mm");
            Date blah = new Date();
            try {
                blah = jsonParse(test);
                Log.e("blah", String.valueOf(blah));

                String blahTime = dfTime.format(blah);
                Log.e("blah2", blahTime);
                eventTime = blahTime;
                String blahString = df.format(blah);
                Log.e("blah2", blahString);
                eventDate = blahString;

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        mDatabaseHelper.addData(eventName, eventDescription, eventDate, eventTime);


    }

    public Date jsonParse(String input ) throws java.text.ParseException {
        SimpleDateFormat df = new SimpleDateFormat( "yyyy-MM-dd'T'HH:mm:ssz" );
        if ( input.endsWith( "Z" ) ) {
            input = input.substring( 0, input.length() - 1) + "GMT-00:00";
        } else {
            int inset = 6;

            String s0 = input.substring( 0, input.length() - inset );
            String s1 = input.substring( input.length() - inset, input.length() );

            input = s0 + "GMT" + s1;
        }

        return df.parse( input );

    }

}
