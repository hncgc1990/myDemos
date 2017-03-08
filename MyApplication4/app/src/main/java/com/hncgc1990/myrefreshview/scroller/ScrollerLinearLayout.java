package com.hncgc1990.myrefreshview.scroller;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;

/**
 * Created by Administrator on 2017/3/1.
 */
public class ScrollerLinearLayout extends LinearLayout{


    private OverScroller mScroller;

    public ScrollerLinearLayout(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        mScroller=new OverScroller(context,new DecelerateInterpolator());
    }

    public ScrollerLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ScrollerLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @Override
    public void computeScroll() {

        if(mScroller.computeScrollOffset()){
            scrollTo(mScroller.getCurrX(),mScroller.getCurrY());






            invalidate();
        }

    }

    public void startScroll(){
        mScroller.startScroll(getScrollX(),getScrollY(),-getScrollX(),-getScrollY());
        invalidate();
    }

    public void springBack(){
        mScroller.springBack(getScrollX(),getScrollY(),0,0,-200,-200);
        invalidate();
    }

    public void fling(){
        mScroller.fling(getScrollX(),getScrollY(),1,1,0,200,0,200);
        invalidate();
    }



}
