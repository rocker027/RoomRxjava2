package com.coors.roomrxjava2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Cancellable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();
    private Button button;
    private EditText editText;
    private String text = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Observable<String> test = getTextObs();
        test.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.d(TAG, "accept: " + s);
            }
        });

        Observable.interval(5, TimeUnit.SECONDS)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Log.d(TAG, "accept: " + "5秒到了");
                        text = "100";
                    }
                });
        findViews();
    }

    @Override
    protected void onStart() {
        super.onStart();




        Observable<String> buttonOnClickObservable = createSearchButtonOnClickObservable();
        buttonOnClickObservable
                // 指定 subscribe 也就是訂閱當下使用new thread
                .subscribeOn(Schedulers.newThread())
                // 將map開始切換為 io執行緒 ，一旦呼叫observeOn（）後，便不可在呼叫 subscribeOn()
                .observeOn(Schedulers.io())
                // 追加操作符 map 轉換，將耗時工作在io thread執行
                .map(new Function<String, Integer>() {
                    @Override
                    public Integer apply(String s) throws Exception {
                        int resoult = 0;
                        switch (s) {
                            case "name":
                                resoult = 0;
                                break;
                            case "a":
                                resoult = 1;
                                break;
                            case "b":
                                resoult = 2;
                                break;
                            case "c":
                                resoult = 3;
                                break;
                        }
                        return resoult;
                    }
                })
                // 指定observer 使用main thread
                .observeOn(AndroidSchedulers.mainThread())
                // 訂閱觀察者
                .subscribe(
                        // 不關心 onSubscribe, onError, onComplete 可以直接用Consumer
                        new Consumer<Integer>() {
                            @Override
                            public void accept(Integer i) throws Exception {
                                Log.d(TAG, "accept: " + i);
                            }
                        // 傳統 Observer 捕捉所有callback
//                new Observer<String>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                        Log.d(TAG, "onSubscribe: ");
//                    }
//
//                    @Override
//                    public void onNext(String s) {
//                        Log.d(TAG, "onNext: " + s);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.d(TAG, "onError: ");
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        Log.d(TAG, "onComplete: ");
//                    }


    });

    }

    private void findViews() {
        button = findViewById(R.id.button);
        editText = findViewById(R.id.editText);
    }

    private Observable<String> createSearchButtonOnClickObservable(){
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(final ObservableEmitter<String> emitter) throws Exception {
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        emitter.onNext(editText.getText().toString());
                    }
                });

                // 取消
                emitter.setCancellable(new Cancellable() {
                    @Override
                    public void cancel() throws Exception {
                        button.setOnClickListener(null);
                    }
                });

            }
        });
    }

    private Observable<String> getTextObs() {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                if (text == null) {
                    e.onNext("目前沒有喔");
                } else {
                    e.onNext("有東西～爽");
                }

            }
        });
    }


}
