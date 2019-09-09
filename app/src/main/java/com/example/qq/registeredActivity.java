package com.example.qq;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import static android.os.Build.VERSION_CODES.LOLLIPOP;

public class registeredActivity extends AppCompatActivity {

    boolean state = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setWindows();//沉浸窗口
        setContentView(R.layout.registered);
        setTextComplish();//设置输入账号信息
        setButtonPost();//设置按钮单击事件
    }

    private void setButtonPost() {
        ImageView imageView = findViewById(R.id.button_register_post);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (state == true) {
//                    Toast.makeText(registeredActivity.this, "暂不开放注册!",Toast.LENGTH_LONG).show();

                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(registeredActivity.this);
                    alertDialog.setTitle("暂不开放注册");
                    alertDialog.setMessage("*Test User:\n\nNumber:9292922\n\nPassword:a9292922");
                    alertDialog.setPositiveButton("了解", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    alertDialog.show();

                }
            }
        });
    }

    private void setTextComplish() {
        final EditText editText_num = findViewById(R.id.editText_register_num);
        final ImageView button_post = findViewById(R.id.button_register_post);

        //监听编辑框账号是否正确
        editText_num.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (editText_num.getText().toString().length() >= 5 && editText_num.getText().toString().length() <= 11) {//满足条件
                    button_post.setImageResource(R.drawable.icon_on);
                    state = true;
                } else {
                    button_post.setImageResource(R.drawable.icon_off);
                    state = false;
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

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

    public void back_MainActivity(View view) {
        Intent intent = new Intent(registeredActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
