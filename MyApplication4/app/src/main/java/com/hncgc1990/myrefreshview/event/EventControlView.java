package com.hncgc1990.myrefreshview.event;

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
import android.graphics.Region;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.hncgc1990.myrefreshview.R;

/**
 * Created by Administrator on 2017/2/14.
 * 圆形
 */
public class EventControlView extends View {


    private Paint mPaint;
    private Paint mPressPaint;

    private RectF mRect;
    private RectF mRectTwo;

    private Bitmap bitmap;

    private float scale;

    private Matrix matrix;


    private Region mCircleReginon;

    public EventControlView(Context context) {
        super(context);
        init();
    }



    public EventControlView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EventControlView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int size = MeasureSpec.getSize(widthMeasureSpec);



        int defaultSize=2*100+getPaddingLeft()+getPaddingRight();

        int result =0;

        switch (mode){
            case MeasureSpec.AT_MOST:
                Log.d("chen","当前的mode__MeasureSpec.AT_MOST");
                result=Math.min(size,defaultSize);
                break;
            case MeasureSpec.EXACTLY:
                result=size;
                Log.d("chen","当前的mode__MeasureSpec.EXACTLY");
                break;
            case MeasureSpec.UNSPECIFIED:
                result=defaultSize;
                Log.d("chen","当前的mode__MeasureSpec.UNSPECIFIED");
                break;
            default:
                break;

        }

        setMeasuredDimension(result,result);


    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mCircleReginon=new Region();

        Region gloable=new Region(-w,-h,w,h);

//        path.addCircle(0,0,100, Path.Direction.CW);
        mRect=new RectF(-300,-300,300,300);
        path.addArc(mRect,-45,90);
        path.lineTo(0,0);
        path.close();
        path.offset(20,0);


        Path path1=new Path();
        path1.addCircle(0,0,140, Path.Direction.CW);

        path.op(path1, Path.Op.DIFFERENCE);






        boolean bool = mCircleReginon.setPath(path, gloable);


        Log.d("chen","截取到了对应的区域了？"+bool);
    }

    Path path=new Path();

    private void init() {
        mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.argb(255, 255, 128, 103));
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(2);



        mPressPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        mPressPaint.setColor(Color.RED);
        mPressPaint.setStyle(Paint.Style.FILL);
        mPressPaint.setStrokeWidth(2);

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


    Matrix matrix1=new Matrix();
    Matrix matrix2=new Matrix();
    Matrix matrix3=new Matrix();
    Matrix matrix4=new Matrix();



    int currentClickIndex=-1;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        canvas.translate(getWidth()/2,getHeight()/2);

        canvas.drawLine(-getWidth()/2,0,getWidth()/2,0,mPaint);
        canvas.drawLine(0,-getHeight()/2,0,getHeight()/2,mPaint);

        for(int i=0;i<4;i++){
           switch (i){
               case 0:
                   canvas.getMatrix().invert(matrix1);
                   break;
               case 1:
                   canvas.getMatrix().invert(matrix2);
                   break;
               case 2:
                   canvas.getMatrix().invert(matrix3);
                   break;
               case 3:
                   canvas.getMatrix().invert(matrix4);
                   break;
           }

            if(i==currentClickIndex){
                canvas.drawPath(path,mPressPaint);
            }else{
                canvas.drawPath(path,mPaint);
            }

            canvas.rotate(90);

        }

        canvas.drawCircle(0,0,110,mPaint);



    }

    float[] srcs=new float[2];

    float[] dess=new float[2];


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int action = event.getAction();

        float x = event.getRawX();

        float y = event.getRawY();
        srcs[0]=x;
        srcs[1]=y;
        switch (action){
            case MotionEvent.ACTION_DOWN:

                Log.d("chen","x______"+x+"_______y"+y);

                matrix1.mapPoints(dess,srcs);
                Log.d("chen","转换后____x______"+dess[0]+"_______y_____"+dess[1]+"_______(int)dess[0]___"+(int)dess[0]+"_______(int)dess[1]___"+(int)dess[1]);

                if(mCircleReginon.contains((int)dess[0],(int)dess[1])){
                    Log.d("chen","该位置在遥控位置1");
                    currentClickIndex=0;
                    invalidate();
                    break;
                }

                matrix2.mapPoints(dess,srcs);
                Log.d("chen","转换后____x______"+dess[0]+"_______y_____"+dess[1]+"_______(int)dess[0]___"+(int)dess[0]+"_______(int)dess[1]___"+(int)dess[1]);
                if(mCircleReginon.contains((int)dess[0],(int)dess[1])){
                    Log.d("chen","该位置在遥控位置2");
                    currentClickIndex=1;
                    invalidate();
                    break;
                }
                matrix3.mapPoints(dess,srcs);
                Log.d("chen","转换后____x______"+dess[0]+"_______y_____"+dess[1]+"_______(int)dess[0]___"+(int)dess[0]+"_______(int)dess[1]___"+(int)dess[1]);
                if(mCircleReginon.contains((int)dess[0],(int)dess[1])){
                    Log.d("chen","该位置在遥控位置3");
                    currentClickIndex=2;
                    invalidate();
                    break;
                }
                matrix4.mapPoints(dess,srcs);
                Log.d("chen","转换后____x______"+dess[0]+"_______y_____"+dess[1]+"_______(int)dess[0]___"+(int)dess[0]+"_______(int)dess[1]___"+(int)dess[1]);
                if(mCircleReginon.contains((int)dess[0],(int)dess[1])){
                    Log.d("chen","该位置在遥控位置4");
                    currentClickIndex=3;
                    invalidate();
                    break;
                }
         case MotionEvent.ACTION_CANCEL:
             Log.d("chen","ACTION_CANCEL_____"+currentClickIndex);
             currentClickIndex=-1;
             invalidate();
             break;
            case MotionEvent.ACTION_MOVE:
                Log.d("chen","ACTION_MOVE_____"+currentClickIndex);
                break;
            case MotionEvent.ACTION_UP:
                Log.d("chen","ACTION_UP_____"+currentClickIndex);
                if(currentClickIndex!=-1){
                    Log.d("chen","点击了位置"+currentClickIndex);
                }
//                TODO 是否需要添加判断
                currentClickIndex=-1;
                invalidate();

            break;


        }

        return true;
    }
}
