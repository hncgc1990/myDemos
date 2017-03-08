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
import android.graphics.Picture;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import com.hncgc1990.myrefreshview.R;

/**
 * Created by Administrator on 2017/2/14.
 * 圆形
 */
public class PathOpView extends View {


    private Paint mPaint;

    private RectF mRect;
    private RectF mRectTwo;

    private Bitmap bitmap;

    private float scale;

    private Matrix matrix;

    public PathOpView(Context context) {
        super(context);
        init();
    }



    public PathOpView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PathOpView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        startAnim();
    }

    Path path=new Path();

    Paint mLittleCicle;


    Picture picture=new Picture();

    private void init() {
        mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.argb(255, 255, 128, 103));
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(2);
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

        mLittleCicle=new Paint(Paint.ANTI_ALIAS_FLAG);
        mLittleCicle.setColor(Color.WHITE);
        mLittleCicle.setStyle(Paint.Style.FILL);
        mLittleCicle.setStrokeWidth(1);


        Canvas canvas = picture.beginRecording(400, 400);
        path1.addCircle(0, 0, 200, Path.Direction.CW);
        path2.addRect(0, -200, 200, 200, Path.Direction.CW);
        path3.addCircle(0, -100, 100, Path.Direction.CW);
        path4.addCircle(0, 100, 100, Path.Direction.CCW);
        path.addRect(0,-200,-200,200, Path.Direction.CW);

        path1.op(path2, Path.Op.DIFFERENCE);

        path1.op(path3, Path.Op.UNION);

        path1.op(path4, Path.Op.DIFFERENCE);

        canvas.drawPath(path1,mPaint);

        path1.reset();
        path1.addCircle(0, 0, 200, Path.Direction.CW);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(1);
        canvas.drawPath(path1,mPaint);

        canvas.drawCircle(0,-100,40,mLittleCicle);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(0,100,40,mPaint);




    }

    Path path1=new Path();
    Path path2=new Path();
    Path path3=new Path();
    Path path4=new Path();

    private  float currentAngle=0;




    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        canvas.translate(getWidth()/2,getHeight()/2);

        canvas.rotate(currentAngle);


        canvas.drawPicture(picture);
    }


    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            currentAngle+=3.6;
            invalidate();
            sendEmptyMessageDelayed(1,10);

        }
    };


    public void startAnim(){
        handler.sendEmptyMessageDelayed(1,10);
    }

}
