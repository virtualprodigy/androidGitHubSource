package fragments;

import java.util.ArrayList;

import com.virtualprodigy.AndrpodSampleApp.MainActivity;
import comvirtualprodigy.androidsamplerapp.R;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
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

public class SMSFragment extends AbstractFragment{
	private Callback callback;
	private EditText smsNumber;
	private EditText smsBody;

	public interface Callback {
		public void finishedSendingSMS();
	}

	public SMSFragment() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View fragView = inflater.inflate(R.layout.sms_frag, container, false);
		fragmentContext = getActivity();
		res = getResources();
		setHasOptionsMenu(true);//MB so the fragment has an options menu
		smsNumber = (EditText) fragView.findViewById(R.id.text_number);
		smsBody = (EditText) fragView.findViewById(R.id.text_message);

		return fragView;
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.send_text_message, menu);
		super.onCreateOptionsMenu(menu, inflater);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_send_text:
			sendSMS();
			return true;
		}
		return super.onOptionsItemSelected(item);
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

	private void sendSMS(){

		new Thread(new Runnable() {

			@Override
			public void run() {

				try{
					SmsManager smsMan = SmsManager.getDefault();
					PendingIntent sentIntent,deliveryIntent;

					sentIntent= PendingIntent.getBroadcast(fragmentContext, 0,
							new Intent("sent"), PendingIntent.FLAG_ONE_SHOT);

					//---when the SMS has been sent---
					fragmentContext.registerReceiver(new BroadcastReceiver(){
						@Override
						public void onReceive(Context arg0, Intent arg1) {
							Log.d("SMS", "context : " + arg0 + " intent : " + arg1);
							String message = "";
							switch (getResultCode())
							{
							case  Activity.RESULT_OK:
								message = "SMS sent";
								break;
							case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
								message ="Generic failure";
								break;
							case SmsManager.RESULT_ERROR_NO_SERVICE:
								message = "No service";
								break;
							case SmsManager.RESULT_ERROR_NULL_PDU:
								message = "Null PDU";
								break;
							case SmsManager.RESULT_ERROR_RADIO_OFF:
								message = "Radio off";
								break;
							}

							if(!message.equals("")){  
								final String mess = message;
								((Activity) fragmentContext).runOnUiThread(new Runnable() {

									@Override
									public void run() {
										Toast.makeText( fragmentContext, mess, 
												Toast.LENGTH_SHORT).show();
									}
								});
							}
						}
					}, new IntentFilter("sent"));

					deliveryIntent = PendingIntent.getBroadcast(fragmentContext, 0,
							new Intent("delivery"),  PendingIntent.FLAG_ONE_SHOT);
					//---when the SMS has been delivered---
					fragmentContext.registerReceiver(new BroadcastReceiver(){
						@Override
						public void onReceive(Context arg0, Intent arg1) {
							String message = "";
							switch (getResultCode())
							{
							case Activity.RESULT_OK:
								message = "SMS delivered";
								break;
							case Activity.RESULT_CANCELED:
								message = "SMS not delivered";
								break;                        
							}
							final String mess = message;
							if(!mess.equals("")){
								((Activity) fragmentContext).runOnUiThread(new Runnable() {

									@Override
									public void run() {
										Toast.makeText( fragmentContext, mess, 
												Toast.LENGTH_SHORT).show();
									}
								});
							}
						}
					}, new IntentFilter("delivery"));       			
					ArrayList<String> parts = smsMan.divideMessage(smsBody.getText().toString());
					ArrayList<PendingIntent> sendArray = new ArrayList<PendingIntent>();
					ArrayList<PendingIntent> deliveryArray = new ArrayList<PendingIntent>();
					for(int i = 0; i< (parts.size()-1);i++){
						sendArray.add(sentIntent);
						deliveryArray.add(deliveryIntent);
					}
					smsMan.sendMultipartTextMessage(smsNumber.getText().toString(), null, parts, null, null);

				}catch(IllegalArgumentException e){
					e.printStackTrace();

					((Activity) fragmentContext).runOnUiThread(new Runnable() {

						@Override
						public void run() {
							Toast.makeText( fragmentContext, "Error", Toast.LENGTH_SHORT).show();
						}
					});
					callback.finishedSendingSMS();

				}catch(Exception e){
					e.printStackTrace();

					((Activity) fragmentContext).runOnUiThread(new Runnable() {

						@Override
						public void run() {
							Toast.makeText( fragmentContext, "Error", Toast.LENGTH_SHORT).show();
						}
					});

					callback.finishedSendingSMS();
				}

			}
		}).start();
	}

}