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
        populateListView();


        return rootView;
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

