package com.uplooking.dell.gamenews.drawer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
import com.uplooking.dell.myjsondata.checkcode.CheckCode;
import com.uplooking.dell.myjsondata.checkcoderesult.CheckCodeResult;
import com.uplooking.dell.myjsondata.findpassword.NewPassword;
import com.uplooking.dell.myjsondata.findpassword.NewPasswordMessage;

import org.apache.http.entity.StringEntity;

import java.io.UnsupportedEncodingException;

/**
 *重设密码界面，获取到验证码并重设密码
 */
public class FindPasswordActivity extends AppCompatActivity {
    private static String SURE_PASSWORD_URL = "http://appapi2.gamersky.com/v2/FindPassword";
    private static String GET_VERIFICATION_CODE = "http://appapi2.gamersky.com/v2/GetVerificationCode";

    private String mStringVerificationCode, mUserName, mNewPassword, mSurePassword;
    private Gson gson;
    private MyFunction mFun,mFunO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_password);
        mFun = new MyFunction();
        mFunO = new MyFunction();
        gson = new Gson();
    }

    /**
     * 获取文本框的内容
     */
    private void getRequestMessage() {
        mStringVerificationCode = ((EditText) findViewById(R.id.findpw_et_identify_code)).getText().toString();
        mNewPassword = ((EditText) findViewById(R.id.findpw_et_new_password)).getText().toString();
        mSurePassword = ((EditText) findViewById(R.id.findpw_et_sure_password)).getText().toString();
        mUserName = getIntent().getStringExtra(MyData.EXTRA_KEY);

    }

    /**
     * 按键监听
     * @param v
     */
    public void findPWClick(View v) {
        getRequestMessage();
        switch (v.getId()) {
            case R.id.findpw_bt_get_identify_code:
                getVerificationCode();
                break;
            case R.id.findpw_visual_new_password:
                mFun.setPasswordVisual((EditText) findViewById(R.id.findpw_et_new_password),
                        (ImageView) findViewById(R.id.findpw_visual_new_password));
                break;
            case R.id.findpw_visual_sure_password:
                mFunO.setPasswordVisual((EditText) findViewById(R.id.findpw_et_sure_password),
                        (ImageView) findViewById(R.id.findpw_visual_sure_password));
                break;
            case R.id.findpw_submit:
                if (checkMessage()) {
                    requestChangePassword();
                }
                break;
        }
    }

    /**
     * 向服务器发送修改用户密码的请求
     */
    private void requestChangePassword() {
        RequestParams params = new RequestParams();

        NewPassword newPassword = new NewPassword();
        NewPasswordMessage request = newPassword.getrequest();
        request.setcodetype(1);
        request.setusername(mUserName);
        request.setnewPassword(mNewPassword);
        request.setVerificationCode(mStringVerificationCode);

        try {
            params.setBodyEntity(new StringEntity(gson.toJson(newPassword),"utf-8"));
            HttpUtils http = new HttpUtils(5 * 1000);
            http.send(HttpRequest.HttpMethod.POST,
                    SURE_PASSWORD_URL,
                    params,
                    new RequestCallBack<String>() {
                        @Override
                        public void onSuccess(ResponseInfo<String> responseInfo) {
                            CheckCodeResult result = gson.fromJson(responseInfo.result, CheckCodeResult.class);
                            if (result.geterrorCode() == 1) {
                                Toast.makeText(FindPasswordActivity.this, result.geterrorMessage(), Toast.LENGTH_SHORT).show();
                            } else {
                                /**
                                 * 修改成功后返回到登录界面，并销毁中间的界面
                                 */
                                Toast.makeText(FindPasswordActivity.this, "修改成功！", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(FindPasswordActivity.this,LoginActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                            }
                        }

                        @Override
                        public void onFailure(HttpException e, String s) {
                            Toast.makeText(FindPasswordActivity.this, "网络连接超时", Toast.LENGTH_SHORT).show();
                        }
                    });
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 检查是否输入密码，若输入密码则判断是否有验证码，都有则判断两次密码是否一致
     * @return
     */
    private boolean checkMessage() {
        if ("".equals(mNewPassword) && "".equals(mSurePassword)) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return false;
        } else if ("".equals(mStringVerificationCode)) {
            Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!mNewPassword.equals(mSurePassword)) {
            Toast.makeText(this, "两次密码不一致", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    /**
     * 获取验证码
     */
    private void getVerificationCode() {
        RequestParams params = new RequestParams();

        CheckCode checkCode = new CheckCode();
        checkCode.getrequest().setcodetype(3);
        checkCode.getrequest().setusername(mUserName);

        try {
            params.setBodyEntity(new StringEntity(gson.toJson(checkCode), "utf-8"));
            HttpUtils http = new HttpUtils(5 * 1000);
            http.send(HttpRequest.HttpMethod.POST,
                    GET_VERIFICATION_CODE,
                    params,
                    new RequestCallBack<String>() {

                        @Override
                        public void onSuccess(ResponseInfo<String> responseInfo) {
                            mFun.countDownTimer(59, (Button) findViewById(R.id.findpw_bt_get_identify_code));
                            CheckCodeResult result = gson.fromJson(responseInfo.result, CheckCodeResult.class);
                            if (result.geterrorCode() == 1) {
                                Toast.makeText(FindPasswordActivity.this, result.geterrorMessage(), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(FindPasswordActivity.this, result.getresult().toString(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(HttpException e, String s) {
                            Toast.makeText(FindPasswordActivity.this, "网络连接超时", Toast.LENGTH_SHORT).show();
                        }
                    });
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
