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
 * 圆形 截取path中的一段
 */
public class PathSegmentView extends View {


    private Paint mPaint;

    private RectF mRect;
    private RectF mRectTwo;

    private Bitmap bitmap;

    private float scale;

    private Matrix matrix;

    public PathSegmentView(Context context) {
        super(context);
        init();
    }



    public PathSegmentView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PathSegmentView(Context context, AttributeSet attrs, int defStyleAttr) {
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

        setLayerType(LAYER_TYPE_SOFTWARE,null);

        mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);

        mPaint.setColor(Color.GREEN);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);


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

    Path src=new Path();


    float lastStartPosition=0;

    float lastLength=0;

    float currentPosition=0;

    float currentLength=0;

    boolean isD=false;


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(getWidth()/2,getHeight()/2);



        path.addCircle(0,0,300, Path.Direction.CW);

        pathMeasure.setPath(path,false);

        pathMeasure.getSegment(currentPosition,currentPosition+currentLength,src,true);
//
//        pathMeasure.getPosTan(pathMeasure.getLength()*currentPercent/100,pos,tan);
//        matrix.postRotate((float)Math.toDegrees(Math.atan2(tan[1],tan[0])),bitmap.getWidth()/2,bitmap.getHeight()/2);
//        matrix.postTranslate(pos[0] - bitmap.getWidth() / 2, pos[1] - bitmap.getHeight() / 2);
        canvas.drawPath(src,mPaint);

//        canvas.drawBitmap(bitmap,matrix,mPaint);

        lastStartPosition=currentPosition;
        lastLength=currentLength;
        Log.d("chen","currentPosition______"+currentPosition+"________"+currentLength);
        Log.d("chen","pathMeasure.getLength()_____"+pathMeasure.getLength()+"________pathMeasure.getLength()"+pathMeasure.getLength()/Math.PI);

    }


    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);



            currentPosition=lastStartPosition+lastLength;
            //TODO 这里的最大值和步程需要调节
            if(isD||currentLength>=80*Math.PI){
                isD=true;
                currentLength=(float)(lastLength-Math.PI);
                if(currentLength<=0){
                    isD=false;
                }
            }else{
                currentLength=(float)(lastLength+Math.PI);
            }

            if(currentPosition+currentLength>pathMeasure.getLength()){
                removeMessages(1);
                return;
//                currentLength=0;
//                currentPosition=0;
//                lastLength=0;
//                lastStartPosition=0;
            }

            src.reset();
            invalidate();
            sendEmptyMessageDelayed(1,100);

        }
    };


    public void startAnim(){
        handler.sendEmptyMessageDelayed(1,100);
    }

}
