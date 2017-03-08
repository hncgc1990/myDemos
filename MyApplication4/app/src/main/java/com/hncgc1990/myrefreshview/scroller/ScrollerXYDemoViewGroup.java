package com.hncgc1990.myrefreshview.scroller;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

import com.hncgc1990.myrefreshview.Logger;
import com.hncgc1990.myrefreshview.Paint.MeasureUtil;


/**
 * Created by Administrator on 2017/2/27.
 */
public class ScrollerXYDemoViewGroup extends ViewGroup{



    private int topBorder;
    private int bottomBorder;
    private int yTransition;


    private Scroller mScroller;

    private float scale;

    public ScrollerXYDemoViewGroup(Context context) {
        super(context);
    }

    public ScrollerXYDemoViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mScroller=new Scroller(context);
        scale= MeasureUtil.getScreenSize((Activity) context)[0]/(float)MeasureUtil.getScreenSize((Activity) context)[1];
        Logger.D("scale______"+scale);
        Logger.D("MeasureUtil.getScreenSize((Activity) context)[0]______"+MeasureUtil.getScreenSize((Activity) context)[0]);
        Logger.D("MeasureUtil.getScreenSize((Activity) context)[1]______"+MeasureUtil.getScreenSize((Activity) context)[1]);
    }

    public ScrollerXYDemoViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        for(int i=0;i<getChildCount();i++){
            View childView = getChildAt(i);
            childView.measure(widthMeasureSpec,heightMeasureSpec);
        }

        topBorder =getChildAt(0).getTop();
        bottomBorder =getChildAt(getChildCount()-1).getBottom();


    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {


        for(int i=0;i<getChildCount();i++){
            View childView = getChildAt(i);
            childView.layout(childView.getMeasuredWidth()*i,childView.getMeasuredHeight()*i,childView.getMeasuredWidth()*(i+1),childView.getMeasuredHeight()*(i+1));
        }

    }

    float mLastX=0;
    float mLastY=0;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {


        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int actionMasked = event.getActionMasked();



        switch (actionMasked){
            case MotionEvent.ACTION_DOWN:
                mLastX= event.getRawX();
                mLastY= event.getRawY();

                break;
            case MotionEvent.ACTION_MOVE:

                yTransition = (int) (mLastY -event.getRawY());
                if(getScrollY()+ yTransition < topBorder){
                    return true;
                }else if(getScrollY()+getHeight()+ yTransition > bottomBorder){
                    return  true;
                }


                scrollBy((int) (scale*yTransition),yTransition);
                mLastY= event.getRawY();

                Logger.D("scale*yTransition__________  "+scale*yTransition+"____-yTransition_____"+yTransition);
                Logger.D("getLeft__________  "+getLeft());
                Logger.D("getRigth__________  "+getRight());
                Logger.D("getScrollX_________"+getScrollX());
                Logger.D("child___getLeft_________"+getChildAt(0).getLeft());
                Logger.D("child___getRight_________"+getChildAt(0).getRight());

                Logger.D("child_1___getLeft_________"+getChildAt(1).getLeft());
                Logger.D("child_1___getRight_________"+getChildAt(1).getRight());
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:

                // 当手指抬起时，根据当前的滚动值来判定应该滚动到哪个子控件的界面
                int targetIndex = (getScrollY() + getHeight() / 2) / getHeight();
                int dy = targetIndex * getHeight() - getScrollY();
                int dx = targetIndex * getWidth() - getScrollX();
                // 第二步，调用startScroll()方法来初始化滚动数据并刷新界面
                mScroller.startScroll(getScrollX(),getScrollY(), dx, dy);
                invalidate();
                mLastY=0;
                break;
        }




        return true;
    }


    @Override
    public void computeScroll() {
        if(mScroller.computeScrollOffset()){
           setScrollX(mScroller.getCurrX());
            setScrollY(mScroller.getCurrY());
//            scrollTo(mScroller.getCurrX(),mScroller.getCurrY());
            invalidate();
        }


    }
}
