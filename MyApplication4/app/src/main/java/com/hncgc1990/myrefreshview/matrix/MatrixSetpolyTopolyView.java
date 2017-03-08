package com.hncgc1990.myrefreshview.matrix;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.hncgc1990.myrefreshview.R;

/**
 * Created by Administrator on 2017/2/14.
 * 圆形
 */
public class MatrixSetpolyTopolyView extends View {


    private Paint mPaint;

    private RectF mRect;
    private RectF mRectTwo;

    private Bitmap bitmap;

    private float scale;

    private Matrix matrix;

    public MatrixSetpolyTopolyView(Context context) {
        super(context);
        init();
    }



    public MatrixSetpolyTopolyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MatrixSetpolyTopolyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    Path path=new Path();

    Matrix matrix2;

    private void init() {
        mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.argb(255, 255, 128, 103));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(2);
        mRect=new RectF(0,0,200,200);
        mRectTwo=new RectF(100,400,500,600);


        mPaint.setColorFilter(new ColorMatrixColorFilter(new float[]{
                0.5f,0,0,0,0,
                0,0.5f,0,0,0,
                0,0,0.5f,0,0,
                0,0,0,0.5f,0
        }));

        bitmap= BitmapFactory.decodeResource(getResources(), R.drawable.ssss);
        matrix=new Matrix();
//        matrix.setScale(0.2f,0.2f);


        float[] src=new float[]{
                0,0,
                bitmap.getWidth(),0,
                bitmap.getWidth(),bitmap.getHeight(),
                0,bitmap.getHeight()
        };

        float[] des=new float[]{
                0,0,
                bitmap.getWidth(),bitmap.getHeight()/4,
                bitmap.getWidth(),bitmap.getHeight()*3/4,
                0,bitmap.getHeight()
        };


        matrix.setPolyToPoly(src,0,des,0,4);

        matrix.postScale(0.2f,0.2f);


        matrix2=new Matrix();

        float[] src2=new float[]{
                0,0,
                bitmap.getWidth(),0,
                bitmap.getWidth(),bitmap.getHeight(),
                0,bitmap.getHeight()
        };
        float[] des2=new float[]{
                0,0,
                bitmap.getWidth(),0,
                bitmap.getWidth()+200,bitmap.getHeight()+200,
                0,bitmap.getHeight()
        };
        matrix2.setPolyToPoly(src2,0,des2,0,4);
        matrix2.postScale(0.2f,0.2f);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        canvas.drawBitmap(bitmap,matrix,null);


        canvas.translate(0,getHeight()/2);


        canvas.drawBitmap(bitmap,matrix2,null);




    }
}
