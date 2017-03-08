package com.hncgc1990.myrefreshview.matrix;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Administrator on 2017/2/14.
 * 圆形
 */
public class MatrixSetpolyTopolyDemoView extends View {


    private Paint mPaint;

    private RectF mRect;
    private RectF mRectTwo;

    private Bitmap bitmap;

    private float scale;


    public MatrixSetpolyTopolyDemoView(Context context) {
        super(context);
        init();
    }



    public MatrixSetpolyTopolyDemoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MatrixSetpolyTopolyDemoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    Path path1=new Path();
    Path path2=new Path();

    Path path3=new Path();
    Path path4=new Path();


    private Paint mPaintTwo;

    Matrix matrix;
    Matrix matrixTwo;


    float[] leftTop=new float[2];
    float[] rightTop=new float[2];
    float[] rightBottom=new float[2];
    float[] leftBottom=new float[2];


    float[] leftTopTwo=new float[2];
    float[] rightTopTwo=new float[2];
    float[] rightBottomTwo=new float[2];
    float[] leftBottomTwo=new float[2];


    private void init() {
        mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.argb(255, 255, 128, 103));
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(2);


        mPaintTwo=new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintTwo.setColor(Color.GREEN);
        mPaintTwo.setStyle(Paint.Style.FILL);
        mPaintTwo.setStrokeWidth(2);


        mRect=new RectF(-300,-300,300,300);
        mRectTwo=new RectF(-100,-100,100,100);


        path1.arcTo(mRect,260,10);
        path1.lineTo(0,0);
        path1.close();



        path2.addArc(mRectTwo,260,10);
        path2.lineTo(0,0);
        path2.close();
        path1.op(path2, Path.Op.DIFFERENCE);

        path3.arcTo(mRect,270,10);
        path3.lineTo(0,0);
        path3.close();



        path4.addArc(mRectTwo,270,10);
        path4.lineTo(0,0);
        path4.close();
        path3.op(path4, Path.Op.DIFFERENCE);



        PathMeasure pathMeasure=new PathMeasure(path1,false);

        float length = pathMeasure.getLength();



        pathMeasure.getPosTan(0,leftTop,null);
        pathMeasure.getPosTan((float) (600*Math.PI/36),rightTop,null);
        pathMeasure.getPosTan((float) (length-200-200*Math.PI/36),rightBottom,null);
        pathMeasure.getPosTan(length-200,leftBottom,null);



        src=new float[]{
                leftTop[0],leftTop[1],
                rightTop[0],rightTop[1],
                rightBottom[0],rightBottom[1],
                leftBottom[0],leftBottom[1]
        };

        des=new float[]{
                leftTop[0],leftTop[1],
                rightTop[0],rightTop[1],
                rightBottom[0],rightBottom[1],
                leftBottom[0],leftBottom[1]
        };


        PathMeasure pathMeasureTwo=new PathMeasure(path3,false);

        pathMeasureTwo.getPosTan(0,leftTopTwo,null);
        pathMeasureTwo.getPosTan(200,rightTopTwo,null);
        pathMeasureTwo.getPosTan((float) (200+600*Math.PI/36),rightBottomTwo,null);
        pathMeasureTwo.getPosTan((float) (length-200*Math.PI/36),leftBottomTwo,null);


        Log.d("chen",leftTopTwo[0]+"________"+leftTopTwo[1]);
        Log.d("chen",rightTopTwo[0]+"________"+rightTopTwo[1]);
        Log.d("chen",rightBottomTwo[0]+"________"+rightBottomTwo[1]);
        Log.d("chen",leftBottomTwo[0]+"________"+leftBottomTwo[1]);


        src2=new float[]{
                leftTopTwo[0],leftTopTwo[1],
                rightTopTwo[0],rightTopTwo[1],
                rightBottomTwo[0],rightBottomTwo[1],
                leftBottomTwo[0],leftBottomTwo[1]
        };


        des2=new float[]{
                leftTopTwo[0],leftTopTwo[1],
                rightTopTwo[0],rightTopTwo[1],
                rightBottomTwo[0],rightBottomTwo[1],
                leftBottomTwo[0],leftBottomTwo[1]
        };


        matrix=new Matrix();
        matrix.setPolyToPoly(src,0,des,0,4);


        matrixTwo=new Matrix();
        matrixTwo.setPolyToPoly(src2,0,des2,0,4);

        path1.transform(matrix);
        path3.transform(matrixTwo);
    }
    float[] src;
    float[] src2;
    float[] des;
    float[] des2;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(getWidth()/2,getHeight()/2);

        canvas.save();
        for(int i=0;i<4;i++){
            mPaint.setColor(Color.argb(255, 255, 128, 103));
            canvas.drawPath(path1,mPaint);
            mPaint.setColor(Color.GREEN);
            canvas.drawPath(path3,mPaint);
            canvas.rotate(20);
        }
        canvas.restore();


        canvas.save();
        for(int i=0;i<3;i++){
            canvas.rotate(-20);
            mPaint.setColor(Color.argb(255, 255, 128, 103));
            canvas.drawPath(path1,mPaint);
            mPaint.setColor(Color.GREEN);
            canvas.drawPath(path3,mPaint);

        }
        canvas.restore();



    }

    private float lastTop=0;
    private float lastBottom=0;



    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            des[3]=rightTop[1]+lastTop;
            des[5]=rightBottom[1]-lastBottom;

            des2[1]=leftTopTwo[1]-lastBottom;
            des2[3]=rightTopTwo[1]+lastTop;

            matrix.reset();
            matrix.setPolyToPoly(src,0,des,0,4);


            matrixTwo.reset();
            matrixTwo.setPolyToPoly(src2,0,des2,0,4);

            path1.transform(matrix);
            path3.transform(matrixTwo);

            lastBottom+=1;
            lastTop+=3;

            Log.d("chen",lastBottom+"_________"+lastTop);

            invalidate();
//            handler.sendEmptyMessageDelayed(1,100);
        }
    };



    public void startAnim(){

        handler.sendEmptyMessageDelayed(1,100);
    }


    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        startAnim();
    }
}
