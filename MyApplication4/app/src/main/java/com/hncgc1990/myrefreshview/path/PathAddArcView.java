package com.hncgc1990.myrefreshview.path;

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
public class PathAddArcView extends View {


    private Paint mPaint;

    private RectF mRect;
    private RectF mRectTwo;

    private Bitmap bitmap;

    private float scale;

    private Matrix matrix;

    public PathAddArcView(Context context) {
        super(context);
        init();
    }



    public PathAddArcView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PathAddArcView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    Path path=new Path();

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





    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        scale=(float) getWidth()/bitmap.getWidth();
//        matrix.setScale(scale,scale);

//       canvas.drawCircle(getWidth()/2,getHeight()/2,100,mPaint);
//        canvas.drawBitmap(bitmap,matrix,mPaint);






        canvas.translate(getWidth()/2,getHeight()/2);


        canvas.drawLine(-getWidth()/2,0,getWidth()/2,0,mPaint);
        canvas.drawLine(0,-getHeight()/2,0,getHeight()/2,mPaint);


        canvas.scale(1,-1);

        path.lineTo(100,100);

//       path.addArc(mRect,0,90);//0度是左边的水平位置,顺时针方向为正向.

        path.arcTo(mRect,0,90);//arcTo是会将上一步的操作点和圆弧的起始点连接,而addArc则不会连接.
        path.close();

        canvas.drawPath(path,mPaint);
        path.offset(100,0);
        canvas.drawPath(path,mPaint);


    }
}
