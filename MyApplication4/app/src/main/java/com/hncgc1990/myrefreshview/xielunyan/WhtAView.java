package com.hncgc1990.myrefreshview.xielunyan;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017/2/23.
 */
public class WhtAView extends View{




    public WhtAView(Context context) {
        super(context);
        init();
    }



    public WhtAView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public WhtAView(Context context, AttributeSet attrs, int defStyleAttr) {
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

        rect=new RectF(-200,-100,200,300);
        rectOne=new RectF(-180,-180,180,180);

        mPath=new Path();
        mPath.addArc(rect,-90,50);

        float[] pos=new float[2];
        PathMeasure measure=new PathMeasure(mPath,false);
        measure.getPosTan(measure.getLength(),pos,null);

        mPath.quadTo(-200,-100,0,100);






    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.translate(getWidth()/2,getHeight()/2);

        canvas.drawCircle(0,0,100,mRedPaint);
        canvas.drawPath(mPath,mRedPaint);
//        canvas.drawArc(rect,-90,60,false,mRedPaint);


    }
}
