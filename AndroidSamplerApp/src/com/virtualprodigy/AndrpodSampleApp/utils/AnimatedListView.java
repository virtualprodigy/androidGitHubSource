package com.virtualprodigy.AndrpodSampleApp.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

public class AnimatedListView extends ListView{

	private Camera mCamera = new Camera();
	private Matrix mMatrix = new Matrix();

	public AnimatedListView(Context context) {
		super(context);
	}

	public AnimatedListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public AnimatedListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
		Bitmap bitmap = child.getDrawingCache();
		if(bitmap == null){
			child.setDrawingCacheEnabled(true);
			child.buildDrawingCache();
			bitmap = child.getDrawingCache();
		}
		int left = child.getLeft();
		int top = child.getTop();

		// get offset to center
		int centerX = child.getWidth() / 2;
		int centerY = child.getHeight() / 2;

		// get absolute center of child
		float pivotX = left + centerX;
		float pivotY = top + centerY;

		// calculate distance from center
		float centerScreen = getHeight() / 2;
		float distFromCenter = (pivotY - centerScreen) / centerScreen;

		// calculate scale and rotation
		float scale = (float)(1 - 2 * (1 - Math.cos(distFromCenter)));
		if(scale < 0.5f)
			scale = 0.5f; //i dont want them too small
		if(child == getChildAt(0));
		Log.d("Tweeking the 3d list view", "scale is" + scale);//use this to set a minimum scale down
		float rotation =distFromCenter;

		canvas.save();
		//	        canvas.rotate(rotation, pivotX, pivotY);// i want caleing via the camera not a rotation
		canvas.scale(scale, scale, pivotX, pivotY);
		Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setFilterBitmap(true);


		if (mCamera == null) {
			mCamera = new Camera();
		}
		mCamera.save();
		mCamera.rotateY(rotation);

		if (mMatrix == null) {
			mMatrix = new Matrix();
		}
		mCamera.getMatrix(mMatrix);
		mCamera.restore();

		mMatrix.preTranslate(-centerX, -centerY);
		mMatrix.postScale(scale, scale);
		mMatrix.postTranslate(pivotX, pivotY);

		canvas.drawBitmap(bitmap, left, top, paint);
		canvas.restore();
		return false;
	}
}