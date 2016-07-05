package com.uplooking.dell.gamenews;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by dell on 2016/1/25.
 */
public class RefreshLoadListView extends ListView implements AbsListView.OnScrollListener {
    private View header;//头部view布局
    private View footer;//脚部view布局

    private int headerHeight;//头部高度
    private int firstVisibleItem;//当前第一个可见的item的位子
    private int startY;//按下时的Y值

    private boolean isRemarke;//标记，当前是在listview最顶端按下的
    private boolean isScrollToBottom;//是否滑到底部
    private boolean isLoading;//是否在加载

    private int scrollState;//listview当前滚动状态
    private int state;//当前的状态
    private static final int NONE = 0;//正常状态
    private static final int PULL = 1;//提示下拉状态
    private static final int RELEASE = 2;//提示释放状态
    private static final int REFRESHING = 3;//正在刷新状态

    private IRefreshListener iRefreshListener;

    public RefreshLoadListView(Context context) {
        super(context);
        initView(context);
    }

    public RefreshLoadListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public RefreshLoadListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    /**
     * 初始化布局，添加头和脚
     * @param context
     */
    private void initView(Context context) {
        header = LayoutInflater.from(context).inflate(R.layout.item_refresh_header, null);
        header.measure(0, 0);
        //measureView(header);
        headerHeight = header.getMeasuredHeight();
        setHeaderPaddingTop(-headerHeight);
        this.addHeaderView(header);
        this.setOnScrollListener(this);

        footer = LayoutInflater.from(context).inflate(R.layout.item_load_footer, null);
        this.addFooterView(footer);
        footer.findViewById(R.id.layout_footer).setVisibility(View.GONE);
        this.setOnScrollListener(this);

    }

    /**
     * 设置头部高度
     *
     * @param paddingTop
     */
    private void setHeaderPaddingTop(int paddingTop) {
        header.setPadding(header.getPaddingLeft(),paddingTop,
                header.getPaddingRight(),header.getPaddingBottom());
        header.invalidate();
    }

    /**
     * 当滚动状态改变时回调
     *
     * @param view
     * @param scrollState
     */
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        this.scrollState = scrollState;
        if (scrollState == SCROLL_STATE_FLING || scrollState == SCROLL_STATE_IDLE) {
            if (isScrollToBottom && !isLoading) {
                isLoading = true;
                footer.findViewById(R.id.layout_footer).setVisibility(View.VISIBLE);
                iRefreshListener.onLoad();
            }
        }
    }

    /**
     * 当滚动式调用
     *
     * @param view
     * @param firstVisibleItem
     * @param visibleItemCount
     * @param totalItemCount
     */
    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        this.firstVisibleItem = firstVisibleItem;
        if (getLastVisiblePosition() == (totalItemCount - 1)) {
            isScrollToBottom = true;
        } else {
            isScrollToBottom = false;
        }
    }

    /**
     * 触屏动作监听
     *
     * @param ev
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            //按下时，记下按下时的Y值
            case MotionEvent.ACTION_DOWN:
                if (firstVisibleItem == 0) {
                    isRemarke = true;
                }
                startY = (int) ev.getY();
                break;
            //移动式，调用移动过程中方法
            case MotionEvent.ACTION_MOVE:
                onMove(ev);
                break;
            case MotionEvent.ACTION_UP:
                Log.i("refresh","当前状态:" + state);
                if (state == RELEASE) {
                    state = REFRESHING;
                    //加载最新数据
                    refreshViewByState();
                    iRefreshListener.onRefresh();
                } else if (state == PULL) {
                    state = NONE;
                    isRemarke = false;
                    refreshViewByState();
                }
                break;
        }
        return super.onTouchEvent(ev);
    }

    /**
     * 判断移动过程中的操作
     *
     * @param ev
     */
    private void onMove(MotionEvent ev) {
        if (!isRemarke) {
            return;
        }
        int tempY = (int) ev.getY();
        int space = tempY - startY;
        int topPadding = (space - headerHeight) ;
        if (firstVisibleItem == 0 && -headerHeight < topPadding) {
            switch (state) {
                case NONE:
                    if (space > 0) {
                        Log.i("refresh","开始移动");
                        state = PULL;
                        refreshViewByState();
                    }
                    break;
                case PULL:
                    setHeaderPaddingTop(topPadding);
                    Log.i("refresh","移动中...");
                    if (header.getPaddingTop() >= 30 && scrollState == SCROLL_STATE_TOUCH_SCROLL) {
                        state = RELEASE;
                        refreshViewByState();
                    }
                    break;
                case RELEASE:
                    setHeaderPaddingTop(topPadding);
                    if (header.getPaddingTop() < 30) {
                        state = PULL;
                        refreshViewByState();
                    } else if (header.getPaddingTop() <= 0) {
                        state = NONE;
                        isRemarke = false;
                        refreshViewByState();
                    }
                    break;
            }
        }
    }

    /**
     * 根据当前状态改变相应的布局样式
     */
    private void refreshViewByState() {
        TextView tip = (TextView) header.findViewById(R.id.tv_refresh_tip);
        ImageView arrow = (ImageView) header.findViewById(R.id.iv_refresh_arrow);
        ProgressBar progress = (ProgressBar) header.findViewById(R.id.refreshing_progress);

        RotateAnimation animationToUp = new RotateAnimation(0, 180,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        animationToUp.setDuration(500);
        // 动画结束后, 停留在结束的位置上
        animationToUp.setFillAfter(true);

        RotateAnimation animationToDown = new RotateAnimation(180, 0,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        animationToDown.setDuration(500);
        animationToDown.setFillAfter(true);

        switch (state) {
            case NONE:
                arrow.clearAnimation();
                arrow.setVisibility(View.VISIBLE);
                progress.setVisibility(View.GONE);
                arrow.setAnimation(animationToDown);
                setHeaderPaddingTop(-headerHeight);
                break;
            case PULL:
                arrow.setVisibility(View.VISIBLE);
                progress.setVisibility(View.GONE);
                tip.setText("下拉可以刷新");
                arrow.clearAnimation();
                arrow.setAnimation(animationToDown);
                break;
            case RELEASE:
                arrow.setVisibility(View.VISIBLE);
                progress.setVisibility(View.GONE);
                tip.setText("松开可以刷新");
                arrow.clearAnimation();
                arrow.setAnimation(animationToUp);
                break;
            case REFRESHING:
                setHeaderPaddingTop(30);
                arrow.setVisibility(View.GONE);
                progress.setVisibility(View.VISIBLE);
                tip.setText("正在刷新...");
                arrow.clearAnimation();
                break;
        }
    }

    /**
     * 获取完数据，刷新结束
     */
    public void refreshComplete() {
        state = NONE;
        isRemarke = false;
        refreshViewByState();
        TextView lastRefreshTime = (TextView) header.findViewById(R.id.tv_lastrefresh_time);
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日   HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        lastRefreshTime.setText(format.format(date));
    }

    /**
     * 加载完数据，加载结束
     */
    public void loadComplete() {
        footer.findViewById(R.id.layout_footer).setVisibility(View.GONE);
        isLoading = false;
    }

    public void setInterface(IRefreshListener iRefreshListener) {
        this.iRefreshListener = iRefreshListener;
    }


    /**
     * 刷新数据接口
     */
    public interface IRefreshListener {
        public void onRefresh();

        public void onLoad();
    }
}
