package com.example.weixin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //    private TextView mTvCodeLogin;
    private String mr = "mr", mrsoft = "mrsoft";    //定义后台用户名与密码
    private String username, password;               //输入的用户名和密码

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText usernameET = (EditText) findViewById(R.id.username);     //获取用户名编辑框
        final EditText passwordET = (EditText) findViewById(R.id.password);      //获取密码编辑框
        Button button = findViewById(R.id.button);               //获取登录按钮
        // 获得SharedPreferences,并创建文件名称为"mrsoft"
        final SharedPreferences sp = getSharedPreferences("mrsoft", MODE_PRIVATE);
        final SharedPreferences.Editor editor = sp.edit();    // 获得Editor对象,用于存储用户名与密码信息

        //判断SharedPreferences文件中，用户名、密码是否存在
        if (username != null && password != null) {
            if (username.equals(mr) && password.equals(mrsoft)) {
                Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                startActivity(intent);
            }
        } else {
            //实现SharedPreferences文件不存在时，手动登录并存储用户名与密码
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String username = usernameET.getText().toString();            //获得输入的用户名
                    String password = passwordET.getText().toString();            //获得输入的密码
                    //如果输入用户名、密码与后台相同时，登录并存储
                    if (username.equals(mr) && password.equals(mrsoft)) { //判断输入的用户名密码是否正确
                        Toast.makeText(MainActivity.this, "用户名、密码正确", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, MainActivity2.class);  //通过Intent跳转登录后界面
                        startActivity(intent);                              //启动跳转界面
                        editor.putString("username", username);           //存储用户名
                        editor.putString("password", password);           //存储密码
                        editor.commit();                                    //提交信息
                        Toast.makeText(MainActivity.this, "已保存用户名密码", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
    }

//        editText.addTextChangedListener(new TextWatcher() {
//            private Context context;
//
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @SuppressLint("ResourceAsColor")
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                if (editText.length() > 0) {
//                    button.setEnabled(true);
//                } else {
//                    button.setEnabled(false);
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//            }
//        });
}


