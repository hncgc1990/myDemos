package com.hncgc1990.mylibrary;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.math.BigDecimal;

public class MyCircleProgressView extends View {

    public static final int COLOR_ONE =Color.GREEN;
    public static final int COLOR_TWO =Color.YELLOW;
    public static final int COLOR_THREE =Color.RED;

    /**
     * 半径
     */
    private int mRadius =30;

    private Paint mCirclePaint;
    private RectF mRect;

    /**
     * 目标渐变色主色
     */
    private int[] mColors =new int[3];


    /**
     * 当前的进度 0-100
     */
    private int mCurrentPercent=0;

    /**
     * 开始的角度
     */
    private float mStartAngle=-90;

    /**
     *
     */
    private Picture mPicture ;
    private Canvas mCanvas;


    private int mDefalutSize;

    public MyCircleProgressView(Context context) {
        this(context, null);
    }

    public MyCircleProgressView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyCircleProgressView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context,attrs,R.attr.MyCircleProgressStyle);
    }



    private void init(Context context, AttributeSet attrs,int defStyle) {

        TypedArray typedArray=context.obtainStyledAttributes(attrs,R.styleable.MyCircleProgressView,0,0);

        mRadius = typedArray.getDimensionPixelSize(R.styleable.MyCircleProgressView_radius, mRadius);

        mColors[0]=typedArray.getColor(R.styleable.MyCircleProgressView_colorOne, COLOR_ONE);
        mColors[1]=typedArray.getColor(R.styleable.MyCircleProgressView_colorOne, COLOR_TWO);
        mColors[2]=typedArray.getColor(R.styleable.MyCircleProgressView_colorOne, COLOR_THREE);


//        Picture需要关闭硬件加速
        setLayerType(LAYER_TYPE_SOFTWARE,null);

        mCirclePaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        mCirclePaint.setStyle(Paint.Style.FILL);

        mRect =new RectF(getPaddingLeft(),getPaddingTop(),2* mRadius+getPaddingLeft(),2* mRadius+getPaddingTop());
        mPicture = new Picture();

    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        mDefalutSize=2*mRadius+getPaddingLeft()+getPaddingRight();

        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int size = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int widthResult = measureSize(mode, size);
        int heightResult = measureSize(heightMode, heightSize);

        int result=Math.min(widthResult,heightResult);
        setMeasuredDimension(result,result);
    }

    private int measureSize(int mode, int size) {
        int result=mDefalutSize;
        switch (mode){
            case MeasureSpec.AT_MOST:
                Log.d("chen","mode is AT_MOST");
                result=Math.min(size,mDefalutSize);
                break;
            case MeasureSpec.EXACTLY:
                result=size;
                Log.d("chen","mode is EXACTLY");
                break;
            case MeasureSpec.UNSPECIFIED:
                result=mDefalutSize;
                Log.d("chen","mode is UNSPECIFIED");
                break;
            default:
                break;
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
            mCanvas = mPicture.beginRecording(getWidth(), getHeight());
            for(int i=1;i<=mCurrentPercent;i++){
                mCirclePaint.setColor(ColorUtils.getCurrentColor((i%500)/500f, mColors));

                if(i==1){
                    mCanvas.drawArc(mRect,mStartAngle,3.6f,true,mCirclePaint);

                }else{
                    mCanvas.drawArc(mRect,mStartAngle-1,4.6f,true,mCirclePaint);

                }

                mStartAngle=add(mStartAngle,3.6f);
            }
            mPicture.endRecording();
            canvas.drawPicture(mPicture);
            mStartAngle=-90f;


    }

    /**
     * 设置当前的完成度
     */
    public void setPercent(int percent){
        mCurrentPercent=percent;
//        Log.d("chen","当前百分比"+mCurrentPercent);
        invalidate();
    }

   Handler handler=new Handler(){
       @Override
       public void handleMessage(Message msg) {
           super.handleMessage(msg);
           mCurrentPercent+=4;
           setPercent(mCurrentPercent);
           handler.sendEmptyMessageDelayed(1,10);
       }
   };


    public void startLodingAnim(int percent){
        mCurrentPercent=percent;
        handler.sendEmptyMessageDelayed(1,10);
    }

    public void stopLoadingAnim(){
        handler.removeMessages(1);
    }


    public void startLodingAnim(){
        startLodingAnim(mCurrentPercent);
    }



    public float add(float f1,float f2){
        BigDecimal bf1=new BigDecimal(Float.toString(f1));
        BigDecimal bf2=new BigDecimal(Float.toString(f2));
        return bf1.add(bf2).floatValue();
    }


}
