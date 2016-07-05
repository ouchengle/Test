package com.uplooking.dell.gamenews.strategy;

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
 * Created by dell on 2016/1/11.
 */
public class StrategyActionBar extends Fragment implements View.OnClickListener{
    private View view;
    private ArrayList<TextView> mArrayListTextView;
    private ViewPager mViewPager;
    private MyFunction mFun;
    private ImageButton mImageButtonLeft,mImageButtonRight;
    private MyData data;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.main_strategy_action_bar,null);
        findView();
        return view;
    }

    //获取攻略actionbar所有可点控件的view，并设置监听器
    private void findView() {
        mArrayListTextView = new ArrayList<>();
        TextView[] textViews = new TextView[]{(TextView) view.findViewById(R.id.strategy_tv1),
                (TextView) view.findViewById(R.id.strategy_tv2)};

        for(int i = 0;i<textViews.length;i++){
            mArrayListTextView.add(textViews[i]);
            textViews[i].setOnClickListener(this);
        }

        mImageButtonLeft = (ImageButton) view.findViewById(R.id.strategy_action_bar_ib_to_drawer);
        mImageButtonRight = (ImageButton) view.findViewById(R.id.strategy_action_bar_ib_search);
        mImageButtonLeft.setOnClickListener(this);
        mImageButtonRight.setOnClickListener(this);
        data = MyData.getDataInstance();
        data.setArrayListTextViewStrategy(mArrayListTextView);
    }

    public ArrayList<TextView> getArrayListTextView() {
        return mArrayListTextView;
    }

    //actionbar监听，根据点击的位子，设置对应viewpager的当前item或打开相应界面
    @Override
    public void onClick(View v) {
        mViewPager = ((ViewPagerStrategy)data.getArrayListContentFrag().get(1)).getViewPager();
        mFun = new MyFunction();
        switch (v.getId()){
            case R.id.strategy_tv1:
                mViewPager.setCurrentItem(0,false);
                mFun.setActionBarClick(mArrayListTextView,0);
                break;
            case R.id.strategy_tv2:
                mViewPager.setCurrentItem(1,false);
                mFun.setActionBarClick(mArrayListTextView,1);
                break;
            case R.id.strategy_action_bar_ib_to_drawer:
                data.getDrawerLayout().openDrawer(data.getFrameDrawer());
                break;
            case R.id.strategy_action_bar_ib_search:
                Toast.makeText(getActivity(), "功能完善中...", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
