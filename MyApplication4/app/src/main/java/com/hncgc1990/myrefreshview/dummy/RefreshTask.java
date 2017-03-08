package com.hncgc1990.myrefreshview.dummy;

import android.os.AsyncTask;

/**
 * Created by Administrator on 2017/2/9.
 */
public abstract class RefreshTask extends AsyncTask<Void,Void,Void>{
    @Override
    protected Void doInBackground(Void... params) {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        finish();

    }

    public abstract void finish();
}
