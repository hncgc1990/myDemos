package com.hncgc1990.myrefreshview.Paint.patheffect;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017/2/14.
 * 圆形
 */
public class DiscretePathView extends View {


    private Paint mPaint;

    private Path mPath;



    public DiscretePathView(Context context) {
        super(context);
        init();
    }



    public DiscretePathView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DiscretePathView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        mPaint=new Paint(Paint.ANTI_ALIAS_FLAG|Paint.DITHER_FLAG);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(4);
        mPaint.setPathEffect(new DiscretePathEffect(5,10));


        mPath=new Path();
        mPath.lineTo(200,200);

        mPath.lineTo(300,400);


        mPath.lineTo(400,300);


    }


    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawPath(mPath,mPaint);
        canvas.translate(0,50);
        mPaint.setPathEffect(new DiscretePathEffect(10,10));
        canvas.drawPath(mPath,mPaint);

        canvas.translate(0,50);
        mPaint.setPathEffect(new DiscretePathEffect(15,10));
        canvas.drawPath(mPath,mPaint);


        canvas.translate(0,50);
        mPaint.setPathEffect(new DiscretePathEffect(20,10));
        canvas.drawPath(mPath,mPaint);


    }
}
