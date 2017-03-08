package com.hncgc1990.myrefreshview.scroller;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Created by Administrator on 2017/2/28.
 */
public class OverScrollScrollView extends ScrollView {
    public OverScrollScrollView(Context context) {
        super(context);
    }

    public OverScrollScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public OverScrollScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(ev);
    }
}
