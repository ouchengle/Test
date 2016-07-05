package com.uplooking.dell.gamenews.drawer;

import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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
import com.uplooking.dell.gamenews.MyFunction;
import com.uplooking.dell.gamenews.R;
import com.uplooking.dell.myjsondata.checkcode.CheckCode;
import com.uplooking.dell.myjsondata.checkcoderesult.CheckCodeResult;
import com.uplooking.dell.myjsondata.register.RegisterClass;
import com.uplooking.dell.myjsondata.register.RegisterMessage;

import org.apache.http.entity.StringEntity;

import java.io.UnsupportedEncodingException;

public class RegisterUserActivity extends AppCompatActivity {
    private static String CHECK_CODE_URL = "http://appapi2.gamersky.com/v2/GetVerificationCode";
    private static String SEND_REGISTER_MESSAGE_URL = "http://appapi2.gamersky.com/v2/SubmitRegistrationInfo";

    private Button mButton;
    private String mStringTel, mStringUser, mStringPW, mStringCheakCode;
    private MyFunction mFun;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        mFun = new MyFunction();
        mButton = (Button) findViewById(R.id.register_bt_get_identify_code);

        //setBar();
    }

    //获取输入框的内容
    private void getMessage() {
        mStringTel = ((EditText) findViewById(R.id.register_et_tel)).getText().toString();
        mStringUser = ((EditText) findViewById(R.id.register_et_user)).getText().toString();
        mStringPW = ((EditText) findViewById(R.id.register_et_pw)).getText().toString();
        mStringCheakCode = ((EditText) findViewById(R.id.register_et_identify_code)).getText().toString();
    }

    //按键监听
    public void registerClick(View v) {
        getMessage();
        gson = new Gson();
        switch (v.getId()) {
            case R.id.register_visual_pw:
                mFun.setPasswordVisual((EditText) findViewById(R.id.register_et_pw), (ImageView) findViewById(R.id.register_visual_pw));
                break;
            case R.id.register_bt_get_identify_code:
                getCheckCode();
                break;
            case R.id.register_sure:
                sendRegisterMessage();
                break;
            case R.id.register_agreement:
                Toast.makeText(this, "暂时没有任何条款", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    //发送注册信息到服务器前判断输入信息是否完整
    private void sendRegisterMessage() {
        if (!"".equals(mStringTel) && !"".equals(mStringUser) && !"".equals(mStringPW) && !"".equals(mStringCheakCode)) {
            sendRegisterMessageToServer();
        } else {
            Toast.makeText(this, "请完善信息", Toast.LENGTH_SHORT).show();
        }
    }

    //发送注册信息到服务器
    private void sendRegisterMessageToServer() {
        RequestParams params = new RequestParams();

        RegisterClass registerClass = new RegisterClass();
        RegisterMessage request = registerClass.getrequest();
        request.setphoneNumber(mStringTel);
        request.setuserName(mStringUser);
        request.setpassword(mStringPW);
        request.setphoneVerificationCode(mStringCheakCode);

        try {
            params.setBodyEntity(new StringEntity(gson.toJson(registerClass),"utf-8"));
            HttpUtils http = new HttpUtils(10*1000);
            http.send(HttpRequest.HttpMethod.POST,
                    SEND_REGISTER_MESSAGE_URL,
                    params,
                    new RequestCallBack<String>() {
                        @Override
                        public void onSuccess(ResponseInfo<String> responseInfo) {
                            CheckCodeResult result = gson.fromJson(responseInfo.result, CheckCodeResult.class);
                            if(result.geterrorCode() == 1){
                                Toast.makeText(RegisterUserActivity.this,result.geterrorMessage(),Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(RegisterUserActivity.this,result.getresult().toString(),Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(HttpException e, String s) {
                            Toast.makeText(RegisterUserActivity.this,"网络连接超时",Toast.LENGTH_SHORT).show();
                        }
                    });
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    //获取验证码前检查用户名是否为空，通过正则表达式对手机号码经行判断
    private void getCheckCode() {
        if (!"".equals(mStringTel) && !"".equals(mStringUser)) {
            if(checkTelNum()){
                getCheckCodeFromServer();
            }else {
                Toast.makeText(this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "请输入手机号和用户名", Toast.LENGTH_SHORT).show();
        }
    }

    //正则表达式判断手机号码格式
    private boolean checkTelNum(){
        String telRegex = "[1][358]\\d{9}";
        if(TextUtils.isEmpty((mStringTel))){
            return false;
        }else {
            return mStringTel.matches(telRegex);
        }
    }

    //向服务器发送获取验证码的请求
    private void getCheckCodeFromServer() {
        //已实现获取验证码发送请求，需要接收短信是否发送成功的json数据
        RequestParams params = new RequestParams();

        CheckCode checkCode = new CheckCode();
        checkCode.getrequest().setphoneNumber(mStringTel);
        checkCode.getrequest().setcodetype(1);
        checkCode.getrequest().setusername(mStringUser);

        try {
            params.setBodyEntity(new StringEntity(gson.toJson(checkCode), "utf-8"));
            Log.i("test",gson.toJson(checkCode));
            HttpUtils httpUtils = new HttpUtils(10*1000);
            httpUtils.send(HttpRequest.HttpMethod.POST,
                    CHECK_CODE_URL,
                    params,
                    new RequestCallBack<String>() {

                        @Override
                        public void onSuccess(ResponseInfo<String> responseInfo) {
                            Log.i("test", responseInfo.result);
                            mFun.countDownTimer(59,mButton);
                            CheckCodeResult result = gson.fromJson(responseInfo.result, CheckCodeResult.class);
                            if(result.geterrorCode() == 1){
                                Toast.makeText(RegisterUserActivity.this,result.geterrorMessage(),Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(RegisterUserActivity.this,result.getresult().toString(),Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(HttpException e, String s) {
                            Toast.makeText(RegisterUserActivity.this,"网络连接超时",Toast.LENGTH_SHORT).show();
                        }
                    });
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
