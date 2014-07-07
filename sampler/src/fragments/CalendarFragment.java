package fragments;

import java.util.Calendar;

import com.virtualprodigyllc.sampler.MainActivity;
import com.virtualprodigyllc.sampler.R;

import fragments.SMSFragment.Callback;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


public class CalendarFragment extends Fragment{


	private Context fragmentContext;
	private Resources res;
	private ActionBar actionBar;
	private Callback callback;
	private EditText title;
	private EditText location;
	private EditText emails;
	private EditText description;

	public interface Callback {
		public void finishedCreatingEvent();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View fragView = inflater.inflate(R.layout.calendar_frag, container, false);
		fragmentContext = getActivity();
		res = getResources();
		setHasOptionsMenu(true);//MB so the fragment has an options menu
		title = (EditText) fragView.findViewById(R.id.cal_title);
		location = (EditText) fragView.findViewById(R.id.cal_location);
		emails = (EditText) fragView.findViewById(R.id.cal_emails);
		description = (EditText) fragView.findViewById(R.id.cal_description);
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	@Override
	public void onAttach(Activity activity) {

		super.onAttach(activity);
		try {
			callback = (Callback) activity;
			actionBar = ((ActionBarActivity)activity).getSupportActionBar();
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement Callbacks");
		}
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.populate_calendar, menu);
		super.onCreateOptionsMenu(menu, inflater);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_send_text:
			populateCalendar();
			return true;
		case android.R.id.home:// MB nav up icon clicked 
			NavUtils.navigateUpTo((Activity) fragmentContext, new Intent(fragmentContext, MainActivity.class));
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

		//MB requires higher min api import android.provider.CalendarContract.EventsColumns;
		private void populateCalendar(){
			final int access_private = 2;
			final int access_public = 3;
			final int availability_busy = 0;
			final int availability_free = 1;
			final int availability_tentative = 2;
			final String freq_weekly = "FREQ=WEEKLY";
			final String freq_yearly = "FREQ=YEARLY";

			Calendar cal = Calendar.getInstance();              
			Intent intent = new Intent(Intent.ACTION_EDIT);
			intent.setType("vnd.android.cursor.item/event");
			intent.putExtra("title", "");
			intent.putExtra("beginTime", cal.getTimeInMillis());
			intent.putExtra("endTime", cal.getTimeInMillis()+60*60*1000);
			intent.putExtra("eventLocation", "");
			intent.putExtra("description", "");
			intent.putExtra("android.intent.extra.EMAIL", "");
			intent.putExtra("accessLevel", access_private);
			intent.putExtra("availability", availability_tentative);
			intent.putExtra("allDay", true);
			intent.putExtra("rrule", freq_yearly);
			//MB see API for more options. Look at CalendarContract.Events 

			startActivity(intent);			
		}
	}
