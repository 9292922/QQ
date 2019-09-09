package com.example.qq;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.logging.LogRecord;

public class SplashViewModel extends ViewModel {
    private MutableLiveData<Integer> time;
    public SplashViewModel() {
        this.time = new MutableLiveData<>();
        countTime();//倒计时
    }

    public void countTime() {
        //1.5秒广告
        this.time.setValue(1);
        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                time.setValue(0);
            }
        }, 1500);

    }

    private void countTime(int num) {//第二种方式
        this.time.setValue(num);
        Handler handler = new Handler();
        for (int i = 0; i < num; i++) {
            try {
                Thread.sleep(1000);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        time.setValue(time.getValue() - 1);
                    }
                });
            } catch (Exception e) {
                Log.d("Exception", e.toString());
            }


        }
    }


    public MutableLiveData<Integer> getLiveData() {
        return this.time;//返回liveData
    }
}
