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
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Steven Huang on 11/13/2017.
 */




public class ToDoTab extends Fragment {

    todoDatabaseHelper mDatabaseHelper;
    private Button addTodoEvent;
    ArrayList<String> listItems;
    ArrayList<String> listItems2;
    ArrayAdapter<String> adapter;
    ListView listview;

    private String todoeventDate;
    private String todoeventDescription;
    private String todoeventName;

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

                Intent nextpage = new Intent(getActivity(), AddtodoActivity.class);
                startActivity(nextpage);
            }
        });

        listview = (ListView) rootView.findViewById(R.id.todoeventList);
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
                intent.putExtra("todoevent_name", todoeventName);
                intent.putExtra("todoevent_description", todoeventDescription);
                intent.putExtra("todoevent_date", todoeventDate);
                startActivity(intent);

            }

        });
    }
}

