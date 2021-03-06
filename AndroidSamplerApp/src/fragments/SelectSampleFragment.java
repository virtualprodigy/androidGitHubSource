package fragments;

import java.util.List;
import java.util.Vector;

import comvirtualprodigy.androidsamplerapp.R;

import com.virtualprodigy.AndrpodSampleApp.MainActivity;
import com.virtualprodigy.AndrpodSampleApp.MainActivity.DemoTypes;
import com.virtualprodigy.AndrpodSampleApp.utils.AnimatedListAdapter;
import com.virtualprodigy.AndrpodSampleApp.utils.AnimatedListView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class SelectSampleFragment extends Fragment{

	public interface Callback {
		public void demoSelect(DemoTypes type);
	}

	public SelectSampleFragment() {
	}
	AnimatedListView demoOptions;
	Context fragmentContext;
	Resources res;
	private Callback callback;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View fragView = inflater.inflate(R.layout.main_layout, container, false);
		demoOptions = (AnimatedListView) fragView.findViewById(R.id.demoOptsListView);
		fragmentContext = getActivity();
		res = getResources();

		final AnimatedListAdapter adapter =	new AnimatedListAdapter(generateDemoInfoObjs() , fragmentContext);
		demoOptions.setDivider(null);
		demoOptions.setDividerHeight(0);
		demoOptions.setAdapter(adapter);
		demoOptions.setOnItemClickListener(itemListener);

		return fragView;
	}

	private OnItemClickListener itemListener = new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			callback.demoSelect((DemoTypes) demoOptions.getAdapter().getItem(position));
		}  
	};

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

	private Vector<DemosInfo> generateDemoInfoObjs(){
		Vector<DemosInfo> demos = new Vector <DemosInfo>(); 
		demos.add(new DemosInfo(res.getString(R.string.default_text), res.getString(R.string.default_text), DemoTypes.None));//MB until i get the offset working properly heres an invisible place holder

		demos.add(new DemosInfo(res.getString(R.string.sms_title), res.getString(R.string.sms_desc), DemoTypes.SMS));
		demos.add(new DemosInfo(res.getString(R.string.cal_title), res.getString(R.string.cal_desc), DemoTypes.CAL));
		demos.add(new DemosInfo(res.getString(R.string.map_title), res.getString(R.string.map_desc), DemoTypes.MAP));
		demos.add(new DemosInfo(res.getString(R.string.alert_dialog_title), res.getString(R.string.alert_dialog_desc), DemoTypes.ALERT));
		demos.add(new DemosInfo(res.getString(R.string.fragment_title), res.getString(R.string.fragment_desc), DemoTypes.FRAG));
		//MB will add these another night
		//    	demos.add(new DemosInfo(res.getString(R.string.spinning_title), res.getString(R.string.shaker_desc), demoTypes.SPINNING));
		//    	demos.add(new DemosInfo(res.getString(R.string.shaker_title), res.getString(R.string.spinning_desc), demoTypes.SHAKER));

		demos.add(new DemosInfo(res.getString(R.string.default_text), res.getString(R.string.default_text), DemoTypes.None));//MB until i get the offset working properly heres an invisible place holder

		return demos;
	}

	public class DemosInfo{
		private String title;
		private String description;
		private DemoTypes type;

		public DemosInfo(String title, String descip, DemoTypes type){
			this.title = title;
			this.description = descip;
			this.type = type;
		}

		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}

		public DemoTypes getType() {
			return type;
		}

		public void setType(DemoTypes type) {
			this.type = type;
		}

	}
}
