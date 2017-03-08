package com.hncgc1990.mylibrary;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.FrameLayout;


/**
 */
public class MyRefreshView extends FrameLayout {


    public static final String CHEN = "chen";

    /**
     * 最大下拉距离
     */
    private float maxPushDistance=300;

    private ViewGroup.MarginLayoutParams layoutParams;

    private  int mTouchSlop;

    /**
     * 包裹的子View
     */
    private View contentView;

    private OnRefreshListener mRefreshListener;

    private ILoadingView mLoadingView=new MyLoadingView();

    /**
     * 刷新布局的高度
     */
    private int mHeaderHeight;

    float downY=0;
    float downX=0;

    float lastY=0;


    float yDistance=0;

    float yMoveDistance=0;



    boolean isPushing=false;


    public MyRefreshView(Context context) {
        super(context);
        init();
    }



    public MyRefreshView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyRefreshView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }
    private void init() {
        ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext());
        mTouchSlop = viewConfiguration.getScaledTouchSlop();

        Log.d(CHEN,"mTouchSlop"+mTouchSlop);

    }



    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        post(new Runnable() {
            @Override
            public void run() {
                mHeaderHeight=mLoadingView.getHeight();
                Log.d(CHEN,"onAttachedToWindow______"+mHeaderHeight);
            }
        });

        Log.d(CHEN,"onAttachedToWindow");

    }




    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        Log.d(CHEN,"onFinishInflate");

        contentView = getChildAt(0);
        if(contentView==null)
            throw new RuntimeException("My RefreshView must has a child view!");
        layoutParams = (MarginLayoutParams) contentView.getLayoutParams();


        addView(mLoadingView.initLoadingView(getContext(),this),0);

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        Log.d(CHEN,"onInterceptTouchEvent"+ev.getAction());

        if(isPushing) return super.onInterceptTouchEvent(ev);


        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                downY=ev.getRawY();
                downX=ev.getX();
                lastY=ev.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                yDistance=ev.getRawY()-lastY;
                yMoveDistance=ev.getRawY()-downY;
                if(Math.abs(yMoveDistance)>Math.abs(ev.getRawX()-downX)){
                    return true;
                }

                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                yDistance=0;
                break;
            default:
                break;
        }


        return super.onInterceptTouchEvent(ev);
    }





    @Override
    public boolean onTouchEvent(MotionEvent ev) {

//        Log.d(CHEN,"onTouchEvent"+ev.getAction());
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                downY=ev.getRawY();
                downX=ev.getX();
                lastY=ev.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                yDistance=ev.getRawY()-lastY;
                yMoveDistance=ev.getRawY()-downY;

                lastY=ev.getY();

                //TODO 进行下拉最大距离的限制
                if(yMoveDistance>maxPushDistance) yMoveDistance=maxPushDistance;


                //TODO 进行整个下拉内容的的移动
                if(yMoveDistance>0){
                    layoutParams.topMargin= (int) yMoveDistance;
                    contentView.setLayoutParams(layoutParams);
                    isPushing=true;
                    pushing((int) (yMoveDistance/mHeaderHeight*100));
                    //TODO 进行头部刷新动画的播放
                }

                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:

                //下拉的距离没有超过刷新高度,则回弹
                if(yMoveDistance<mHeaderHeight){
                    finishRefresh((int)yMoveDistance);
                }else{
                    if(isPushing){
                        pushFinish();
                    }
                }
                yDistance=0;


                break;
            default:
                break;
        }
        return true;
    }

    /**
     * 正在下拉
     */
    private void pushing(int percent) {
        mLoadingView.pushing(percent);

    }

    /**
     *回弹到头部高度位置，显示正在刷新布局
     */
    private void pushFinish() {
       mLoadingView.loading();
        ValueAnimator animator=ValueAnimator.ofInt(0,100);
        animator.setDuration(400);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                layoutParams.topMargin= (int) ((yMoveDistance -mHeaderHeight)*(1-animation.getAnimatedFraction()))+mHeaderHeight;
                contentView.setLayoutParams(layoutParams);
                if(animation.getAnimatedFraction()==1){
                    if(mRefreshListener!=null){
                        mRefreshListener.onRefresh();
                    }
                }
            }
        });
        animator.start();
    }

    public void finishRefresh(){
        finishRefresh(mHeaderHeight);
       mLoadingView.stopLoading();
    }


    /**
     * 完成刷新 回弹到原状
     */
    public void finishRefresh(final int distance){
        isPushing=false;

        ValueAnimator animator=ValueAnimator.ofInt(0,100);
        animator.setDuration(300);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                layoutParams.topMargin= (int) ((distance)*(1-animation.getAnimatedFraction()));
                contentView.setLayoutParams(layoutParams);
            }
        });
        animator.start();

    }

    public interface OnRefreshListener{
        void onRefresh();

    }

    public void setRefreshListener(OnRefreshListener mRefreshListener) {
        this.mRefreshListener = mRefreshListener;
    }
}
