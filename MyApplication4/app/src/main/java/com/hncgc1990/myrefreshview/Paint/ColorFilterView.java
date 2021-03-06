package com.hncgc1990.myrefreshview.Paint;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.hncgc1990.myrefreshview.R;

/**
 * Created by Administrator on 2017/2/14.
 * 圆形
 */
public class ColorFilterView extends View {


    private Paint mPaint;

    private RectF mRect;
    private RectF mRectTwo;

    private Bitmap bitmap;

    private float scale;

    private Matrix matrix;

    public ColorFilterView(Context context) {
        super(context);
        init();
    }



    public ColorFilterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ColorFilterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.argb(255, 255, 128, 103));
        mPaint.setStyle(Paint.Style.FILL);

        mRect=new RectF(100,100,500,300);
        mRectTwo=new RectF(100,400,500,600);


        mPaint.setColorFilter(new ColorMatrixColorFilter(new float[]{
                0.5f,0,0,0,0,
                0,0.5f,0,0,0,
                0,0,0.5f,0,0,
                0,0,0,0.5f,0
        }));

        bitmap= BitmapFactory.decodeResource(getResources(), R.drawable.ssss);
        matrix=new Matrix();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        scale=(float) getWidth()/bitmap.getWidth();
        matrix.setScale(scale,scale);

//       canvas.drawCircle(getWidth()/2,getHeight()/2,100,mPaint);
        canvas.drawBitmap(bitmap,matrix,mPaint);
    }
}
