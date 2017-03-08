package com.hncgc1990.myrefreshview.Paint;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;

import com.hncgc1990.myrefreshview.R;

/**
 * Created by Administrator on 2017/2/14.
 *  LightingColorFilter相关
 */
public class PorterDuffColorFilterView extends View {



    Bitmap mBgBitmap;

    Bitmap mFgBitmap;



    public PorterDuffColorFilterView(Context context) {
        super(context);
        init();
    }




    public PorterDuffColorFilterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PorterDuffColorFilterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }



    Paint mPaint;
    Paint mColorFilterPaint;
    Canvas mFgCanvas;
    Path mPath;


    private void init() {
        mPaint=new Paint(Paint.ANTI_ALIAS_FLAG|Paint.DITHER_FLAG);
        mPaint.setColor(Color.GREEN);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(3);

        mColorFilterPaint=new Paint(Paint.ANTI_ALIAS_FLAG|Paint.DITHER_FLAG);
        mColorFilterPaint.setARGB(0, 255, 0, 0);
        mColorFilterPaint.setStyle(Paint.Style.FILL);
        mColorFilterPaint.setStrokeWidth(50);
        mColorFilterPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
//        mColorFilterPaint.setColorFilter(new PorterDuffColorFilter(0x00000000,PorterDuff.Mode.DST_IN));





        mBgBitmap= BitmapFactory.decodeResource(getResources(), R.drawable.ssss);


        mFgBitmap=Bitmap.createBitmap(mBgBitmap.getWidth(),mBgBitmap.getHeight(), Bitmap.Config.ARGB_4444);//此处一点要包含透明度A


        mFgCanvas=new Canvas(mFgBitmap);
        mFgCanvas.drawColor(0xFF808080);



        mPath=new Path();
        mPath.addCircle(300,300,100, Path.Direction.CW);
//        mPath.lineTo(22,333);
        mFgCanvas.drawPath(mPath,mColorFilterPaint);
//        mFgCanvas.drawCircle(0,0,100,mPaint);
    }

    private Matrix matrix;


    @Override
    protected void onDraw(Canvas canvas) {


        canvas.drawBitmap(mBgBitmap,0,0,null);


        canvas.drawBitmap(mFgBitmap,0,0,null);

    }
}
