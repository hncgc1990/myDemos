package com.hncgc1990.myrefreshview.Paint.shader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposeShader;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.hncgc1990.myrefreshview.R;

/**
 * Created by Administrator on 2017/2/14.
 * 圆形
 */
public class ComposeShaderView extends View {


    private Paint mPaint;

    private Path mPath;

    Path path;


    private Bitmap mBitmap;
    private Matrix matrix;


    public ComposeShaderView(Context context) {
        super(context);
        init();
    }



    public ComposeShaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ComposeShaderView(Context context, AttributeSet attrs, int defStyleAttr) {
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

        LinearGradient linearGradient = new LinearGradient(0,0,0,50,Color.GREEN,Color.YELLOW, Shader.TileMode.MIRROR);
//        SweepGradient radialGradient=new SweepGradient(50,50,Color.YELLOW,Color.GREEN);

        RadialGradient radialGradient=new RadialGradient(50,50,50,Color.YELLOW,Color.GREEN, Shader.TileMode.REPEAT);


        mPaint.setShader(new ComposeShader(linearGradient,radialGradient, PorterDuff.Mode.SRC_IN));





    }
    float x=100;

    float y=100;


    @Override
    public boolean onTouchEvent(MotionEvent event) {




        return true;

    }

    @Override
    protected void onDraw(Canvas canvas) {


        canvas.drawRect(0,0,100,100,mPaint);





    }
}
