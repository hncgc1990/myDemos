package com.hncgc1990.mylibrary;

import android.content.Context;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2017/2/14.
 */
public interface ILoadingView {

    ViewGroup initLoadingView(Context context, ViewGroup parent);

    int getHeight();

    void pushing(int percent);

    void loading();

    void stopLoading();


}
