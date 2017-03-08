package com.hncgc1990.mylibrary;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/2/14.
 */
public class MyLoadingView implements ILoadingView {
    private TextView tvHeader;
    private   ViewGroup vgHeader;
    private MyCircleProgressView progressView;





    @Override
    public ViewGroup initLoadingView(Context context, ViewGroup parent) {

        //TODO 添加头部刷新的布局
        vgHeader = (ViewGroup) parent.inflate(context, R.layout.layout_header, null);
        vgHeader.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        tvHeader= (TextView) vgHeader.findViewById(R.id.tv_header);
        progressView= (MyCircleProgressView) vgHeader.findViewById(R.id.myCircleProgress);

        return vgHeader;
    }

    @Override
    public int getHeight() {
        return vgHeader.getHeight();
    }

    @Override
    public void pushing(int percent) {
        progressView.setPercent(percent);
        tvHeader.setText("松开开始刷新");
    }

    @Override
    public void loading() {
        progressView.startLodingAnim();
        tvHeader.setText("正在努力刷新中...");
    }

    @Override
    public void stopLoading() {
        progressView.stopLoadingAnim();
    }
}
