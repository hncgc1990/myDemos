package com.hncgc1990.myrefreshview.Paint.shaowlayer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017/2/14.
 * 圆形
 */
public class ShadowlayerView extends View {


    private Paint mPaint;

    private Path mPath;

    Path path;

    public ShadowlayerView(Context context) {
        super(context);
        init();
    }



    public ShadowlayerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ShadowlayerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {



        mPaint=new Paint(Paint.ANTI_ALIAS_FLAG|Paint.DITHER_FLAG);
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(1);
//        mPaint.setPathEffect(new ComposePathEffect(new CornerPathEffect(10),new DashPathEffect(new float[]{4,4},0)));
        mPaint.setShadowLayer(10,0,0,Color.GRAY);


    }


    @Override
    protected void onDraw(Canvas canvas) {

    canvas.drawRect(100,100,400,400,mPaint);



    }
}
