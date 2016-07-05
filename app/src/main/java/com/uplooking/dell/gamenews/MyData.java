package com.uplooking.dell.gamenews;

import android.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 数据类，封装各种数据，用单例模式实现
 */
public class MyData {
    private static MyData dataInstance = new MyData();

    public static String LOGIN_URL = "http://appapi2.gamersky.com/v2/login";
    public static String USER_NAME_KEY = "user";

    public static String PASSWORD_KEY = "password";
    private ArrayList<TextView> arrayListTextViewNews,
            arrayListTextViewStrategy, arrayListTextViewHandGame;
    private ArrayList<Fragment> arrayListView,arrayListBarFrag,arrayListContentFrag;
    private ArrayList<LinearLayout> arrayListLinear,arrayListBarLinear,arrayListContentLinear;
    private ArrayList<FrameLayout> arrayListFrame;
    private ArrayList<View> arrayListBarViews,arrayListContentViews;
    private ViewPager viewPagerNews, viewPagerGonglue, viewPagerShouyou;
    private DrawerLayout drawerLayout;
    private FrameLayout frameDrawer, frameLayoutNews,
            frameLayoutStrategy, frameLayoutHandGame;

    private LinearLayout linearLayoutBottomMenu;

    private boolean loginState = false;

    public static String EXTRA_KEY = "str1";
    public static String NEWS_ALL_CHANNEL_URL = "http://appapi2.gamersky.com/v2/AllChannel";
    public static String NEWS_ALL_CHANNEL_REQUEST_JOSN = "{\n" +
            "    \"os\": \"android\",\n" +
            "    \"osVersion\": \"5.0\",\n" +
            "    \"deviceType\": \"Google Nexus 4 - 5.0.0 - API 21 - 768x1280\",\n" +
            "    \"appVersion\": \"2.0.4\",\n" +
            "    \"deviceId\": \"000000000000000\",\n" +
            "    \"request\": {\n" +
            "        \"type\": \"0\"\n" +
            "    }\n" +
            "}";

    private MyData() {

    }

    public static MyData getDataInstance() {
        return dataInstance;
    }

    public ArrayList<LinearLayout> getArrayListLinear() {
        return arrayListLinear;
    }

    public void setArrayListLinear(ArrayList<LinearLayout> mArrayListLinear) {
        this.arrayListLinear = mArrayListLinear;
    }

    public ArrayList<LinearLayout> getArrayListBarLinear() {
        return arrayListBarLinear;
    }

    public void setArrayListBarLinear(ArrayList<LinearLayout> arrayListBarLinear) {
        this.arrayListBarLinear = arrayListBarLinear;
    }

    public ArrayList<LinearLayout> getArrayListContentLinear() {
        return arrayListContentLinear;
    }

    public void setArrayListContentLinear(ArrayList<LinearLayout> arrayListContentLinear) {
        this.arrayListContentLinear = arrayListContentLinear;
    }

    public ArrayList<View> getArrayListBarViews() {
        return arrayListBarViews;
    }

    public void setArrayListBarViews(ArrayList<View> arrayListBarViews) {
        this.arrayListBarViews = arrayListBarViews;
    }

    public ArrayList<View> getArrayListContentViews() {
        return arrayListContentViews;
    }

    public void setArrayListContentViews(ArrayList<View> arrayListContentViews) {
        this.arrayListContentViews = arrayListContentViews;
    }

    public ArrayList<Fragment> getArrayListBarFrag() {
        return arrayListBarFrag;
    }

    public void setArrayListBarFrag(ArrayList<Fragment> arrayListBarFrag) {
        this.arrayListBarFrag = arrayListBarFrag;
    }

    public ArrayList<Fragment> getArrayListContentFrag() {
        return arrayListContentFrag;
    }

    public void setArrayListContentFrag(ArrayList<Fragment> arrayListContentFrag) {
        this.arrayListContentFrag = arrayListContentFrag;
    }

    public LinearLayout getLinearLayoutBottomMenu() {
        return linearLayoutBottomMenu;
    }

    public void setLinearLayoutBottomMenu(LinearLayout mLinearLayoutBottomMenu) {
        this.linearLayoutBottomMenu = mLinearLayoutBottomMenu;
    }

    public ArrayList<FrameLayout> getArrayListFrame() {
        return arrayListFrame;
    }

    public void setArrayListFrame(ArrayList<FrameLayout> mArrayListFrame) {
        this.arrayListFrame = mArrayListFrame;
    }

    public ViewPager getViewPagerGonglue() {
        return viewPagerGonglue;
    }

    public void setViewPagerGonglue(ViewPager viewPagerGonglue) {
        this.viewPagerGonglue = viewPagerGonglue;
    }

    public ViewPager getViewPagerShouyou() {
        return viewPagerShouyou;
    }

    public void setViewPagerShouyou(ViewPager viewPagerShouyou) {
        this.viewPagerShouyou = viewPagerShouyou;
    }

    public FrameLayout getmFrameLayoutNews() {
        return frameLayoutNews;
    }

    public void setmFrameLayoutNews(FrameLayout mFrameLayoutNews) {
        this.frameLayoutNews = mFrameLayoutNews;
    }

    public FrameLayout getmFrameLayoutStrategy() {
        return frameLayoutStrategy;
    }

    public void setmFrameLayoutStrategy(FrameLayout mFrameLayoutStrategy) {
        this.frameLayoutStrategy = mFrameLayoutStrategy;
    }

    public FrameLayout getmFrameLayoutHandGame() {
        return frameLayoutHandGame;
    }

    public void setmFrameLayoutHandGame(FrameLayout mFrameLayoutHandGame) {
        this.frameLayoutHandGame = mFrameLayoutHandGame;
    }

    public boolean isLoginState() {
        return loginState;
    }

    public void setLoginState(boolean loginState) {
        this.loginState = loginState;
    }

    public DrawerLayout getDrawerLayout() {
        return drawerLayout;
    }

    public void setDrawerLayout(DrawerLayout mDrawerLayout) {
        this.drawerLayout = mDrawerLayout;
    }

    public FrameLayout getFrameDrawer() {
        return frameDrawer;
    }

    public void setFrameDrawer(FrameLayout mFrameDrawer) {
        this.frameDrawer = mFrameDrawer;
    }

    public ViewPager getViewPagerNews() {
        return viewPagerNews;
    }

    public void setViewPagerNews(ViewPager mViewPager) {
        this.viewPagerNews = mViewPager;
    }

    public ArrayList<Fragment> getArrayListView() {
        return arrayListView;
    }

    public void setArrayListView(ArrayList<Fragment> arrayListView) {
        this.arrayListView = arrayListView;
    }

    public ArrayList<TextView> getArrayListTextViewStrategy() {
        return arrayListTextViewStrategy;
    }

    public void setArrayListTextViewStrategy(ArrayList<TextView> mArrayListTextViewStrategy) {
        this.arrayListTextViewStrategy = mArrayListTextViewStrategy;
    }

    public ArrayList<TextView> getArrayListTextViewHandGame() {
        return arrayListTextViewHandGame;
    }

    public void setArrayListTextViewHandGame(ArrayList<TextView> mArrayListTextViewHandGame) {
        this.arrayListTextViewHandGame = mArrayListTextViewHandGame;
    }

    public void setArrayList(ArrayList<TextView> arrayList) {
        this.arrayListTextViewNews = arrayList;
    }

    public ArrayList<TextView> getArrayList() {
        return arrayListTextViewNews;
    }
}
