package com.example.boxuegu;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ChangeUserInfoActivity extends AppCompatActivity {
    private TextView tv_main_title, tv_save;
    private RelativeLayout rl_title_bar;
    private TextView tv_back;
    private String title, content;
    private int flag;
    private EditText et_content;
    private ImageView iv_delete;
    private static final String TAG = "ChangeUserInfoActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_user_info);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        init();
    }

    /**
     * 初始化界面，
     */
    private void init() {
        // 从个人资料界面传递过来的标题和内容，UserInfoActivity.java会传递过来数据，然后修改界面会进行原始显示
        title = getIntent().getStringExtra("title");
        content = getIntent().getStringExtra("content");
        flag = getIntent().getIntExtra("flag", 0);
        // 初始化各个控件
        tv_main_title = (TextView) findViewById(R.id.tv_main_title);
        tv_main_title.setText(title);
        rl_title_bar = (RelativeLayout) findViewById(R.id.title_bar);
        rl_title_bar.setBackgroundColor(Color.parseColor("#30B4FF"));
        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_save = (TextView) findViewById(R.id.tv_save);
        tv_save.setVisibility(View.VISIBLE);
        et_content = (EditText) findViewById(R.id.et_content);
        iv_delete = (ImageView) findViewById(R.id.iv_delete);
        // 编辑框进行原始显示
        if(!TextUtils.isEmpty(content)){
            et_content.setText(content);
            et_content.setSelection(content.length());
        }
        // 监听修改界面输入的文字
        contentListener();
        // 返回键点击监听事件，退出当前，返回的应该是个人资料界面，UserInfoActivity.java
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeUserInfoActivity.this.finish();
            }
        });
        // 删除按钮的点击监听事件，控件内容设置为空
        iv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_content.setText("");
            }
        });
        // 保存按钮的点击监听事件
        tv_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = new Intent();
                // trim()的作用是去掉字符串两端的多余的空格,注意,是两端的空格,且无论两端的空格有多少个都会去掉
                String etContent = et_content.getText().toString().trim();
                switch (flag){
                    case 1:
                        if(!TextUtils.isEmpty(etContent)){
                            data.putExtra("nickName", etContent);
                            setResult(RESULT_OK, data);
                            Toast.makeText(ChangeUserInfoActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
                            Log.d(TAG, "onClick: 点击保存按钮，接收到了新的昵称为" + etContent + "并且要回传数据给用户信息界面");
                            // 这时候终止当前活动，返回上一级，那么就是UserInfoActivity.java，并且返回最新数据给UserInfoActivity
                            ChangeUserInfoActivity.this.finish();
                        }else{
                            Toast.makeText(ChangeUserInfoActivity.this, "昵称不能为空", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 2:
                        if(!TextUtils.isEmpty(etContent)){
                            data.putExtra("signature", etContent);
                            setResult(RESULT_OK, data);
                            Toast.makeText(ChangeUserInfoActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
                            // 这时候终止当前活动，返回上一级，那么就是UserInfoActivity.java，并且返回最新数据给UserInfoActivity
                            ChangeUserInfoActivity.this.finish();
                        }else{
                            Toast.makeText(ChangeUserInfoActivity.this, "签名不能为空", Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
            }
        });
    }

    /**
     * 监听个人资料修改界面输入的文字
     */
    private void contentListener() {
        et_content.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Editable editable = et_content.getText();
                int len = editable.length(); // 输入的文本的长度
                if(len > 0){
                    iv_delete.setVisibility(View.VISIBLE);
                }else{
                    iv_delete.setVisibility(View.GONE);
                }
                switch (flag){
                    case 1: // 昵称，昵称限制最多8个文字，超过8个需要截取掉多余的文字
                        if(len>8){
                            int selEndIndex = Selection.getSelectionEnd(editable);
                            String str = editable.toString();
                            // 截取新字符串
                            String newStr = str.substring(0, 8);
                            et_content.setText(newStr);
                            editable = et_content.getText();
                            // 新字符串长度
                            int newLen = editable.length();
                            // 旧光标位置超过新字符串的长度
                            if(selEndIndex > newLen){
                                selEndIndex = editable.length();
                            }
                            // 设置新光标所在的位置
                            Selection.setSelection(editable, selEndIndex);
                        }
                        break;
                    case 2: // 签名，签名最多是16个文字，超过16个需要接去掉多余的文字
                        if(len > 16){
                            int selEndIndex = Selection.getSelectionEnd(editable);
                            String str = editable.toString();
                            // 截取新字符串
                            String newStr = str.substring(0, 16);
                            et_content.setText(newStr);
                            editable = et_content.getText();
                            // 新字符串的长度
                            int newLen = editable.length();
                            // 旧光标位置超过新字符串的长度
                            if(selEndIndex > newLen){
                                selEndIndex = editable.length();
                            }
                            // 设置新光标所在的位置
                            Selection.setSelection(editable, selEndIndex);
                        }
                        break;
                    default:
                        break;
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
