package com.hncgc1990.myrefreshview.xielunyan;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017/2/23.
 */
public class SgyView extends View{




    public SgyView(Context context) {
        super(context);
        init();
    }



    public SgyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SgyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    Paint mRedPaint;

    Paint mBlackPaint;
    RectF rect;
    RectF rectOne;


    Path mPath;


    Path mPathOne;

    private void init() {

        mRedPaint=new Paint(Paint.ANTI_ALIAS_FLAG|Paint.DITHER_FLAG);
        mRedPaint.setColor(Color.parseColor("#ed1b24"));
        mRedPaint.setStyle(Paint.Style.FILL);

        mBlackPaint=new Paint(Paint.ANTI_ALIAS_FLAG|Paint.DITHER_FLAG);
        mBlackPaint.setColor(Color.parseColor("#151716"));
        mBlackPaint.setStyle(Paint.Style.FILL);

        rect=new RectF(-100,-100,100,100);
        rectOne=new RectF(-180,-180,180,180);

        mPath=new Path();
        mPath.addArc(rect,0,360);
        mPath.setLastPoint(160,0);






    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.translate(getWidth()/2,getHeight()/2);

        canvas.drawPath(mPath,mRedPaint);

    }
}
