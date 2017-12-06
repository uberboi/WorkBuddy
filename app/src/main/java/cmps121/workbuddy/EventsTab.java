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
    ArrayAdapter<String> adapter;
    ListView listview;

    int counter = 0;


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
                //listItems.add("test " + counter++);
                //adapter.notifyDataSetChanged();
                populateListView();
                addData("test" + counter++);
            }
        });

        //adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, listItems);

        listview = (ListView) rootView.findViewById(R.id.eventList);
        populateListView();
        //listview.setAdapter(adapter);
        /*listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> l, View v, int position, long id) {
                Log.i("ListView", "You clicked Item: " + id + " at position:" + position);
                Intent intent = new Intent(getActivity(), EventDescription.class);
                intent.putExtra("position", position);
                intent.putExtra("id", id);
                startActivity(intent);
            }

        });*/


        return rootView;
    }

    public void addData(String newEntry) {
        boolean insertData = mDatabaseHelper.addData(newEntry);
        if (insertData){
            toastMessage("Data inserted");
        }else{
            toastMessage("Something went wrong");
        }
    }

    private void populateListView(){
        Cursor data = mDatabaseHelper.getData();
        listItems  = new ArrayList<String>();
        while(data.moveToNext()){
            listItems.add(data.getString(1));
        }
        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, listItems);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> l, View v, int position, long id) {
                //Log.i("ListView", "You clicked Item: " + id + " at position:" + position);
                //Intent intent = new Intent(getActivity(), EventDescription.class);
                //intent.putExtra("position", position);
                //intent.putExtra("id", id);
                //startActivity(intent);
                Cursor data = mDatabaseHelper.getItemID(l.getItemAtPosition(position).toString()); //get the id associated with that name
                int itemID = -1;
                while(data.moveToNext()){
                    itemID = data.getInt(0);
                }
                mDatabaseHelper.deleteName(itemID,l.getItemAtPosition(position).toString());
            }

        });
    }

    private void toastMessage(String message){
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
}

