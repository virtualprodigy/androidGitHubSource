package fragments;

import java.util.ArrayList;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.virtualprodigy.AndrpodSampleApp.MainActivity;

import comvirtualprodigy.androidsamplerapp.R;
import fragments.SMSFragment.Callback;

public class MapFragment  extends AbstractFragment{
	private Callback callback;
	private EditText address;

	public interface Callback {
		public void finishedDisplayingMap();
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View fragView = inflater.inflate(R.layout.map_frag, container, false);
		fragmentContext = getActivity();
		res = getResources();
		address = (EditText) fragView.findViewById(R.id.address);

		return fragView;
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.open_map, menu);
		super.onCreateOptionsMenu(menu, inflater);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_maps:
			uriToMap();
			
			return true;
//		case android.R.id.home:// MB nav up icon clicked 
//			callback.finishedDisplayingMap();
//			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onAttach(Activity activity) {

		super.onAttach(activity);
		try {
			callback = (Callback) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement Callbacks");
		}
	}

	private void uriToMap(){
		Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + address.getText().toString()));
		startActivity(intent);	
	}
	
}