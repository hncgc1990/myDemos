package com.hncgc1990.myrefreshview.Paint.patheffect;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposePathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017/2/14.
 * 圆形
 */
public class ComposeView extends View {


    private Paint mPaint;

    private Path mPath;

    Path path;

    public ComposeView(Context context) {
        super(context);
        init();
    }



    public ComposeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ComposeView(Context context, AttributeSet attrs, int defStyleAttr) {
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
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(1);
        mPaint.setPathEffect(new ComposePathEffect(new CornerPathEffect(10),new DashPathEffect(new float[]{4,4},0)));


        mPath=new Path();
        mPath.lineTo(200,200);

        mPath.lineTo(300,400);


        mPath.lineTo(400,300);


    }


    @Override
    protected void onDraw(Canvas canvas) {

//        canvas.drawPath(mPath,mPaint);
//        canvas.translate(100,0);
//        mPaint.setPathEffect(new ComposePathEffect(new DashPathEffect(new float[]{4,40},0),new CornerPathEffect(10)));
//        canvas.drawPath(mPath,mPaint);

        canvas.translate(50,0);
        mPaint.setPathEffect(new ComposePathEffect(new PathDashPathEffect(path,30,20, PathDashPathEffect.Style.MORPH),new CornerPathEffect(20)));
        canvas.drawPath(mPath,mPaint);


        canvas.translate(0,50);
        mPaint.setPathEffect(new ComposePathEffect(new CornerPathEffect(20),new PathDashPathEffect(path,30,20, PathDashPathEffect.Style.MORPH)));
        canvas.drawPath(mPath,mPaint);


    }
}
