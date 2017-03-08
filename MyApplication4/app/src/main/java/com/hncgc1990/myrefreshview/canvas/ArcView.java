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
 * 圆弧
 */
public class ArcView extends View {


    private Paint mPaint;

    private RectF mRect;
    private RectF mRectTwo;

    public ArcView(Context context) {
        super(context);
        init();
    }



    public ArcView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ArcView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.FILL);

        mRect=new RectF(100,100,500,500);
        mRectTwo=new RectF(100,600,500,1000);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
       canvas.drawArc(mRect,-90,90,true,mPaint);
       canvas.drawArc(mRectTwo,-90,90,false,mPaint);
    }
}
