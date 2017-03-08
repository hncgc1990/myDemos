package com.hncgc1990.myrefreshview.event;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.hncgc1990.myrefreshview.R;

/**
 * Created by Administrator on 2017/2/14.
 * 圆形
 */
public class EventJusView extends View {


    private Paint mPaint;

    private RectF mRect;
    private RectF mRectTwo;

    private Bitmap bitmap;

    private float scale;

    private Matrix matrix;


    private Region mCircleReginon;

    public EventJusView(Context context) {
        super(context);
        init();
    }



    public EventJusView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EventJusView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int size = MeasureSpec.getSize(widthMeasureSpec);



        int defaultSize=2*100+getPaddingLeft()+getPaddingRight();

        int result =0;

        switch (mode){
            case MeasureSpec.AT_MOST:
                Log.d("chen","当前的mode__MeasureSpec.AT_MOST");
                result=Math.min(size,defaultSize);
                break;
            case MeasureSpec.EXACTLY:
                result=size;
                Log.d("chen","当前的mode__MeasureSpec.EXACTLY");
                break;
            case MeasureSpec.UNSPECIFIED:
                result=defaultSize;
                Log.d("chen","当前的mode__MeasureSpec.UNSPECIFIED");
                break;
            default:
                break;

        }

        setMeasuredDimension(result,result);


    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mCircleReginon=new Region();

        Region gloable=new Region(0,0,w,h);

        path.addCircle(100+getPaddingLeft(),100+getPaddingTop(),100, Path.Direction.CW);


        boolean bool = mCircleReginon.setPath(path, gloable);


        Log.d("chen","截取到了对应的区域了？"+bool);
    }

    Path path=new Path();

    private void init() {
        mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.argb(255, 255, 128, 103));
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(2);
        mRect=new RectF(0,0,200,200);
        mRectTwo=new RectF(100,400,500,600);


        mPaint.setColorFilter(new ColorMatrixColorFilter(new float[]{
                0.5f,0,0,0,0,
                0,0.5f,0,0,0,
                0,0,0.5f,0,0,
                0,0,0,0.5f,0
        }));

        bitmap= BitmapFactory.decodeResource(getResources(), R.drawable.ssss);
        matrix=new Matrix();


    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


//        canvas.translate(getWidth()/2,getHeight()/2);
        canvas.drawPath(path,mPaint);



    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int action = event.getAction();


        switch (action){
            case MotionEvent.ACTION_DOWN:

                float x = event.getX();

                float y = event.getY();

                if(mCircleReginon.contains((int)x,(int)y)){
                    Log.d("chen","该位置在圆中_____x_____"+x+"________y_____"+y);
                }


                break;
        }

        return true;
    }
}
