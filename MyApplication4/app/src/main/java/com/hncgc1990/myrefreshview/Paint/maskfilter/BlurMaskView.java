package com.hncgc1990.myrefreshview.Paint.maskfilter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017/2/14.
 * 圆形
 */
public class BlurMaskView extends View {


    private Paint mPaint;

    private RectF mRect;
    private RectF mRectTwo;

    private Bitmap bitmap;

    private float scale;

    private Matrix matrix;

    public BlurMaskView(Context context) {
        super(context);
        init();
    }



    public BlurMaskView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BlurMaskView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.argb(255, 255, 128, 103));
        mPaint.setStyle(Paint.Style.FILL);






        mRect=new RectF(100,100,200,200);


    }


    @Override
    protected void onDraw(Canvas canvas) {
//       canvas.translate(getWidth()/2,getHeight()/2);
        mPaint.setMaskFilter(new BlurMaskFilter(20, BlurMaskFilter.Blur.NORMAL));
        canvas.drawRect(mRect,mPaint);

        canvas.translate(0,120);
        mPaint.setMaskFilter(new BlurMaskFilter(20, BlurMaskFilter.Blur.INNER));
        canvas.drawRect(mRect,mPaint);

        canvas.translate(0,120);
        mPaint.setMaskFilter(new BlurMaskFilter(20, BlurMaskFilter.Blur.OUTER));
        canvas.drawRect(mRect,mPaint);

        canvas.translate(0,120);
        mPaint.setMaskFilter(new BlurMaskFilter(20, BlurMaskFilter.Blur.SOLID));
        canvas.drawRect(mRect,mPaint);

    }
}
