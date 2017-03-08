package com.hncgc1990.myrefreshview.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017/2/14.
 * 圆角矩形
 */
public class RoundRectView extends View {


    private Paint mPaint;

    private RectF mRect;
    private RectF mRectTwo;

    public RoundRectView(Context context) {
        super(context);
        init();
    }



    public RoundRectView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RoundRectView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.FILL);

        mRect=new RectF(100,100,500,300);
        mRectTwo=new RectF(100,400,500,600);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRoundRect(mRect,200,100,mPaint);
        canvas.drawRoundRect(mRectTwo,10,10,mPaint);
    }
}
