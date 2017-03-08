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
public class WhtView extends View{




    public WhtView(Context context) {
        super(context);
        init();
    }



    public WhtView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public WhtView(Context context, AttributeSet attrs, int defStyleAttr) {
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

        rect=new RectF(-298,-298,298,298);
        rectOne=new RectF(-180,-180,180,180);

        mPath=new Path();
        mPath.moveTo(-296,0);
        mPath.quadTo(0,-230,296,0);
        mPath.quadTo(0,230,-296,0);






    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.translate(getWidth()/2,getHeight()/2);
        canvas.rotate(-30);



        canvas.drawCircle(0,0,300,mBlackPaint);

        canvas.drawCircle(0,0,296,mRedPaint);


        canvas.save();
        for(int i=0;i<3;i++){
            canvas.drawPath(mPath,mBlackPaint);
            canvas.rotate(120);
        }

        canvas.restore();


        canvas.drawCircle(0,0,20,mRedPaint);

        mRedPaint.setStyle(Paint.Style.STROKE);
        mRedPaint.setStrokeWidth(20);
        canvas.drawCircle(0,0,100,mRedPaint);



    }
}
