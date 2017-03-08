package com.hncgc1990.myrefreshview.scroller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.hncgc1990.myrefreshview.R;

public class ScrollerDemoActivity extends AppCompatActivity {


    JellyTextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroller_demo);
        textView= (JellyTextView) findViewById(R.id.text);
    }


    public void editBtn(View view){

//        RelativeLayout button = (RelativeLayout) view;
//        button.scrollBy(-20,-20);



    }


    public void fling(View view){
        textView.fling();
    }
}
