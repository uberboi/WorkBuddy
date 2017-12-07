package cmps121.workbuddy;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import com.roomorama.caldroid.CaldroidFragment;

import java.util.Calendar;

import static android.content.ContentValues.TAG;

/**
 * Created by Steven Huang on 11/13/2017.
 */

public class CalendarTab extends Fragment {

    private CalendarView mCalendarView;
    public static String date;
    public static int day = 14;
    public static int month = 11;
    public static int year = 2017;
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.calendar_fragment, container, false);

        CaldroidFragment caldroidFragment = new CaldroidFragment();
        Bundle args = new Bundle();
        Calendar cal = Calendar.getInstance();
        args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1);
        args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
        caldroidFragment.setArguments(args);

        android.support.v4.app.FragmentTransaction t = getChildFragmentManager().beginTransaction();
        t.replace(R.id.calendarView, caldroidFragment);
        t.commit();

        /*
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
       */

        return rootView;
    }

}
