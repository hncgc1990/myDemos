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
public class CanvasSkewView extends View {


    private Paint mPaint;

    private RectF mRect;
    private RectF mRectTwo;

    public CanvasSkewView(Context context) {
        super(context);
        init();
    }



    public CanvasSkewView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CanvasSkewView(Context context, AttributeSet attrs, int defStyleAttr) {
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


        canvas.skew((float)Math.sqrt(3)/3,0);//第一个参数为向x轴靠拢的角度的tan值
        canvas.drawLine(-getWidth()/2,0,getWidth()/2,0,mPaint);
        canvas.drawLine(0,-getHeight()/2,0,getHeight()/2,mPaint);


        canvas.drawLine(0,100,100,0,mPaint);





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
