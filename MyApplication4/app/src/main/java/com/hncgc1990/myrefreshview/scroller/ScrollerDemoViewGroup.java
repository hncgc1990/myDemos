package com.hncgc1990.myrefreshview.scroller;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

import com.hncgc1990.myrefreshview.Logger;


/**
 * Created by Administrator on 2017/2/27.
 */
public class ScrollerDemoViewGroup extends ViewGroup{



    private int leftBorder;
    private int rightBorder;
    private int xTransition;


    private Scroller mScroller;

    public ScrollerDemoViewGroup(Context context) {
        super(context);
    }

    public ScrollerDemoViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mScroller=new Scroller(context);
    }

    public ScrollerDemoViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        for(int i=0;i<getChildCount();i++){
            View childView = getChildAt(i);
            childView.measure(widthMeasureSpec,heightMeasureSpec);
        }

        leftBorder=getChildAt(0).getLeft();
        rightBorder=getChildAt(getChildCount()-1).getRight();

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {


        for(int i=0;i<getChildCount();i++){
            View childView = getChildAt(i);
            childView.layout(childView.getMeasuredWidth()*i,0,(i+1)*childView.getMeasuredWidth(),childView.getMeasuredHeight());
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

                xTransition = (int) (mLastX -event.getRawX());
                if(getScrollX()+xTransition<leftBorder){
                    return true;
                }else if(getScrollX()+getWidth()+xTransition>rightBorder){
                    return  true;
                }


                scrollBy(xTransition,0);
                mLastX= event.getRawX();

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
                int targetIndex = (getScrollX() + getWidth() / 2) / getWidth();
                int dx = targetIndex * getWidth() - getScrollX();
                // 第二步，调用startScroll()方法来初始化滚动数据并刷新界面
                mScroller.startScroll(getScrollX(), 0, dx, 0);
                invalidate();
                mLastX=0;
                break;
        }




        return true;
    }


    @Override
    public void computeScroll() {
        if(mScroller.computeScrollOffset()){
            scrollTo(mScroller.getCurrX(),mScroller.getCurrY());
            invalidate();
        }
    }
}
