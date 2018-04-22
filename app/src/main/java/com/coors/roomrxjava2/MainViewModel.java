package com.coors.roomrxjava2;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;


public class MainViewModel {
    private DataModel dataModel = new DataModel();
    // ViewModel 的變數不可以為 private 會造成，在編譯時，DataBinding not found
    public  ObservableField<String> mData = new ObservableField<>();
    public  ObservableBoolean isLoading = new ObservableBoolean(false);

    public void refresh() {
        isLoading.set(true);
        dataModel.retrieveData(new DataModel.onDataReadyCallback() {
            @Override
            public void onDataReady(String jsonData) {
                mData.set(jsonData);
                isLoading.set(false);
            }
        });
    }

}
