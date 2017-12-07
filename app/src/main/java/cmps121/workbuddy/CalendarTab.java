package cmps121.workbuddy;

import android.content.Intent;
import android.database.Cursor;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * Created by Steven Huang on 11/13/2017.
 */

public class CalendarTab extends Fragment {

    private CalendarView mCalendarView;
    DatabaseHelper mDatabaseHelper;
    Date eventDate = new Date();
    private String eventDateString;
    private String eventDescription;
    private String eventName;
    private String eventTime;

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.calendar_fragment, container, false);
        mDatabaseHelper = new DatabaseHelper(getActivity());

        final SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");

        final CaldroidListener listener = new CaldroidListener() {

            @Override
            public void onSelectDate(Date date, View view) {
                Cursor data = mDatabaseHelper.getItemFromDate(String.valueOf(formatter.format(date))); //get the id associated with that name
                while(data.moveToNext()){
                    eventName = data.getString(1);
                    eventDescription = data.getString(2);
                    eventDateString = data.getString(3);
                    eventTime = data.getString(4);
                    if(eventName.equals("")){
                        Log.e("event", "does not exist");
                    }else {
                        Log.e("event name", eventName);
                        Log.e("event description", eventDescription);
                        Log.e("event date", eventDateString);
                        Intent intent = new Intent(getActivity(), EventDescription.class);
                        intent.putExtra("event_name", eventName);
                        intent.putExtra("event_description", eventDescription);
                        intent.putExtra("event_date", eventDateString);
                        intent.putExtra("event_time", eventTime);
                        startActivity(intent);
                    }
                }
            }
        };

        CaldroidFragment caldroidFragment = new CaldroidFragment();
        Bundle args = new Bundle();
        Calendar cal = Calendar.getInstance();
        args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1);
        args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
        caldroidFragment.setArguments(args);
        caldroidFragment.setCaldroidListener(listener);

        Cursor data = mDatabaseHelper.getData();
        while(data.moveToNext()){
            if(data.getString(3).equals("")){
                Log.e("event", "event has no date");
            }else {
                eventDate = ConvertToDate(data.getString(3));
                caldroidFragment.setBackgroundDrawableForDate(getResources().getDrawable(R.drawable.event), eventDate);
                caldroidFragment.refreshView();
            }

        }

        android.support.v4.app.FragmentTransaction t = getChildFragmentManager().beginTransaction();
        t.replace(R.id.calendarView, caldroidFragment);
        t.commit();

        return rootView;
    }

    private Date ConvertToDate(String dateString){
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date convertedDate = new Date();
        try {
            convertedDate = dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return convertedDate;
    }

}
