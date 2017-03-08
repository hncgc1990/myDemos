package com.hncgc1990.myrefreshview.scroller;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.animation.BounceInterpolator;
import android.widget.Scroller;
import android.widget.TextView;

/**
 * Created by zhaokaiqiang on 15/2/28.
 */
public class JellyTextView extends TextView {

	private Scroller mScroller;

	private float lastX;
	private float lastY;

	private float startX;
	private float startY;

	private VelocityTracker velocityTracker;

	public JellyTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
//		mScroller = new OverScroller(context, new BounceInterpolator());
		mScroller = new Scroller(context,new BounceInterpolator());
//		mScroller=new Scroller(context);
	}


	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if(velocityTracker==null){
			velocityTracker=VelocityTracker.obtain();
		}
		velocityTracker.addMovement(event);


		switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				lastX = event.getRawX();
				lastY = event.getRawY();



				break;
			case MotionEvent.ACTION_MOVE:
				float disX = event.getRawX() - lastX;
				float disY = event.getRawY() - lastY;

				offsetLeftAndRight((int) disX);
				offsetTopAndBottom((int) disY);

				Log.d("TAG", "getX()=" + getX() + "__getY()=" + getY());
				Log.d("TAG", "getTranslationX()=" + getTranslationX() + "__getTranslationY()=" + getTranslationY());
				Log.d("TAG", "getLeft()=" + getLeft() + "____getTop()=" + getTop());
				Log.d("TAG", "getPaddingLeft()=" + getPaddingLeft() + "____getPaddingRight()=" + getPaddingRight());

//				scrollBy((int)disX,(int)disY);
				velocityTracker.computeCurrentVelocity(1000);

				xVelocity = velocityTracker.getXVelocity();
				yVelocity = velocityTracker.getYVelocity();
				Log.d("TAG", "xVelocity()=" + xVelocity + "____yVelocity()=" + yVelocity);

				lastX = event.getRawX();
				lastY = event.getRawY();
				break;
			case MotionEvent.ACTION_UP:
				mScroller.startScroll((int) getX(), (int) getY(), -(int) (getX() - startX),
						-(int) (getY() - startY));
				invalidate();



//				if (mScroller.springBack((int)getX(), (int)getY(), 0,  0, 0,
//						0)){
//					Log.d("TAG", "getX()=" + getX() + "__getY()=" + getY());
//					Log.d("TAG", "getWidth()=" + getWidth() + "__getHeight()=" + getHeight());
//					invalidate();
//				}

				velocityTracker.clear();

				break;
		}

		return true;
	}
	float xVelocity;
	float yVelocity;

	@Override
	public void computeScroll() {

		if (mScroller.computeScrollOffset()) {
			setX(mScroller.getCurrX());
			setY(mScroller.getCurrY());
//			setTranslationX(mScroller.getCurrX()-getLeft());
//			setTranslationY(mScroller.getCurrY()-getTop());
			invalidate();
		}

	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		startX = getX();
		startY = getY();
		Log.d("TAG", "getX()=" + getX() + "__getY()=" + getY());
	}

	public void spingBack() {

//		if (mScroller.springBack((int) getX(), (int) getY(), 0, (int) getX(), 0,
//				(int) getY() - 100)) {
//			Log.d("TAG", "getX()=" + getX() + "__getY()=" + getY());
//			invalidate();
//		}

	}

	public void fling(){
//		mScroller.fling((int)getX(),(int)getY(),(int)xVelocity,(int)yVelocity,0,10,0,10,100,100);
//		invalidate();
	}


}