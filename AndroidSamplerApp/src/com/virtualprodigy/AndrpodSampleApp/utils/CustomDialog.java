package com.virtualprodigy.AndrpodSampleApp.utils;
import comvirtualprodigy.androidsamplerapp.R;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

public class CustomDialog {

	Context context;
	Resources res;
	Dialog dialog;
	View view;
	TextView title;
	TextView message;
	Button accept;
	Button cancel;
	Button neutral;
	LinearLayout contentView;
	LinearLayout buttonlayout;
	LinearLayout neutralBttns;
	ProgressBar progressBar;

	public CustomDialog(Context context, boolean useRelativeLayout){
		this.context = context;
		res = this.context.getResources();
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if(useRelativeLayout){
			view = inflater.inflate(R.layout.custom_alertdialog_relativelayout, null);
		}else{
			view = inflater.inflate(R.layout.custom_alertdialog, null);
		}
		title = (TextView) view.findViewById(R.id.title);
		message = (TextView) view.findViewById(R.id.message);
		accept = (Button) view.findViewById(R.id.accept);
		cancel = (Button) view.findViewById(R.id.cancel);
		neutral = (Button) view.findViewById(R.id.neutral);
		contentView = (LinearLayout) view.findViewById(R.id.contentView);
		buttonlayout = (LinearLayout) view.findViewById(R.id.buttonlayout);
		neutralBttns = (LinearLayout) view.findViewById(R.id.buttonlayoutWithNeutral);
		progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
	}

	public boolean createDialog(boolean cancelable){


		DisplayMetrics metrics = res.getDisplayMetrics();
		int widthOfDialog = (int) (metrics.widthPixels * 0.95);//fill 95 percent of the screen like a normal dialog

		//		dialog = new Dialog(new ContextThemeWrapper(context, android.R.style.Theme_DeviceDefault_Dialog));
		dialog = new Dialog(new ContextThemeWrapper(context, R.style.custom_dialog_theme));// you need a theme to style the dialog properly
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);//remove the headers from the usual dialog
		dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00000000")));
		dialog.setContentView(view);
		dialog.getWindow().setLayout(widthOfDialog, LinearLayout.LayoutParams.WRAP_CONTENT);
		dialog.setCancelable(cancelable);
		dialog.setCanceledOnTouchOutside(cancelable);
		return (dialog != null);
		//android:layout_above="@+id/buttonlayoutWithNeutral"
		//android:layout_above="@+id/buttonlayout"
	}

	/**
	 * forces the dialog to be a width of 90% and the height of 60% of the screen
	 */
	public void dialogSizeRule95_60(){
		DisplayMetrics metrics = res.getDisplayMetrics();
		int widthOfDialog = (int) (metrics.widthPixels * 0.95);
		int heightOfDialog = (int) (metrics.heightPixels * 0.60);
		dialog.getWindow().setLayout(widthOfDialog, heightOfDialog);
	}

	public void setTitle(String text){
		title.setText(text);
	}

	public void setMessage(String text){
		message.setText(text);
	}

	/**
	 * This call must be made before setting up titls and listeners for accept and cancel buttons as these 
	 * buttons will be changed
	 */
	public void enableNeutralBttns(){
		accept = (Button) view.findViewById(R.id.neu_accept);
		cancel = (Button) view.findViewById(R.id.neu_cancel);
		buttonlayout.setVisibility(View.GONE);
		neutralBttns.setVisibility(View.VISIBLE);
	}

	/**
	 * hides the title and message boxes
	 * @param hideTitle
	 * @param hideMessage
	 */
	public void hideTitle_Message(boolean hideTitle, boolean hideMessage){
		if(hideTitle){
			title.setVisibility(View.GONE);
		}else{
			title.setVisibility(View.VISIBLE);
		}
		if(hideMessage){
			message.setVisibility(View.GONE);
		}else{
			message.setVisibility(View.VISIBLE);
		}
	}

	/**
	 * adds a view to the content area of the alert dialog
	 * @param content
	 */
	public void setView(View content){
		contentView.addView(content);
		contentView.setVisibility(View.VISIBLE);
	}


	/**
	 * this sets the listener for the positive button. you must give a text for the display
	 * passing a null listener creates a default listen to just dismiss the dialog
	 * @param text
	 * @param listener
	 */
	public void setPositiveListener(String text, View.OnClickListener listener){
		//	accept.setOnClickListener(new View.OnClickListener() {
		//		
		//		@Override
		//		public void onClick(View v) {
		//			// TODO Auto-generated method stub
		//			
		//		}
		//	});
		accept.setText(text);

		if(listener != null){
			accept.setOnClickListener(listener);	
		}else{
			accept.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					dialog.dismiss();
				}
			});

		}
	}

	public void setProgressDialog(int total,boolean isIndeterminate){
		progressBar.setVisibility(View.VISIBLE);
		progressBar.setIndeterminate(isIndeterminate);
		if(!isIndeterminate){
			progressBar.setMax(total);
			progressBar.setProgress(0);
		}
	}

	public void updateDeterminateProgressDialog(int complete){

	}

	/**
	 * this sets the listener for the neutral button. you must give a text for the display
	 * passing a null listener creates a default listen to just dismiss the dialog
	 * @param text
	 * @param listener
	 */
	public void setNeutralListener(String text, View.OnClickListener listener){

		neutral.setText(text);

		if(listener != null){
			neutral.setOnClickListener(listener);	
		}else{
			neutral.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					dialog.dismiss();
				}
			});

		}
	}

	/**
	 * this sets the listener for the cancel button. you must give a text for the display
	 * passing a null listener creates a default listen to just dismiss the dialog
	 * @param text
	 * @param listener
	 */
	public void setCancelListener(String text, View.OnClickListener listener){

		cancel.setText(text);

		if(listener != null){
			cancel.setOnClickListener(listener);	
		}else{
			cancel.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					dialog.dismiss();
				}
			});

		}
	}

	public void show(){
		dialog.show();
	}

	public void dismiss(){
		dialog.dismiss();
	}

	public boolean isShowing(){
		return dialog.isShowing();
	}
}

