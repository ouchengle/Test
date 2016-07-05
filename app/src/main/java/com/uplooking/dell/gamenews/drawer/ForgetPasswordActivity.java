package com.uplooking.dell.gamenews.drawer;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.uplooking.dell.gamenews.MyData;
import com.uplooking.dell.gamenews.R;
import com.uplooking.dell.myjsondata.checkcode.CheckCode;
import com.uplooking.dell.myjsondata.checkcoderesult.CheckCodeResult;
import com.uplooking.dell.myjsondata.findpassword.GetUserTel;
import com.uplooking.dell.myjsondata.usertelresult.UserTelResult;

import org.apache.http.entity.StringEntity;

import java.io.UnsupportedEncodingException;

public class ForgetPasswordActivity extends AppCompatActivity {
    private static String GET_USER_TEL_URL = "http://appapi2.gamersky.com/v2/TwoGetCodeInformation";

    private String mUserName;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
    }

    //按键监听
    public void fpwClick(View v) {
        mUserName = ((EditText) findViewById(R.id.forget_PW_et_user)).getText().toString();
        gson = new Gson();
        switch (v.getId()) {
            case R.id.forget_PW_submit:
                if ("".equals(mUserName)) {
                    Toast.makeText(this, "请输入用户名", Toast.LENGTH_SHORT).show();
                } else {
                    submitUsernameToServer();
                }
                break;
        }
    }

    //获取用户手机号码信息
    private void submitUsernameToServer() {
        RequestParams params = new RequestParams();

        GetUserTel getUserTel = new GetUserTel();
        getUserTel.getrequest().setusername(mUserName);
        try {
            params.setBodyEntity(new StringEntity(gson.toJson(getUserTel), "utf-8"));
            HttpUtils http = new HttpUtils(5 * 1000);
            http.send(HttpRequest.HttpMethod.POST,
                    GET_USER_TEL_URL,
                    params,
                    new RequestCallBack<String>() {
                        @Override
                        public void onSuccess(ResponseInfo<String> responseInfo) {
                            Log.i("test", responseInfo.result);
                            UserTelResult userTelResult = gson.fromJson(responseInfo.result, UserTelResult.class);
                            if (userTelResult.geterrorCode() == 1) {
                                Toast.makeText(ForgetPasswordActivity.this, userTelResult.geterrorMessage(), Toast.LENGTH_SHORT).show();
                            } else {
                                checkUserTelDialog(userTelResult.getresult().getphoneNumber());
                            }
                        }

                        @Override
                        public void onFailure(HttpException e, String s) {
                            Toast.makeText(ForgetPasswordActivity.this,"网络连接超时",Toast.LENGTH_SHORT).show();
                        }
                    });
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    //弹出用户信息对话框
    private void checkUserTelDialog(String telNum) {
        new AlertDialog.Builder(this)
                .setTitle("请确认信息：")
                .setMessage("尊敬的用户”" + mUserName + "“，你绑定的手机号为："
                        + telNum + "，确定修改密码")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(ForgetPasswordActivity.this,FindPasswordActivity.class);
                        intent.putExtra(MyData.EXTRA_KEY,mUserName);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
    }
}
