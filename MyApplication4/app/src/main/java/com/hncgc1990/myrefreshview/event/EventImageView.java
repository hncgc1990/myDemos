package com.hncgc1990.myrefreshview.event;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.hncgc1990.myrefreshview.R;

/**
 * Created by Administrator on 2017/2/14.
 * 圆形
 */
public class EventImageView extends View {


    private Paint mPaint;
    private Paint mPressPaint;

    private RectF mRect;
    private RectF mRectTwo;

    private Bitmap bitmap;

    private float scale;

    private Matrix matrix;


    private Region mCircleReginon;

    public EventImageView(Context context) {
        super(context);
        init();
    }



    public EventImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EventImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }




    Path path=new Path();

    private void init() {
        mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.argb(255, 255, 128, 103));
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(2);



        mPressPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        mPressPaint.setColor(Color.RED);
        mPressPaint.setStyle(Paint.Style.FILL);
        mPressPaint.setStrokeWidth(2);

        mRectTwo=new RectF(100,400,500,600);


        mPaint.setColorFilter(new LightingColorFilter(0xFFFFFF,0x800000));

        bitmap= BitmapFactory.decodeResource(getResources(), R.drawable.star);
        matrix=new Matrix();
        matrix.setScale(0.2f,0.2f);




    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(bitmap,matrix,mPaint);


        canvas.translate(0,getHeight()/2);


        canvas.drawBitmap(bitmap,matrix, mPressPaint);
    }



    float lastx=0;

    float lasty=0;

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int action = event.getAction();

        float rawX = event.getRawX();
        float rawY = event.getRawY();

        switch (action){
            case MotionEvent.ACTION_DOWN:

                lastx=rawX;
                lasty=rawY;


                break;
            case MotionEvent.ACTION_MOVE:
                float distanceX=rawX-lastx;
                float distanceY=rawY-lasty;

                lastx=rawX;
                lasty=rawY;


                matrix.postTranslate(distanceX,distanceY);
                invalidate();
                break;

            case MotionEvent.ACTION_UP:

                break;
        }


        return true;
    }
}
