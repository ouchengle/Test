package com.uplooking.dell.gamenews;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.uplooking.dell.gamenews.drawer.LoginActivity;
import com.uplooking.dell.myjsondata.userlogin.UserLoginResult;
import com.uplooking.dell.myjsondata.userlogin.UserLoginRootData;

import org.apache.http.entity.StringEntity;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;

/**
 * 左侧滑栏的相关代码
 */
public class MainLeftDrawerLayout extends Fragment implements View.OnClickListener {
    private View view;//侧滑栏fragment的view
    private TextView mTextView;//“立即登录”的textview
    private SharedPreferences mSharedPreferences;
    private Gson gson;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.main_left_drawer_layout, null);
        getListener();
        checkLoginState();
        return view;
    }

    /**
     * 检查登录状态，通过SharedPreferences获取保存的密码是否为空来判断推出前是否登录
     * 若为空，则直接返回，若不为空，则调用登录方法
     */
    private void checkLoginState() {
        mSharedPreferences = getActivity().getSharedPreferences("user_name", Context.MODE_PRIVATE);
        String tmpUser = mSharedPreferences.getString(MyData.USER_NAME_KEY, "");
        String tmpPassword = mSharedPreferences.getString(MyData.PASSWORD_KEY, "");
        if ("".equals(tmpUser) || "".equals(tmpPassword)) {
            return;
        } else {
            gson = new Gson();
            loginBack(tmpUser, tmpPassword);
        }
    }

    /**
     * 退出前是已登录状态，再次打开时执行该方法
     * 使用xUtils的Http协议传送json数据实现登录
     *
     * @param tmpUser     用户名
     * @param tmpPassword 密码
     */
    private void loginBack(String tmpUser, String tmpPassword) {
        RequestParams params = new RequestParams();

        UserLoginRootData data = new UserLoginRootData();
        data.getrequest().setuserName(tmpUser);
        data.getrequest().setpassWord(tmpPassword);

        try {
            params.setBodyEntity(new StringEntity(gson.toJson(data), "utf-8"));
            HttpUtils http = new HttpUtils(5 * 1000);
            http.send(HttpRequest.HttpMethod.POST,
                    MyData.LOGIN_URL,
                    params,
                    new RequestCallBack<String>() {
                        @Override
                        public void onSuccess(ResponseInfo<String> responseInfo) {
                            UserLoginResult userLoginResult = gson.fromJson(responseInfo.result, UserLoginResult.class);
                            /*登录成功后把侧滑栏“立即登录字体”改为用户名*/
                            setUserNameStyle(userLoginResult.getresult().getuserName(), 0xff333333);
                            /*设置当前登录状态*/
                            MyData.getDataInstance().setLoginState(true);
                        }

                        @Override
                        public void onFailure(HttpException e, String s) {

                        }
                    });
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取空间的view，添加侧滑栏每个功能的监听
     */
    private void getListener() {
        mTextView = (TextView) view.findViewById(R.id.left_drawer_login_tv);

        setListener(R.id.left_drawer_login_iv);
        setListener(R.id.left_drawer_login_tv);
        setListener(R.id.left_drawer_collect);
        setListener(R.id.left_drawer_search);
        setListener(R.id.left_drawer_news);
        setListener(R.id.left_drawer_app_manage);
        setListener(R.id.left_drawer_clean_cache);
        setListener(R.id.left_drawer_night);
        setListener(R.id.left_drawer_feedback);
        setListener(R.id.left_drawer_more_set);
    }

    /**
     * 设置监听，此方法是为了减少代码的重复率
     *
     * @param Id 相应控件ID
     */
    private void setListener(int Id) {
        view.findViewById(Id).setOnClickListener(this);
    }

    /**
     * 登录后显示用户名
     * @param name 用户名
     * @param color 字体大小
     */
    private void setUserNameStyle(String name, int color) {
        mTextView.setText(name);
        mTextView.setTextColor(color);
    }

    /**
     * 获取返回回来的用户名信息
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            if (requestCode == 1) {
                if (resultCode == 0) {
                    setUserNameStyle(data.getStringExtra(MyData.EXTRA_KEY), 0xff333333);
                }
            }
        }
    }

    /**
     * 侧滑栏的按键监听相应事件
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            /*点击头像或“立即登录”（用户名）字体，若检查到登录状态为已登录，则弹出对话框
            若未登录，则打开一个可获取返回值的登录界面*/
            case R.id.left_drawer_login_iv:
            case R.id.left_drawer_login_tv:
                if (MyData.getDataInstance().isLoginState()) {
                    showTipDialog();
                } else {
                    startActivityForResult(new Intent(getActivity(), LoginActivity.class), 1);
                }
                break;
            case R.id.left_drawer_clean_cache:
                cleanCacheDialog();
                break;
        }

    }

    /**
     * 弹出当前缓存大小，并提示是否清除缓存
     */
    private void cleanCacheDialog(){
        new AlertDialog.Builder(getActivity())
                .setTitle("提示")
                .setMessage("当前缓存大小为" + getCacheSize() + ",是否清空缓存？")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        BitmapUtils bitmapUtils = new BitmapUtils(getActivity());
                        bitmapUtils.clearDiskCache();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
    }

    /**
     * 获取当前缓存大小
     */
    private String getCacheSize() {
        double size = 0;
        File file = new File(getActivity().getExternalCacheDir() + "/xBitmapCache");
        File[] files = file.listFiles();
        for (int i = 0; i < files.length; i++) {
            size = size + files[i].length();
        }
        Log.i("size", size + "");
        if (size < 1024) {
            return "0KB";
        }
        if (size >= 1024 && size < 1024 * 1024) {
            BigDecimal bd = new BigDecimal(size/1024);
            bd = bd.setScale(1,BigDecimal.ROUND_HALF_UP);
            Log.i("size",bd + "");
            return bd + "KB";
        }
        if (size >= 1024 * 1024 && size < 1024 * 1024 * 1024) {
            BigDecimal bd = new BigDecimal(size/(1024*1024));
            bd = bd.setScale(1,BigDecimal.ROUND_HALF_UP);
            Log.i("size", bd + "");
            return bd + "MB";
        }
        if (size >= 1024 * 1024 * 1024) {
            BigDecimal bd = new BigDecimal(size/(1024*1024*1024));
            bd = bd.setScale(1,BigDecimal.ROUND_HALF_UP);
            Log.i("size", bd + "");
            return bd + "GB";
        }
        return "0KB";
    }

    /**
     * 若已登录，则弹出对话框，提示是否退出登录
     * 点击确定，执行退出登录方法
     */
    private void showTipDialog() {
        new AlertDialog.Builder(getActivity())
                .setTitle("提示")
                .setMessage("退出当前账号？")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        offLogin();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
    }

    /**
     * 退出登录，并设置存储的密码为“”
     */
    private void offLogin() {
        MyData.getDataInstance().setLoginState(false);
        setUserNameStyle("立即登录", 0xff707070);
        SharedPreferences.Editor ed = mSharedPreferences.edit();
        ed.putString(MyData.PASSWORD_KEY, "");
        ed.commit();
    }
}
