package com.hncgc1990.myrefreshview.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017/2/14.
 * Canvas进行旋转
 */
public class CanvasRotateView extends View {


    private Paint mPaint;

    private RectF mRect;
    private RectF mRectTwo;

    public CanvasRotateView(Context context) {
        super(context);
        init();
    }



    public CanvasRotateView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CanvasRotateView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);

        mRect=new RectF(100,100,500,300);
        mRectTwo=new RectF(100,400,500,600);
    }

    private int currentAngle=0;


    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        startAnim();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(getWidth()/2,getHeight()/2);


        for(int i=0;i<5;i++){
            canvas.drawCircle(0,0,200-i*20,mPaint);
        }

        for(int j=0;j<2;j++){

            canvas.save();
            canvas.rotate(currentAngle);
            for(int i=0;i<36;i++){

                canvas.drawLine(180-j*40,0,200-j*40,0,mPaint);//200 160 120 80 40
                canvas.rotate(10);//
            }
            canvas.restore();
            canvas.save();
            canvas.rotate(5);
            for(int i=0;i<36;i++){

                canvas.drawLine(160-j*40,0,180-j*40,0,mPaint);
                canvas.rotate(10);
            }
            canvas.restore();
        }



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
