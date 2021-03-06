package com.hncgc1990.myrefreshview.Paint.shader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.hncgc1990.myrefreshview.R;

/**
 * Created by Administrator on 2017/2/14.
 * 圆形
 */
public class BitmapShaderView extends View {


    private Paint mPaint;

    private Path mPath;

    Path path;


    private Bitmap mBitmap;
    private Matrix matrix;


    public BitmapShaderView(Context context) {
        super(context);
        init();
    }



    public BitmapShaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BitmapShaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {



        mPaint=new Paint(Paint.ANTI_ALIAS_FLAG|Paint.DITHER_FLAG);
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(1);


        mBitmap= BitmapFactory.decodeResource(getResources(), R.drawable.haha);
        matrix=new Matrix();
        matrix.setScale(0.5f,0.5f);
        mPaint.setShader(new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.REPEAT));







    }
    float x=100;

    float y=100;


    @Override
    public boolean onTouchEvent(MotionEvent event) {



         y = event.getY();

        x = event.getX();

        invalidate();


        return true;

    }

    @Override
    protected void onDraw(Canvas canvas) {


        canvas.drawColor(0x808080);



//        canvas.drawBitmap(mBitmap,matrix,mPaint);
        canvas.drawCircle(x,y,100,mPaint);
    }
}
