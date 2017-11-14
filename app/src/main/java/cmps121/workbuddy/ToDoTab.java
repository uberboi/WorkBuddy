package cmps121.workbuddy;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Steven Huang on 11/13/2017.
 */


public class ToDoTab extends Fragment {
    public Button todoEvent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.todo_fragment, container, false);

        todoEvent = (Button)rootView.findViewById(R.id.hwkbox);
        todoEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextpage = new Intent(getActivity(), EventDescription.class);



                startActivity(nextpage);
            }

        });


        return rootView;
    }

}
