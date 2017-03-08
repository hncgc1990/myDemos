package com.hncgc1990.myrefreshview.event;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Administrator on 2017/2/14.
 * 多点触控
 */
public class EventMultiPointView extends View {



    private RectF mRect;
    private RectF mRectTwo;

    private Bitmap bitmap;

    private float scale;

    private Matrix matrix;


    private Region mCircleReginon;

    public EventMultiPointView(Context context) {
        super(context);
        init();
    }




    public EventMultiPointView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EventMultiPointView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }



    Path path;

    Paint mPaint;
    private void init() {
        path =new Path();
        mPaint=new Paint();
        mPaint.setColor(Color.GREEN);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(3);
    }

    boolean hasSecondFinger=false;

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int action = event.getActionMasked();
        float rawX = event.getX();

        float rawY = event.getY();


        int actionIndex = event.getActionIndex();


        switch (action){
            case MotionEvent.ACTION_DOWN:

                Log.d("chen","ACTION_DOWN__actionIndex____"+actionIndex+"____PointerId_____"+event.getPointerId(actionIndex));
                break;

            case MotionEvent.ACTION_UP:
                Log.d("chen","ACTION_UP__actionIndex____"+actionIndex+"____PointerId_____"+event.getPointerId(actionIndex));
                if(event.getPointerId(actionIndex)==1){//第二个手指
                    hasSecondFinger=false;
                    path.reset();
                }
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                Log.d("chen","ACTION_POINTER_DOWN__actionIndex____"+actionIndex+"____PointerId_____"+event.getPointerId(actionIndex));



                if(event.getPointerId(actionIndex)==1){//第二个手指
                    hasSecondFinger=true;
                    int pointerIndex = event.findPointerIndex(1);
                    float x = event.getX(pointerIndex);
                    float y = event.getY(pointerIndex);
                    path.moveTo(x,y);
                }

                break;
            case MotionEvent.ACTION_POINTER_UP:
                Log.d("chen","ACTION_POINTER_UP__actionIndex____"+actionIndex+"____PointerId_____"+event.getPointerId(actionIndex));
                if(event.getPointerId(actionIndex)==1){//第二个手指
                    hasSecondFinger=false;
                    path.reset();
                }
                break;
            case MotionEvent.ACTION_MOVE:
//                Log.d("chen","ACTION_MOVE__actionIndex____"+actionIndex+"____PointerId_____"+event.getPointerId(actionIndex));
                if(hasSecondFinger){
                    int pointerIndex = event.findPointerIndex(1);
                    float x = event.getX(pointerIndex);
                    float y = event.getY(pointerIndex);
                    path.lineTo(x,y);
                    invalidate();
                }
                break;
        }

        return true;
    }


    @Override
    protected void onDraw(Canvas canvas) {

        if(path.isEmpty()) return;

        canvas.drawPath(path,mPaint);
    }
}
