package com.hncgc1990.myrefreshview.canvas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.hncgc1990.myrefreshview.R;

/**
 * Created by Administrator on 2017/2/14.
 * Canvas进行旋转
 */
public class CanvasImageView extends View {


    private Paint mPaint;

    private Rect mRect;
    private RectF mRectTwo;

    private Bitmap mBitmap;

    public CanvasImageView(Context context) {
        super(context);
        init();
    }



    public CanvasImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CanvasImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private float scale;

    private Matrix matrix;

    private RectF mRectThree;
    private void init() {
        //关闭硬件加速
        setLayerType(View.LAYER_TYPE_SOFTWARE,null);
        mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);

        mRect=new Rect(500,0,970,430);
        mRectTwo=new RectF(0,0,400,420);
        mRectThree=new RectF(400,420,500,520);
        mBitmap= BitmapFactory.decodeResource(getResources(), R.drawable.ssss);




    }

    private int currentAngle=0;


    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        scale =((float) 200)/mBitmap.getWidth();
        Log.d("chen",getWidth()+"比例是:"+scale);




        matrix=new Matrix();
        matrix.setScale(scale,scale);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        canvas.save();
        canvas.translate(getWidth()/2,getHeight()/2);

        canvas.drawBitmap(mBitmap,matrix,mPaint);

        canvas.restore();

        canvas.drawBitmap(mBitmap,mRect,mRectTwo,mPaint);

        canvas.drawBitmap(mBitmap,mRect,mRectThree,mPaint);
    }


    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            currentAngle+=1;
            invalidate();
            handler.sendEmptyMessageDelayed(1,10);
        }
    };


    public void startAnim(){
        handler.sendEmptyMessageDelayed(1,10);
    }
}
