package com.example.qq;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.view.WindowManager;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.qq.databinding.SplashBinding;

import static android.os.Build.VERSION_CODES.LOLLIPOP;

public class splashActivity extends AppCompatActivity {
    SplashViewModel myViewModel;
    SplashBinding splashBinding;
    MutableLiveData<Integer> time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myViewModel = ViewModelProviders.of(splashActivity.this).get(SplashViewModel.class);//获取viewModel实例
        splashBinding = DataBindingUtil.setContentView(this, R.layout.splash);//绑定dataBinding
        splashBinding.setLifecycleOwner(this);
        setWindows();//沉浸窗口
        time = myViewModel.getLiveData();//获取LiveData实例
        time.observe(this, new Observer<Integer>() {//观察dataBinding
            @Override
            public void onChanged(Integer integer) {
                if (integer == 0) {
                    Intent intent = new Intent(splashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    splashBinding.timeTextView.setText("剩余"+String.valueOf(integer)+"秒");
                }
            }
        });




    }




    private void setWindows() {
        //-------------------------沉浸化-----------------------
        if (Build.VERSION.SDK_INT >= LOLLIPOP) {
            int uiFlags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            uiFlags |= View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            getWindow().getDecorView().setSystemUiVisibility(uiFlags);
            //设置沉浸完毕
        }
    }




}

