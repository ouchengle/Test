package com.uplooking.dell.gamenews;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Handler;
import android.os.Message;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 2015/12/21.
 */
public class MyFunction{
    private MyData data;
    private ArrayList<Fragment> mArrayListView;
    private ArrayList<TextView> mTextViewArrayList;
    private ArrayList<LinearLayout> mArrayListLinear,mArrayListBarLinear,mArrayListContentLinear;
    private ArrayList<FrameLayout> mArrayListFrame;
    private ArrayList<View> mArrayListBarViews,mArrayListContentViews;
    private ViewPager mViewPager,mViewPagerNews,mViewPagerGonglue,mViewPagerShouyou;
    private Button mButton;
    private FrameLayout mFrameLayoutNews,mFrameLayoutStrategy,mFrameLayoutHandGame;

    private boolean IS_PASSWORD_VISIBLE = false;

    /**
     * 获取验证码后的倒计时功能
     */
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //开始倒计时后设置按键为不可点击
            mButton.setClickable(false);
            /**
             * 当时间变为0时，改变按键内容，并设置为可点击
             * 或则就显示当前倒计时的时间
             */
            if (msg.arg1 == 0) {
                mButton.setText("重新获取");
                mButton.setClickable(true);
            } else {
                mButton.setText(msg.arg1 + "秒");
            }
        }
    };

    /**
     * 构造方法
     * 用于获取到三大板块的viewpager的每个fragment
     */
    public MyFunction(){
        data = MyData.getDataInstance();
        mArrayListView = data.getArrayListView();
        mViewPagerNews = data.getViewPagerNews();
        mViewPagerGonglue = data.getViewPagerGonglue();
        mViewPagerShouyou = data.getViewPagerShouyou();
        mTextViewArrayList = data.getArrayList();
    }

    //根据点击位子设置底部导航栏样式，并显示相应的frame layout
    public void setMainContentVisual(int position){
        mArrayListLinear = data.getArrayListLinear();
        mArrayListBarLinear = data.getArrayListBarLinear();
        mArrayListContentLinear = data.getArrayListContentLinear();

        for(int i = 0;i<mArrayListLinear.size();i++){
            mArrayListLinear.get(i).setVisibility(View.GONE);
            mArrayListBarLinear.get(i).setVisibility(View.GONE);
            mArrayListContentLinear.get(i).setVisibility(View.GONE);
            Log.i("test", "设置:" + mArrayListBarLinear.get(i));
        }
        mArrayListLinear.get(position).setVisibility(View.VISIBLE);
        mArrayListBarLinear.get(position).setVisibility(View.VISIBLE);
        mArrayListContentLinear.get(position).setVisibility(View.VISIBLE);
    }

    /**
     * 设置密码可见与不可见
     * 如果当前密码为可见，则设置输入框输入类型为密码类型，并改变图标
     * 如果当前密码为不可见，则设置输入框输入类型为可见，并改变图标
     * @param editTextPassword 密码栏输入框对象
     * @param imageView 密码显示与否的可点击控件对象
     */
    public void setPasswordVisual(EditText editTextPassword,ImageView imageView){
        if(IS_PASSWORD_VISIBLE){
            editTextPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            imageView.setImageResource(R.drawable.icon_hide_pw);
            IS_PASSWORD_VISIBLE = false;
        }else {
            editTextPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            imageView.setImageResource(R.drawable.icon_show_pw);
            IS_PASSWORD_VISIBLE = true;
        }
    }

    /**
     * 主界面actionbar被点击后设置相应的view显示，并改变相应的textView的样式
     * @param textViewArrayList 主题栏的textview的容器
     * @param position 点击的位子
     */
    public void setActionBarClick(ArrayList<TextView> textViewArrayList,int position){
        initTitle(textViewArrayList);
        setTopMenuStyle(textViewArrayList,position,1,20);
    }

    //打开主界面后，设置默认样式：显示第一个view，改变第一个textView样式
    public void setDefaultViewPager(ArrayList<TextView> textViewArrayList) {
        mViewPagerNews.setCurrentItem(0);
        setTopMenuStyle(textViewArrayList,0, 1, 20);
    }

    /**
     * 设置主界面的actionbar中指定textView的样式
     * @param textViewArrayList
     * @param position
     * @param alpha
     * @param size
     */
    public void setTopMenuStyle(ArrayList<TextView> textViewArrayList,int position, float alpha,float size){
        TextView tv = textViewArrayList.get(position);
        tv.setAlpha(alpha);
        tv.setTextSize(size);
        tv.getParent().requestChildFocus(tv, tv);
    }

    //初始化主界面的actionbar中所有的textView为统一的样式
    public void initTitle(ArrayList<TextView> textViewArrayList) {
        for (int i = 0; i < textViewArrayList.size(); i++) {
            TextView tv = textViewArrayList.get(i);
            tv.setTextSize(15);
            tv.setAlpha(0.75f);
        }
    }

    //点击后去验证码后进行倒计时
    public void countDownTimer(int second,Button button){
        this.mButton = button;
        new Thread(new MyCountDownTimer(second)).start();
    }

    class MyCountDownTimer implements Runnable {
        private int second;

        public MyCountDownTimer(int second){
            this.second = second;
        }

        @Override
        public void run() {
            for (int i = second; i >= 0; i--) {
                try {
                    Thread.sleep(1000);
                    Message msg = Message.obtain();
                    msg.arg1 = i;
                    mHandler.sendMessage(msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
