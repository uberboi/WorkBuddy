package cmps121.workbuddy;

import android.content.Intent;
import android.database.Cursor;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cmps121.workbuddy.model.Assignment;
import cmps121.workbuddy.rest.AssignmentApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.ContentValues.TAG;
import static android.os.Parcelable.PARCELABLE_WRITE_RETURN_VALUE;

/**
 * Created by Steven Huang on 11/13/2017.
 */

public class EventsTab extends Fragment {

    DatabaseHelper mDatabaseHelper;
    private Button addEvent;
    ArrayList<String> listItems;
    ArrayList<String> listItems2;
    ArrayAdapter<String> adapter;
    ListView listview;

    private String eventDate;
    private String eventDescription;
    private String eventName;
    private String eventTime;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.events_fragment, container, false);
        mDatabaseHelper = new DatabaseHelper(getActivity());

        addEvent = (Button) rootView.findViewById(R.id.addEvent);
        addEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent nextpage = new Intent(getActivity(), AddEventActivity.class);
                startActivity(nextpage);

            }
        });

        listview = (ListView) rootView.findViewById(R.id.eventList);
        addCanvasData();
        populateListView();


        return rootView;
    }

    private void addCanvasData(){
        Retrofit retrofit = null;
        String BASE_URL = "https://canvas.ucsc.edu/ ";
        String ACCESS_TOKEN = "9270~DcNmfuBDyuifVzDb9jlQYcoPwwGXR5SjpjAxNHok3xUcnSOSi5MgBR4dbjXbySiu";

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

                setPost(response.body().get(0));
            }

            @Override
            public void onFailure(Call<List<Assignment>> call, Throwable throwable) {
                Log.e(TAG, throwable.toString());
            }
        });


    }
    private void setPost(Assignment assign) {

        eventName = (assign.getName().toString());
        eventDescription = (assign.getDescription().toString());
        eventDate = "12/8/17";
        eventTime = "9:00PM";
        mDatabaseHelper.addData(eventName, eventDescription, eventDate, eventTime);


    }
    private void populateListView(){
        Cursor data = mDatabaseHelper.getData();
        listItems  = new ArrayList<String>();
        listItems2  = new ArrayList<String>();
        while(data.moveToNext()){
            listItems.add(data.getString(1));
            listItems2.add(data.getString(3));
            Log.e("test", data.getString(3));
        }
        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_2, android.R.id.text1, listItems){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                text1.setText(listItems.get(position));
                text2.setText(listItems2.get(position));
                return view;
            }
        };
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> l, View v, int position, long id) {

                Cursor data = mDatabaseHelper.getItemID(l.getItemAtPosition(position).toString()); //get the id associated with that name
                while(data.moveToNext()){
                    eventName = data.getString(1);
                    eventDescription = data.getString(2);
                    eventDate = data.getString(3);
                    eventTime = data.getString(4);
                    //Log.e("description", EventDescription);
                    Intent intent = new Intent(getActivity(), EventDescription.class);
                    intent.putExtra("event_name", eventName);
                    intent.putExtra("event_description", eventDescription);
                    intent.putExtra("event_date", eventDate);
                    intent.putExtra("event_time", eventTime);
                    //intent.putExtra("event_date", eventDate);
                    //Log.e("event name", eventName);
                    //Log.e("event description", eventDescription);
                    //Log.e("event date", eventDate);

                    startActivity(intent);
                }


            }

        });
    }
}

