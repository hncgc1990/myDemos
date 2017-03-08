package com.hncgc1990.myrefreshview.Paint;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.hncgc1990.myrefreshview.R;

/**
 * Created by Administrator on 2017/2/14.
 *  LightingColorFilter相关
 */
public class LightingColorFilterView extends View {



    Bitmap bitmap;



    public LightingColorFilterView(Context context) {
        super(context);
        init();
    }




    public LightingColorFilterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LightingColorFilterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }



    Paint mPaint;
    Paint mColorFilterPaint;
    Paint mColorFilteOnerPaint;
    Paint mColorFilteTwoPaint;
    Paint mColorFilteThreePaint;




    private void init() {
        mPaint=new Paint();
        mPaint.setColor(Color.GREEN);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(3);

        mColorFilterPaint=new Paint();
        mColorFilterPaint.setColor(Color.GREEN);
        mColorFilterPaint.setStyle(Paint.Style.FILL);
        mColorFilterPaint.setStrokeWidth(3);
        mColorFilterPaint.setColorFilter(new LightingColorFilter(0xffffff,0x008000));//相当增加掉绿色


        mColorFilteOnerPaint=new Paint();
        mColorFilteOnerPaint.setColor(Color.GREEN);
        mColorFilteOnerPaint.setStyle(Paint.Style.FILL);
        mColorFilteOnerPaint.setStrokeWidth(3);
        mColorFilteOnerPaint.setColorFilter(new LightingColorFilter(0xff00ff,0));//相当去掉绿色


        mColorFilteTwoPaint=new Paint();
        mColorFilteTwoPaint.setColor(Color.GREEN);
        mColorFilteTwoPaint.setStyle(Paint.Style.FILL);
        mColorFilteTwoPaint.setStrokeWidth(3);
        mColorFilteTwoPaint.setColorFilter(new LightingColorFilter(0xff00ff,0xFF0000));//相当去掉绿色,再增加红色



        mColorFilteThreePaint=new Paint();
        mColorFilteThreePaint.setColor(Color.GREEN);
        mColorFilteThreePaint.setStyle(Paint.Style.FILL);
        mColorFilteThreePaint.setStrokeWidth(3);
        mColorFilteThreePaint.setColorFilter(new LightingColorFilter(0xffffff,0x00bb00));//


        bitmap= BitmapFactory.decodeResource(getResources(), R.drawable.sanyuanse);
        matrix=new Matrix();





    }

    private Matrix matrix;


    @Override
    protected void onDraw(Canvas canvas) {


//        canvas.drawCircle(100,100,100,mPaint);


//        canvas.drawCircle(100,300,100,mColorFilterPaint);


        canvas.drawBitmap(bitmap,matrix,mPaint);



        canvas.save();

        canvas.translate(bitmap.getWidth()*2,0);

        canvas.drawBitmap(bitmap,matrix,mColorFilterPaint);//相当增加掉绿色


        canvas.restore();



        canvas.translate(0,bitmap.getHeight()*2);

        canvas.drawBitmap(bitmap,matrix,mColorFilteOnerPaint);//相当去掉绿色




        canvas.translate(bitmap.getWidth()*2,0);

        canvas.drawBitmap(bitmap,matrix,mColorFilteTwoPaint);


        canvas.translate(0,bitmap.getHeight()*2);

        canvas.drawBitmap(bitmap,matrix,mColorFilteThreePaint);//相当去掉绿色

    }
}
