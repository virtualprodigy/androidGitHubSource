package com.virtualprodigy.AndrpodSampleApp;

import comvirtualprodigy.androidsamplerapp.R;

import fragments.SMSFragment;
import fragments.CalendarFragment;
import fragments.SelectSampleFragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

public class MainActivity extends ActionBarActivity implements SelectSampleFragment.Callback, SMSFragment.Callback, CalendarFragment.Callback {
	public enum DemoTypes{
		None,SMS,CAL,MAP,ALERT,FRAG,SPINNING,SHAKER;
	}

	//MB Tags to reference the fragments later
	public final String SAMPLE_TAG = "sample";
	public final String SMS_TAG = "sms";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getSupportFragmentManager().beginTransaction()
		.add(R.id.container, new SelectSampleFragment(), SAMPLE_TAG).commit();
	}

	// MB child frag requesting a sample frag to be displayed
	@Override
	public void demoSelect(DemoTypes type) {
		switch(type){
		case SMS:
			getSupportFragmentManager().beginTransaction().replace(R.id.container, new SMSFragment(), SMS_TAG).commit();
			break;

		case CAL:
			break;

		case MAP:
			break;

		case ALERT:
			break;

		case FRAG:
			break;

		case SPINNING:
			break;

		case SHAKER:
			break;

		default:
			break;	
		}
	}

	@Override
	public void finishedSendingSMS() {
		getSupportFragmentManager().beginTransaction()
		.replace(R.id.container, new SelectSampleFragment(), SAMPLE_TAG).commit();		
	}

	@Override
	public void finishedCreatingEvent() {
		getSupportFragmentManager().beginTransaction()
		.replace(R.id.container, new SelectSampleFragment(), SAMPLE_TAG).commit();
	}
	
	/*  
//  MB not settings menu at this point 
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

 	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

//        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            return true;
//        }
        return super.onOptionsItemSelected(item);
    } */

}
