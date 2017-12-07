package cmps121.workbuddy;

import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Steven Huang on 11/13/2017.
 */




public class ToDoTab extends Fragment {

    todoDatabaseHelper mDatabaseHelper;
    private Button addTodoEvent;
    ArrayList<String> listItems;
    ArrayAdapter<String> adapter;
    ListView listview;

    private String todoeventDate;
    private String todoeventDescription;
    private String todoeventName;
    private String todoeventTime;
    int counter = 0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.todo_fragment, container, false);
        mDatabaseHelper = new todoDatabaseHelper(getActivity());

        addTodoEvent = (Button) rootView.findViewById(R.id.addtodoEvent);
        addTodoEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //listItems.add("test " + counter++);
                //adapter.notifyDataSetChanged();

                Intent nextpage = new Intent(getActivity(), AddtodoActivity.class);
                startActivity(nextpage);

                //addData("test" + counter++);
                //populateListView();
            }
        });

        //eventName = getActivity().getIntent().getStringExtra("event_name");
        //eventDescription = getActivity().getIntent().getStringExtra("event_description");
        todoeventDate = getActivity().getIntent().getStringExtra("event_date");
        //adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, listItems);

        listview = (ListView) rootView.findViewById(R.id.todoeventList);
        populateListView();



        return rootView;
    }

    /*
    public void addData(String newEntry) {
        boolean insertData = mDatabaseHelper.addData(newEntry);
        if (insertData){
            toastMessage("Data inserted");
        }else{
            toastMessage("Something went wrong");
        }
    }
    */

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

                Log.println(position,"The Posotion", "The position");
                // When clicked, show a toast with the TextView text
                String val =(String) l.getItemAtPosition(position);
                System.out.println("Value is "+val);



                Cursor data = mDatabaseHelper.getItemID(val);
                Log.v("Cursor Object", DatabaseUtils.dumpCursorToString(data));

                if (data.moveToFirst()) // data
                    System.out.println(data.getString(data.getColumnIndex("name")));
                    System.out.println(data.getString(data.getColumnIndex("description")));
                    System.out.println("date will be underneath");

                    System.out.println(data.getString(data.getColumnIndex("date")));





                todoeventName = data.getString(data.getColumnIndex("name"));
                todoeventDescription = data.getString(data.getColumnIndex("description"));
                todoeventDate = data.getString(data.getColumnIndex("date"));
                data.close();
                Intent intent = new Intent(getActivity(), todoDescription.class);
                intent.putExtra("event_name", todoeventName);
                intent.putExtra("event_description", todoeventDescription);
                intent.putExtra("event_date", todoeventDate);
                startActivity(intent);

               /* Cursor data = mDatabaseHelper.getItemID(val); //get the id associated with that name

                while(data.moveToNext()){
                    todoeventName = data.getString(1);
                    todoeventDescription = data.getString(2);
                    todoeventDate = data.getString(3);
                   // todoeventTime = data.getString(4);
                    //Log.e("description", EventDescription);
                    Intent intent = new Intent(getActivity(), todoDescription.class);
                    intent.putExtra("event_name", todoeventName);
                    intent.putExtra("event_description", todoeventDescription);
                    intent.putExtra("event_date", todoeventDate);
                    //intent.putExtra("event_time", eventTime);
                    //intent.putExtra("event_date", eventDate);
                    //Log.e("event name", eventName);
                    //Log.e("event description", eventDescription);
                    //Log.e("event date", eventDate);

                    startActivity(intent);
                }*/


            }

        });
    }
}

