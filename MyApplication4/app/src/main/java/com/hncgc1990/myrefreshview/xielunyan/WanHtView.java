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
public class WanHtView extends View{




    public WanHtView(Context context) {
        super(context);
        init();
    }



    public WanHtView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public WanHtView(Context context, AttributeSet attrs, int defStyleAttr) {
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
        mPath.moveTo(80,0);
        mPath.arcTo(rect,-60,120,false);
        mPath.close();


        PathMeasure pathMeasure=new PathMeasure(mPath,false);

        float radius = (float) ((pathMeasure.getLength() - 298 * 2 * Math.PI/3)/2);

        float[] pos=new float[2];

        pathMeasure.getPosTan(radius/2,pos,null);

        mPathOne=new Path();
        mPathOne.moveTo(pos[0],pos[1]);
        mPathOne.arcTo(rectOne,-60,120);

        pathMeasure.getPosTan(radius,pos,null);

        mPathOne.quadTo(pos[0]+210,pos[1]+280,pos[0],pos[1]);
        mPathOne.close();






    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.translate(getWidth()/2,getHeight()/2);
        canvas.rotate(-30);

        canvas.drawCircle(0,0,300,mBlackPaint);

        canvas.save();
        for(int i=0;i<3;i++){
            canvas.drawPath(mPath,mRedPaint);
            canvas.rotate(120);
        }
        canvas.restore();


        canvas.drawCircle(0,0,36,mRedPaint);

        canvas.save();
        for(int i=0;i<3;i++){
            canvas.drawPath(mPathOne,mBlackPaint);
            canvas.rotate(120);
        }
        canvas.restore();




    }
}
