package com.hncgc1990.myrefreshview.scroller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.hncgc1990.myrefreshview.R;

public class Main2Activity extends AppCompatActivity {


    ScrollerLinearLayout ll_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ll_content= (ScrollerLinearLayout) findViewById(R.id.ll_content);
    }



    public void scrool(View view){
        ll_content.scrollTo(-400,-900);
    }
    public void startStrool(View view){
        ll_content.startScroll();
    }
    public void springBack(View view){
        ll_content.springBack();
    }
    public void fling(View view){
        ll_content.fling();
    }
}
