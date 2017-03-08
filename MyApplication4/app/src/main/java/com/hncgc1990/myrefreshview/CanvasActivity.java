package com.hncgc1990.myrefreshview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hncgc1990.myrefreshview.canvas.CanvasRotateView;

public class CanvasActivity extends AppCompatActivity {


    CanvasRotateView canvasRotateView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas);
//        canvasRotateView= (CanvasRotateView) findViewById(R.id.rotateview);

    }
}
