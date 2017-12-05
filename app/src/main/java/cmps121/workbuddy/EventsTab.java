package cmps121.workbuddy;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Arrays;

import static android.content.ContentValues.TAG;

/**
 * Created by Steven Huang on 11/13/2017.
 */

public class EventsTab extends Fragment {
    public String name = "";
    public String duedate = "";
    public String desciption = "";
    public String test = "";
    private Button eventDes;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView =  inflater.inflate(R.layout.events_fragment, container, false);

        eventDes = (Button)rootView.findViewById(R.id.hwkbox);
        eventDes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextpage = new Intent(getActivity(), EventDescription.class);



                startActivity(nextpage);
            }

        });

        ListView listview = (ListView) rootView.findViewById(R.id.eventList);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> l, View v, int position, long id){
                Log.i("ListView", "You clicked Item: " + id + " at position:" + position);
                Intent intent = new Intent(getActivity(), EventDescription.class);
                intent.putExtra("position", position);
                intent.putExtra("id", id);
                startActivity(intent);
            }

        });



        return rootView;
    }

}
