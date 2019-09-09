package com.example.qq;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.qq.databinding.ActivityMainBinding;

import static android.os.Build.VERSION_CODES.LOLLIPOP;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding activityMainBinding;
    public boolean state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        activityMainBinding.setLifecycleOwner(this);
        setWindows();//设置窗口沉浸
        setTextOnchange();//设置编辑框文本改变事件
        setEnter();
    }

    public boolean getState() {//获取按钮状态
        return state;
    }
    private void setEnter() {
        ImageView imageView = findViewById(R.id.imageView_enter);
        ImageView imageView1 = findViewById(R.id.imageView_enter2);
        FrameLayout frameLayout = findViewById(R.id.enter);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (getState()) {//按钮是否可被点击
                    try {
                        if (activityMainBinding.editTextNum.getText().toString().equals("9292922") && activityMainBinding.editTextPassword.getText().toString().equals("a9292922")) {
                            Intent intent = new Intent(MainActivity.this, HomePageActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(MainActivity.this, "账号或密码不正确", Toast.LENGTH_LONG).show();
                        }
                    } catch (Exception e) {
                        Log.d("调试", e.toString());
                    }

                } else {
                    Toast.makeText(MainActivity.this, "按钮不可点击", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void setTextOnchange() {
        activityMainBinding.editTextNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String num;
                String password;
                num = activityMainBinding.editTextNum.getText().toString();
                password = activityMainBinding.editTextPassword.getText().toString();
                if ((!num.equals("") && (!password.equals("")))) {
                    //可用
                    activityMainBinding.imageViewEnter.setImageResource(R.drawable.icon_enter_background_blue);
                    setState(true);
                } else {
                    //不可用
                    activityMainBinding.imageViewEnter.setImageResource(R.drawable.icon_background_whrit);
                   setState(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {


            }
        });

        activityMainBinding.editTextPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String num;
                String password;
                num = activityMainBinding.editTextNum.getText().toString();
                password = activityMainBinding.editTextPassword.getText().toString();
                if ((!num.equals("") && (!password.equals("")))) {
                    activityMainBinding.imageViewEnter.setImageResource(R.drawable.icon_enter_background_blue);
                } else {
                    activityMainBinding.imageViewEnter.setImageResource(R.drawable.icon_background_whrit);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //绑定跳转注册事件
        activityMainBinding.textViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, registeredActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setState(boolean b) {//设置按钮状态
        state = b;
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
