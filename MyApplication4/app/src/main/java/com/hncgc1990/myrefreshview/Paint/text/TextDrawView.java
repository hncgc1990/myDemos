package com.hncgc1990.myrefreshview.Paint.text;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017/2/24.
 */
public class TextDrawView extends View {

    Paint mPaint;
    Path path;




    public TextDrawView(Context context) {
        super(context);
        init();
    }



    public TextDrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TextDrawView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint=new Paint(Paint.ANTI_ALIAS_FLAG|Paint.DITHER_FLAG);
        mPaint.setTextSize(30);
        mPaint.setColor(Color.GRAY);


        path=new Path();
//        path.lineTo(100,100);
//        path.lineTo(200,0);
//        path.lineTo(300,100);


        path.addCircle(0,0,100, Path.Direction.CW);

    }
    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onDraw(Canvas canvas) {

        canvas.translate(getWidth()/2,getHeight()/2);
//        canvas.drawText("dlfhiofjdild",0,0,mPaint);
//        canvas.drawTextRun("AAAAAAAAAAABBBBBBBBBCVCVCCCC",3,15,0,17,0,0,true,mPaint);
        canvas.drawTextOnPath("adfldfdlfljdfldfjdjfdjf",path,0,-10,mPaint);
        canvas.drawPath(path,mPaint);

    }
}
