package com.uplooking.dell.gamenews;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 * 底部导航栏界面及相关功能代码
 */
public class MainBottomMenu extends Fragment implements View.OnClickListener{
    private View view;
    private MyData data;
    private ArrayList<LinearLayout> arrayList;//导航栏红色布局的容器
    private ArrayList<Fragment> mArrayListBarFrag,mArrayListContentFrag;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.main_bottom_menu,null);
        findView();
        setListener();
        defaultShowFrag();
        return view;
    }

    /**
     * 获取相关控件的view，并封装到数据类
     */
    private void findView() {
        data = MyData.getDataInstance();
        arrayList = new ArrayList<>();
        arrayList.add((LinearLayout)view.findViewById(R.id.linearLayout_bottom_menu_news_selected));
        arrayList.add((LinearLayout)view.findViewById(R.id.linearLayout_bottom_menu_strategy_selected));
        arrayList.add((LinearLayout) view.findViewById(R.id.linearLayout_bottom_menu_hand_game_selected));

        data.setArrayListLinear(arrayList);
    }

    /**
     * 设置每个栏目的监听器
     */
    private void setListener() {
        (view.findViewById(R.id.linearLayout_bottom_menu_news)).setOnClickListener(this);
        (view.findViewById(R.id.linearLayout_bottom_menu_strategy)).setOnClickListener(this);
        (view.findViewById(R.id.linearLayout_bottom_menu_hand_game)).setOnClickListener(this);
    }

    /**
     * 设置打开主界面时默认显示的界面，即隐藏“功率”和“手游”的fragment
     */
    private void defaultShowFrag() {
        mArrayListBarFrag = data.getArrayListBarFrag();
        mArrayListContentFrag = data.getArrayListContentFrag();

        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.hide(mArrayListBarFrag.get(1));
        ft.hide(mArrayListContentFrag.get(1));
        ft.hide(mArrayListBarFrag.get(2));
        ft.hide(mArrayListContentFrag.get(2));
        ft.commit();
    }


    /**
     * 根据点击的栏目执行相应的功能
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.linearLayout_bottom_menu_news:
                setMainContentVisual(0);
                break;
            case R.id.linearLayout_bottom_menu_strategy:
                setMainContentVisual(1);
                break;
            case R.id.linearLayout_bottom_menu_hand_game:
                setMainContentVisual(2);
                break;
        }
    }

    /**
     * 根据点击的位置，从数据类获取相应的actionbar和主内容的fragment
     * 使用fragment的事务机制，实现相应fragment的显示和隐藏
     * 并对点击后的栏目显示为红色布局
     * @param position 点击的位子，从0开始计
     */
    private void setMainContentVisual(int position){
        /**
         * 用for循环让所有actionbar和主内容及红色布局隐藏
         */
        for(int i = 0;i<arrayList.size();i++){
            arrayList.get(i).setVisibility(View.GONE);
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.hide(mArrayListBarFrag.get(i));
            ft.hide(mArrayListContentFrag.get(i));
            ft.commit();
        }

        /**
         * 根据position让相应的actionbar、主内容及导航栏目红色布局为显示
         */
        arrayList.get(position).setVisibility(View.VISIBLE);
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.show(mArrayListBarFrag.get(position));
        ft.show(mArrayListContentFrag.get(position));
        ft.commit();
    }
}
