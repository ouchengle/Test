package com.uplooking.dell.gamenews.strategy;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.uplooking.dell.gamenews.MyData;
import com.uplooking.dell.gamenews.MyFunction;
import com.uplooking.dell.gamenews.R;

import java.util.ArrayList;

/**
 * Created by dell on 2016/1/11.
 */
public class ViewPagerStrategy extends Fragment {
    private MyData data;
    private MyFunction mFun;
    private View view;
    private ArrayList<Fragment> mArrayListView;
    private ArrayList<TextView> arrayList;
    private ViewPager mViewPager;
    private FragAdapter mAdapter;
    private static String[] pageCounts = new String[]{"20","1000"};
    private static String[] types = new String[]{"0","1"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.view_pager_strategy, null);
        setViewPager();
        setViewPagerListener();
        Log.i("test", "strategy viewpager");
        return view;
    }

    //给viewpager添加布局，用FragmentStatePagerAdapter设置适配器
    private void setViewPager() {
        data = MyData.getDataInstance();
        arrayList = ((StrategyActionBar)data.getArrayListBarFrag().get(1)).getArrayListTextView();
        mViewPager = (ViewPager) view.findViewById(R.id.viewpager_strategy);
        //mViewPager.setPageTransformer(true, new ZoomOutPageTransformer());
        mArrayListView = new ArrayList<>();

        /*for (int i = 0; i < 2; i++) {
            StrategyFollowContentFragment fragment = new StrategyFollowContentFragment();
            fragment.setName(arrayList.get(i).getText().toString());
            fragment.setPageCount(pageCounts[i]);
            fragment.setType(types[i]);
            mArrayListView.add(fragment);
        }
*/
        mArrayListView.add(new StrategyFollowContentFragment());
        mArrayListView.add(new StrategyAllContentFragment());

        mAdapter = new FragAdapter(getFragmentManager());
        mViewPager.setAdapter(mAdapter);
        mViewPager.setCurrentItem(0);
    }

    private class FragAdapter extends FragmentStatePagerAdapter {

        public FragAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mArrayListView.get(position);
        }

        @Override
        public int getCount() {
            return mArrayListView.size();
        }
    }

    //viewpager监听器，实现顶部菜单文字大小渐变功能
    private void setViewPagerListener(){
        mFun = new MyFunction();
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (positionOffset != 0) {
                    mFun.setTopMenuStyle(arrayList,position, 1 - positionOffset * 0.25f, 20 - positionOffset * 5);
                    mFun.setTopMenuStyle(arrayList,position + 1, 0.75f + positionOffset * 0.25f, 15 + positionOffset * 5);
                }
            }

            @Override
            public void onPageSelected(int position) {
                mFun.initTitle(arrayList);
                mFun.setTopMenuStyle(arrayList,position, 1, 20);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == 0) {
                    mFun.initTitle(arrayList);
                    mFun.setTopMenuStyle(arrayList,mViewPager.getCurrentItem(), 1, 20);
                }
            }
        });
    }

    public ViewPager getViewPager() {
        return mViewPager;
    }
}
