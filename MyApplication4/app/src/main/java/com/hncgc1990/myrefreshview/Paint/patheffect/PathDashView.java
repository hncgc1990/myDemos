package com.hncgc1990.myrefreshview.Paint.patheffect;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017/2/14.
 * 圆形
 */
public class PathDashView extends View {


    private Paint mPaint;

    private Path mPath;

    Path path;

    public PathDashView(Context context) {
        super(context);
        init();
    }



    public PathDashView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PathDashView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        path=new Path();
//        path.addCircle(0,0,10, Path.Direction.CW);
        path.lineTo(0,20);
        path.lineTo(10,10);
        path.close();



        mPaint=new Paint(Paint.ANTI_ALIAS_FLAG|Paint.DITHER_FLAG);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(1);
        mPaint.setPathEffect(new PathDashPathEffect(path,30,0, PathDashPathEffect.Style.TRANSLATE));


        mPath=new Path();
        mPath.lineTo(200,200);

        mPath.lineTo(300,400);


        mPath.lineTo(400,300);


    }


    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawPath(mPath,mPaint);
        canvas.translate(0,50);
        mPaint.setPathEffect(new PathDashPathEffect(path,30,10, PathDashPathEffect.Style.ROTATE));
        canvas.drawPath(mPath,mPaint);

        canvas.translate(0,50);
        mPaint.setPathEffect(new PathDashPathEffect(path,30,20, PathDashPathEffect.Style.MORPH));
        canvas.drawPath(mPath,mPaint);

//
//        canvas.translate(0,50);
//        mPaint.setPathEffect(new DiscretePathEffect(20,10));
//        canvas.drawPath(mPath,mPaint);


    }
}
