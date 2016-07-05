package com.uplooking.dell.gamenews;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.uplooking.dell.gamenews.handgame.HandGameActionBar;
import com.uplooking.dell.gamenews.handgame.ViewPagerHandGame;
import com.uplooking.dell.gamenews.news.NewsActionBar;
import com.uplooking.dell.gamenews.news.ViewPagerNews;
import com.uplooking.dell.gamenews.strategy.StrategyActionBar;
import com.uplooking.dell.gamenews.strategy.ViewPagerStrategy;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;//侧滑栏整个布局
    private FrameLayout mFrameDrawer;//侧滑栏的framelayout
    private ArrayList<Fragment> mArrayListBarFrag,mArrayListContentFrag;
    private MyData data;
    private long exitTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addMainView();
        findView();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * 把侧边栏的view封装到数据类以便通过按键点击弹出侧边栏
     */
    private void findView() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout_main);

        mFrameDrawer = (FrameLayout) findViewById(R.id.left_drawer_main);

        data.setDrawerLayout(mDrawerLayout);
        data.setFrameDrawer(mFrameDrawer);
    }

    /**
     * 将四个布局（toolbar，主内容，底部导航栏，侧边栏）添加到主界面，
     * 并把actionbar和viewpager的fragment对象放入容器并封装到MyData类
     */
    private void addMainView() {
        Fragment fragmentBar1 = new NewsActionBar();
        Fragment fragmentBar2 = new StrategyActionBar();
        Fragment fragmentBar3 = new HandGameActionBar();
        Fragment fragmentContent1 = new ViewPagerNews();
        Fragment fragmentContent2 = new ViewPagerStrategy();
        Fragment fragmentContent3 = new ViewPagerHandGame();

        FragmentManager fm = getFragmentManager();
        fm.beginTransaction()
                .add(R.id.frameLayout_action_bar_main, fragmentBar1)
                .add(R.id.frameLayout_action_bar_main, fragmentBar2)
                .add(R.id.frameLayout_action_bar_main, fragmentBar3)
                .add(R.id.frameLayout_content_main, fragmentContent1)
                .add(R.id.frameLayout_content_main,fragmentContent2)
                .add(R.id.frameLayout_content_main,fragmentContent3)
                .add(R.id.frameLayout_bottom_menu_main, new MainBottomMenu())
                .add(R.id.left_drawer_main,new MainLeftDrawerLayout())
                .commit();

        mArrayListBarFrag = new ArrayList<>();
        mArrayListContentFrag = new ArrayList<>();

        mArrayListBarFrag.add(fragmentBar1);
        mArrayListBarFrag.add(fragmentBar2);
        mArrayListBarFrag.add(fragmentBar3);
        mArrayListContentFrag.add(fragmentContent1);
        mArrayListContentFrag.add(fragmentContent2);
        mArrayListContentFrag.add(fragmentContent3);

        data = MyData.getDataInstance();
        data.setArrayListBarFrag(mArrayListBarFrag);
        data.setArrayListContentFrag(mArrayListContentFrag);
    }

    /**
     * 两次点击退出程序
     * @param keyCode
     * @param event
     * @return
     */
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - exitTime > 2000) {
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                this.finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
