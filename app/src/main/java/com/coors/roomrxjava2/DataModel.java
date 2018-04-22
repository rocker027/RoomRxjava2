package com.coors.roomrxjava2;


import android.os.Handler;

class DataModel {
    interface onDataReadyCallback {
        void onDataReady(String jsonData);
    }


    public void retrieveData(final onDataReadyCallback callback) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                callback.onDataReady("Data Get");
            }
        },3000);
    }
}
