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
public class CanvasScaleView extends View {


    private Paint mPaint;

    private RectF mRect;
    private RectF mRectTwo;

    public CanvasScaleView(Context context) {
        super(context);
        init();
    }



    public CanvasScaleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CanvasScaleView(Context context, AttributeSet attrs, int defStyleAttr) {
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


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(getWidth()/2,getHeight()/2);
        canvas.save();
        for(int i=0;i<20;i++){
            canvas.drawCircle(0,0,200,mPaint);

            canvas.scale(0.9f,0.9f);
        }

        canvas.restore();
        canvas.save();
        canvas.rotate(45);
        for(int i=0;i<4;i++){
            canvas.drawLine((float)(200*Math.pow(0.9,20)*Math.sqrt(2)),0,(float)(200*Math.sqrt(2)),0,mPaint);
            canvas.rotate(90);
        }

        canvas.restore();
        for(int i=0;i<20;i++){
            canvas.drawRect(-200,-200,200,200,mPaint);

            canvas.scale(0.9f,0.9f);
        }


    }
}
