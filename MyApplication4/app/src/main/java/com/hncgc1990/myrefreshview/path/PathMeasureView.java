package com.hncgc1990.myrefreshview.path;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

import com.hncgc1990.myrefreshview.R;

/**
 * Created by Administrator on 2017/2/14.
 * 圆形
 */
public class PathMeasureView extends View {


    private Paint mPaint;

    private RectF mRect;
    private RectF mRectTwo;

    private Bitmap bitmap;

    private float scale;

    private Matrix matrix;

    public PathMeasureView(Context context) {
        super(context);
        init();
    }



    public PathMeasureView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PathMeasureView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        startAnim();
    }

    Path path=new Path();


    private void init() {
        mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(2);
        mRect=new RectF(100,100,500,300);
        mRectTwo=new RectF(100,400,500,600);

//
//        mPaint.setColorFilter(new ColorMatrixColorFilter(new float[]{
//                0.5f,0,0,0,0,
//                0,0.5f,0,0,0,
//                0,0,0.5f,0,0,
//                0,0,0,0.5f,0
//        }));

        BitmapFactory.Options options=new BitmapFactory.Options();
        options.inSampleSize=3;


        bitmap= BitmapFactory.decodeResource(getResources(), R.drawable.arrow,options);
        matrix=new Matrix();
//        matrix.postScale(0.5f,0.5f);

        Log.d("chen","宽度"+bitmap.getWidth()+"______高度"+bitmap.getHeight());

    }


    PathMeasure pathMeasure=new PathMeasure();

    float[] pos=new float[2];
    float[] tan=new float[2];

    int currentPercent=0;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        matrix.reset();
        canvas.translate(getWidth()/2,getHeight()/2);


        path.addCircle(0,0,200, Path.Direction.CW);
        pathMeasure.setPath(path,false);

        pathMeasure.getPosTan(pathMeasure.getLength()*currentPercent/100,pos,tan);
        matrix.postRotate((float)Math.toDegrees(Math.atan2(tan[1],tan[0])),bitmap.getWidth()/2,bitmap.getHeight()/2);
        matrix.postTranslate(pos[0] - bitmap.getWidth() / 2, pos[1] - bitmap.getHeight() / 2);
        canvas.drawPath(path,mPaint);

        canvas.drawBitmap(bitmap,matrix,mPaint);


    }


    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if(currentPercent==100){
              currentPercent=0;
            }

            currentPercent+=1;
            invalidate();
            sendEmptyMessageDelayed(1,100);

        }
    };


    public void startAnim(){
        handler.sendEmptyMessageDelayed(1,100);
    }

}
