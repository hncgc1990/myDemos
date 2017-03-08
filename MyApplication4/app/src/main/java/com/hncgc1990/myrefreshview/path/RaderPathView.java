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
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import com.hncgc1990.myrefreshview.R;

/**
 * Created by Administrator on 2017/2/14.
 * 圆形
 */
public class RaderPathView extends View {


    private Paint mPaint;

    private RectF mRect;
    private RectF mRectTwo;

    private Bitmap bitmap;

    private float scale;

    private Matrix matrix;

    private Paint mPointPaint;


    private Paint mPathPaint;


    private int mCurrentPercent=100;

    public RaderPathView(Context context) {
        super(context);
        init();
    }



    public RaderPathView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RaderPathView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    Path path=new Path();

    private void init() {

        setLayerType(LAYER_TYPE_SOFTWARE,null);
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

        mPointPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        mPointPaint.setColor(Color.GREEN);
        mPointPaint.setStrokeWidth(10);
        mPointPaint.setStyle(Paint.Style.FILL);
        mPointPaint.setColorFilter(new ColorMatrixColorFilter(new float[]{
                0.5f,0,0,0,0,
                0,0.5f,0,0,0,
                0,0,0.5f,0,0,
                0,0,0,0.5f,0
        }));

        mPathPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        mPathPaint.setColor(Color.YELLOW);
        mPathPaint.setStrokeWidth(2);
        mPathPaint.setStyle(Paint.Style.FILL);
        mPathPaint.setColorFilter(new ColorMatrixColorFilter(new float[]{
                0.5f,0,0,0,0,
                0,0.5f,0,0,0,
                0,0,0.5f,0,0,
                0,0,0,0.5f,0
        }));
    }

    int lastCanvasStauts;

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        mCurrentPercent=0;
        startAnim();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(getWidth()/2,getHeight()/2);

        //画背景
        canvas.save();
        for(int i=0;i<6;i++){
            canvas.drawLine(-300,0,300,0,mPaint);
            for(int j=0;j<6;j++){
                canvas.drawLine(50*(j+1),0,25*(j+1),(float)(25*(j+1)*Math.sqrt(3)),mPaint);
            }
            canvas.rotate(60);
        }

        canvas.restore();


        //画对应的文字
        canvas.save();
        for(int i=0;i<6;i++){
            lastCanvasStauts =canvas.save();
            canvas.translate(300,0);
            canvas.rotate(-60*i);
            if(i==3 ||i==2)
                canvas.drawText("A",-10,10,mPaint);
            if(i==0 || i==1)
                canvas.drawText("A",10,10,mPaint);
            if(i==4||i==5)
                canvas.drawText("A",-10,-10,mPaint);

            canvas.restoreToCount(lastCanvasStauts);
            canvas.rotate(60);
        }
        canvas.restore();


        //画围中的面积
        path.moveTo(30,0);

        for(int i=1;i<6;i++){

            path.lineTo((float) (30*(i+1)*Math.cos(Math.toRadians(60*i)))*mCurrentPercent/100,(float)(30*(i+1)*Math.sin(Math.toRadians(60*i)))*mCurrentPercent/100);
        }

        path.close();
        canvas.drawPath(path,mPathPaint);


        //画点
        canvas.save();
        for(int i=0;i<6;i++){
//            canvas.drawPoint(30*(i+1),0,mPointPaint);
            canvas.drawCircle(30*(i+1),0,5,mPointPaint);

            canvas.rotate(60);
        }

        canvas.restore();
    }


    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(mCurrentPercent==100){
                removeMessages(1);
                return;

            }

            mCurrentPercent+=1;
            invalidate();
            sendEmptyMessageDelayed(1,10);
        }
    };


    public void startAnim(){

        handler.sendEmptyMessageDelayed(1,10);
    }

}
