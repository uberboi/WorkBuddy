package cmps121.workbuddy;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import static android.content.ContentValues.TAG;

/**
 * Created by Steven Huang on 11/13/2017.
 */

public class CalendarTab extends Fragment {

    private CalendarView mCalendarView;
    public static String date;
    public static int day = 1;
    public static int month = 1;
    public static int year = 2017;
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.calendar_fragment, container, false);
        mCalendarView = (CalendarView)rootView.findViewById(R.id.calendarView);

        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                day = i2;
                month = i1;
                year = i;
                date = (i1+1) + "," + i2 + "," + i;
                Log.d(TAG, "on SelectedDayChange: mm/dd/yyyy: " + date);
                //i = year
                //i1 = month, jan at 0
                //i2 = day
            }

        });


        return rootView;
    }

}
