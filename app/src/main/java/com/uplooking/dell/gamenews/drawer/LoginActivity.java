package com.uplooking.dell.gamenews.drawer;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.uplooking.dell.gamenews.MyData;
import com.uplooking.dell.gamenews.MyFunction;
import com.uplooking.dell.gamenews.R;
import com.uplooking.dell.myjsondata.userlogin.UserLoginResult;
import com.uplooking.dell.myjsondata.userlogin.UserLoginRootData;

import org.apache.http.entity.StringEntity;

import java.io.UnsupportedEncodingException;

public class LoginActivity extends AppCompatActivity {
    private EditText mEditTextUser;
    private EditText mEditTextPassword;
    private ImageView mImageView;
    private String mUser;
    private String mPassword;
    private MyFunction mFun;
    private SharedPreferences mSharedPreferences;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findView();
        addUserName();
    }

    /**
     * 获取保存的用户名，如果保存的用户名不为空，则把用户名添加进输入框
     */
    private void addUserName() {
        mSharedPreferences = getSharedPreferences("user_name", Context.MODE_PRIVATE);
        String tmpUser = mSharedPreferences.getString(MyData.USER_NAME_KEY, "");
        if (!"".equals(tmpUser)) {
            mEditTextUser.setText(tmpUser);
        }
    }

    private void findView() {
        mEditTextUser = (EditText) findViewById(R.id.login_et_user);
        mEditTextPassword = (EditText) findViewById(R.id.login_et_pw);
        mImageView = (ImageView) findViewById(R.id.login_visual_pw);
        mFun = new MyFunction();
        gson = new Gson();
    }

    //按键监听
    public void loginClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.login_visual_pw:
                mFun.setPasswordVisual(mEditTextPassword, mImageView);
                break;
            case R.id.login_login:
                if (getUserData()) {
                    userLogin();
                }
                break;
            case R.id.login_forget_pw:
                intent.setClass(LoginActivity.this, ForgetPasswordActivity.class);
                startActivity(intent);
                break;
            case R.id.login_register_user:
                intent.setClass(LoginActivity.this, RegisterUserActivity.class);
                startActivity(intent);
                break;
            case R.id.login_by_qq:
                Toast.makeText(this, "功能完善中.......", Toast.LENGTH_SHORT).show();
                break;
            case R.id.login_by_weibo:
                Toast.makeText(this, "功能完善中.......", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    /**
     * 登录，登录成功后会保存登录状态，退出后再次进入会后台自动登录，
     * 并关闭登录页面，把相关信息返回给上一个页面
     */
    private void userLogin() {
        RequestParams params = new RequestParams();

        UserLoginRootData data = new UserLoginRootData();
        data.getrequest().setuserName(mUser);
        data.getrequest().setpassWord(mPassword);

        try {
            params.setBodyEntity(new StringEntity(gson.toJson(data), "utf-8"));
            HttpUtils http = new HttpUtils(5 * 1000);
            http.send(HttpRequest.HttpMethod.POST,
                    MyData.LOGIN_URL,
                    params,
                    new RequestCallBack<String>() {
                        AlertDialog dialog;

                        @Override
                        public void onSuccess(ResponseInfo<String> responseInfo) {
                            Log.i("login test","logining");
                            UserLoginResult userLoginResult = gson.fromJson(responseInfo.result, UserLoginResult.class);
                            if (userLoginResult.geterrorCode() == 1) {
                                Toast.makeText(LoginActivity.this, userLoginResult.geterrorMessage(), Toast.LENGTH_SHORT).show();
                            } else {
                                saveUser();
                                MyData.getDataInstance().setLoginState(true);
                                Intent intent = new Intent();
                                intent.putExtra(MyData.EXTRA_KEY, userLoginResult.getresult().getuserName());
                                setResult(0, intent);
                                LoginActivity.this.finish();
                            }
                        }

                        @Override
                        public void onStart() {
                            super.onStart();
                            Log.i("login test","start login");
                            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this)
                                    .setView(getLayoutInflater().inflate(R.layout.item_progress_bar, null));
                            dialog = builder.show();
                        }

                        @Override
                        public void onFailure(HttpException e, String s) {
                            Log.i("login test","fail login");
                            dialog.dismiss();
                            Toast.makeText(LoginActivity.this, "网络连接超时", Toast.LENGTH_SHORT).show();
                        }
                    });
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取输入框信息，并判断是否填写完整
     * @return
     */
    private boolean getUserData() {
        Log.i("login test","judge data");
        mUser = mEditTextUser.getText().toString();
        mPassword = mEditTextPassword.getText().toString();
        if ("".equals(mUser)) {
            Toast.makeText(this, "请输入用户名", Toast.LENGTH_SHORT).show();
            return false;
        } else if ("".equals(mPassword)) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    //保存用户名
    private void saveUser() {
        SharedPreferences.Editor ed = mSharedPreferences.edit();
        ed.putString(MyData.USER_NAME_KEY, mUser);
        ed.putString(MyData.PASSWORD_KEY, mPassword);
        ed.commit();
    }
}
