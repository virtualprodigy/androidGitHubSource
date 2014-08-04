package fragments;

import comvirtualprodigy.androidsamplerapp.R;

import fragments.CalendarFragment.Callback;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;

public abstract class AbstractFragment extends Fragment{
	protected Context fragmentContext;
	protected Resources res; 
	protected SuperCallback superCallback;
	protected ActionBar actionBar;

	public interface SuperCallback {
		public void backNavigate();
		public void setIsNavUpMainMenu(boolean isNUMM);
		public void setOrginalLayout(boolean setOriginal);
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		fragmentContext = getActivity();
		res = getResources();
		setHasOptionsMenu(true);//MB so the fragment has an options menu
	}
	
	@Override
	public void onAttach(Activity activity) {

		super.onAttach(activity);
		try {
			superCallback = (SuperCallback) activity;
			actionBar = ((ActionBarActivity)activity).getSupportActionBar();
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement Callbacks");
		}
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:// MB nav up icon clicked 
//			NavUtils.navigateUpTo((Activity) fragmentContext, new Intent(fragmentContext, MainActivity.class));//MB cant navi from frag this way must be frag act
			superCallback.backNavigate();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	@Override
	public void onPause() {
		super.onPause();
		superCallback.setIsNavUpMainMenu(false);
	}

	@Override
	public void onResume() {
		super.onResume();
		superCallback.setIsNavUpMainMenu(true);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayHomeAsUpEnabled(true);
//		actionBar.setDisplayShowHomeEnabled(true);
	}
	
}
