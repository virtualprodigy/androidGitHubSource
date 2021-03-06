package com.virtualprodigy.AndrpodSampleApp.utils;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import java.util.Vector;

import comvirtualprodigy.androidsamplerapp.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import fragments.SelectSampleFragment.DemosInfo;

public class AnimatedListAdapter  extends BaseAdapter {

	private Vector<DemosInfo> _demoList;
	Context _context;
	private final String Error_TAG = AnimatedListAdapter.this.getClass().getName();

	/**
	 * @param demoList
	 * @param context
	 * THis class populates the list view at the bottom of the calendar view, with all of that days's stories
	 */
	public AnimatedListAdapter (Vector<DemosInfo> demoList, Context context){
		_demoList = demoList;
		_context = context;
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return _demoList.size();
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getItem(int)
	 * MB no object to return 
	 */
	public Object getItem(int position) {
		return _demoList.get(position).getType();
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public View getView(int position, View view, ViewGroup parent) {
		// TODO Auto-generated method stub
		View v = view;
		if (v == null)
		{
			LayoutInflater vi = (LayoutInflater)_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.anim_listview_item, null);
		}else{//MB ensure the recycled view isn't set to invisible by below code
			((RelativeLayout)v.findViewById(R.id.list_item_container)).setVisibility(View.VISIBLE);
			((RelativeLayout)v.findViewById(R.id.list_item_container)).setEnabled(true);
		}
		
		if(position == 0 || position == getCount() -1){
			((RelativeLayout)v.findViewById(R.id.list_item_container)).setVisibility(View.INVISIBLE);//MB add a little padding via invisible view until I fix offsetting issue
			((RelativeLayout)v.findViewById(R.id.list_item_container)).setEnabled(false);
			return v;
		}
		
		TextView title = (TextView)v.findViewById(R.id.title);
		TextView description = (TextView)v.findViewById(R.id.demoDescrip);
		DemosInfo tempDemoInfo = _demoList.get(position);

			title.setText(tempDemoInfo.getTitle());
			description.setText(tempDemoInfo.getDescription());
			description.setSelected(true);//MB force the marquee to start when needed
			
		return v;
	}	
}
