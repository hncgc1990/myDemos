package com.hncgc1990.myrefreshview;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;

import com.hncgc1990.mylibrary.MyCircleProgressView;
import com.hncgc1990.mylibrary.MyRefreshView;
import com.hncgc1990.myrefreshview.dummy.DummyContent;
import com.hncgc1990.myrefreshview.dummy.RefreshTask;

public class MainActivity extends AppCompatActivity implements ItemFragment.OnListFragmentInteractionListener{

    ViewPager viewPager;
    MyRefreshView myfreshview;



    MyCircleProgressView progressView;
    SeekBar seekBar;



    EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager= (ViewPager) findViewById(R.id.viewPager);
        myfreshview= (MyRefreshView) findViewById(R.id.myfreshview);
        myfreshview.setRefreshListener(new MyRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
               new RefreshTask(){
                   @Override
                   public void finish() {
                       myfreshview.finishRefresh();
                   }
               }.execute();
            }
        });

        editText= (EditText) findViewById(R.id.et_input);

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return ItemFragment.newInstance(1);
            }

            @Override
            public int getCount() {
                return 3;
            }
        });


        progressView= (MyCircleProgressView) findViewById(R.id.ciclrProgressView);
        seekBar= (SeekBar) findViewById(R.id.seekbar);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

//                Log.d("chen",progress+"%");
                progressView.setPercent(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });




    }


    public void editBtn(View view){
        progressView.setPercent(Integer.parseInt(editText.getText().toString()));
    }


    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }
}
