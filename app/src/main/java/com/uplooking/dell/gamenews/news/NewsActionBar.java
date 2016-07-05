package com.uplooking.dell.gamenews.news;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.uplooking.dell.gamenews.MyData;
import com.uplooking.dell.gamenews.MyFunction;
import com.uplooking.dell.gamenews.R;

import java.util.ArrayList;

/**
 * 新闻模块actionbar，可通过点击左侧按键弹出侧边栏
 * 中间为一个横向的scrollview，通过点击显示viewpager相应的页面
 */
public class NewsActionBar extends Fragment implements View.OnClickListener {
    private View view;
    private ArrayList<TextView> mArrayListTextView;
    private ViewPager mViewPager;
    private MyFunction mFun;
    private ImageButton mImageButtonLeft,mImageButtonRight;
    private MyData data;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.main_news_action_bar,null);
        findView();
        return view;
    }

    /**
     * 获取新闻actionbar所有可点控件的view，并设置监听器
     */
    private void findView() {
        mArrayListTextView = new ArrayList<>();
        TextView[] textViews = new TextView[]{(TextView) view.findViewById(R.id.tv1),
                (TextView) view.findViewById(R.id.tv2), (TextView) view.findViewById(R.id.tv3),
                (TextView) view.findViewById(R.id.tv4), (TextView) view.findViewById(R.id.tv5),
                (TextView) view.findViewById(R.id.tv6), (TextView) view.findViewById(R.id.tv7),
                (TextView) view.findViewById(R.id.tv8), (TextView) view.findViewById(R.id.tv9),
                (TextView) view.findViewById(R.id.tv10)};

        for(int i = 0;i<textViews.length;i++){
            mArrayListTextView.add(textViews[i]);
            textViews[i].setOnClickListener(this);
        }

        mImageButtonLeft = (ImageButton) view.findViewById(R.id.action_bar_ib_to_drawer);
        mImageButtonRight = (ImageButton) view.findViewById(R.id.action_bar_ib_to_more);
        mImageButtonLeft.setOnClickListener(this);
        mImageButtonRight.setOnClickListener(this);
        data = MyData.getDataInstance();
        data.setArrayList(mArrayListTextView);
    }

    /**
     * 返回一个装有标题的textview的容器
     * @return
     */
    public ArrayList<TextView> getArrayListTextView() {
        return mArrayListTextView;
    }

    /**
     * actionbar中各个按键的监听，根据点击的位子，设置对应viewpager的当前item或打开相应界面
     * @param v
     */
    @Override
    public void onClick(View v) {
        mViewPager = ((ViewPagerNews)data.getArrayListContentFrag().get(0)).getViewPager();
        mFun = new MyFunction();
        switch (v.getId()){
            case R.id.tv1:
                mViewPager.setCurrentItem(0,false);
                mFun.setActionBarClick(mArrayListTextView, 0);
                break;
            case R.id.tv2:
                mViewPager.setCurrentItem(1,false);
                mFun.setActionBarClick(mArrayListTextView,1);
                break;
            case R.id.tv3:
                mViewPager.setCurrentItem(2,false);
                mFun.setActionBarClick(mArrayListTextView,2);
                break;
            case R.id.tv4:
                mViewPager.setCurrentItem(3,false);
                mFun.setActionBarClick(mArrayListTextView,3);
                break;
            case R.id.tv5:
                mViewPager.setCurrentItem(4,false);
                mFun.setActionBarClick(mArrayListTextView,4);
                break;
            case R.id.tv6:
                mViewPager.setCurrentItem(5,false);
                mFun.setActionBarClick(mArrayListTextView,5);
                break;
            case R.id.tv7:
                mViewPager.setCurrentItem(6,false);
                mFun.setActionBarClick(mArrayListTextView,6);
                break;
            case R.id.tv8:
                mViewPager.setCurrentItem(7,false);
                mFun.setActionBarClick(mArrayListTextView,7);
                break;
            case R.id.tv9:
                mViewPager.setCurrentItem(8,false);
                mFun.setActionBarClick(mArrayListTextView,8);
                break;
            case R.id.tv10:
                mViewPager.setCurrentItem(9,false);
                mFun.setActionBarClick(mArrayListTextView,9);
                break;
            case R.id.action_bar_ib_to_drawer:
                data.getDrawerLayout().openDrawer(data.getFrameDrawer());
                break;
            case R.id.action_bar_ib_to_more:
                Toast.makeText(getActivity(),"功能完善中...",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
