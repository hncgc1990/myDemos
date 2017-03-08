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
 * Canvas进行 平移
 */
public class CanvasTranslateView extends View {


    private Paint mPaint;

    private RectF mRect;
    private RectF mRectTwo;

    public CanvasTranslateView(Context context) {
        super(context);
        init();
    }



    public CanvasTranslateView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CanvasTranslateView(Context context, AttributeSet attrs, int defStyleAttr) {
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

       canvas.translate(100,100);

       canvas.drawCircle(0,0,100,mPaint);

        canvas.translate(100,100);

        canvas.drawCircle(0,0,100,mPaint);
    }
}
