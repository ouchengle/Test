package com.uplooking.dell.gamenews.drawer;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.uplooking.dell.gamenews.R;

/**
 *侧滑栏打开相应界面的toolbar的fragment
 * Created by dell on 2015/12/23.
 */
public class DrawerActivityBar extends Fragment implements View.OnClickListener{
    private TextView mTextView;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.drawer_activity_bar,null);
        setBarTitle();
        view.findViewById(R.id.drawer_bar_back).setOnClickListener(this);
        return view;
    }

    /**
     * 设置toolbar的标题：根据当前界面来判断类型进行设置
     * （标题不全，需添加）
     */
    private void setBarTitle() {
        mTextView = (TextView) view.findViewById(R.id.drawer_bar_title);
        if(getActivity() instanceof LoginActivity) {
            mTextView.setText("登录");
        }
        if(getActivity() instanceof ForgetPasswordActivity){
            mTextView.setText("找回密码");
        }
        if(getActivity() instanceof FindPasswordActivity){
            mTextView.setText("找回密码");
        }
        if(getActivity() instanceof RegisterUserActivity){
            mTextView.setText("用户注册");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.drawer_bar_back:
                getActivity().finish();
                break;
        }
    }
}
