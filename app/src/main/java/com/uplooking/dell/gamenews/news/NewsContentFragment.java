package com.uplooking.dell.gamenews.news;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
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
import com.uplooking.dell.gamenews.R;
import com.uplooking.dell.gamenews.RefreshLoadListView;
import com.uplooking.dell.gamenews.WebViewActivity;
import com.uplooking.dell.myjsondata.newsresult.NewsResultRootClass;
import com.uplooking.dell.myjsondata.newsrequestlist.NewsRequest;
import com.uplooking.dell.myjsondata.newsrequestlist.NewsRequestRootClass;

import org.apache.http.entity.StringEntity;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Created by dell on 2016/1/7.
 */
public class NewsContentFragment extends Fragment implements RefreshLoadListView.IRefreshListener {
    public static String KEY_CONTENT_ID = "key of contentID";
    private static String NEWS_URL = "http://appapi2.gamersky.com/v2/AllChannelList";

    private RelativeLayout mRelativeLayout;
    private RefreshLoadListView mListView;
    private View mViewHuandeng, mViewHuandengPicOne, mViewHuandengPicTwo,
            mViewHuandengPicThree, mViewHuandengPicFour;
    private ViewPager mViewPagerHuandeng;
    private TextView mTVHuandengTitle;
    private ImageView mIVHuandengPointOne, mIVHuandengPointTwo,
            mIVHuandengPointThree, mIVHuandengPointFour;

    private int mPageIndex;
    private String mStringName;
    private String mStringNoteId;
    private String mStringPageIndex;
    private String mStrHuangdengTitles;
    private String mTempURL;
    private ArrayList<View> mArrayHuandengPic;
    private ArrayList<ImageView> mArrayHuandengPoint;

    private NewsListViewAdapter adapter;
    private NewsResultRootClass mResult;
    private Gson gson;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.news_content, null);
        initData();
        findView();
        sendRequestData();
        return view;
    }

    /**
     * 初始化一些数据，由于刷新后重新设置数据
     */
    private void initData(){
        mResult =null;
        adapter = null;
        mPageIndex = 1;
        mStringPageIndex = Integer.toString(mPageIndex);
    }

    private void findView() {
        mRelativeLayout = (RelativeLayout) view.findViewById(R.id.news_load_rl);
        mListView = (RefreshLoadListView) view.findViewById(R.id.list_view_news);
        mViewHuandeng = LayoutInflater.from(getActivity()).inflate(R.layout.item_huandeng, null);
        mViewPagerHuandeng = (ViewPager) mViewHuandeng.findViewById(R.id.viewPager_huandeng);
        mTVHuandengTitle = (TextView) mViewHuandeng.findViewById(R.id.tv_huandeng_title);
        mIVHuandengPointOne = (ImageView) mViewHuandeng.findViewById(R.id.iv_huandeng_1st_point_selected);
        mIVHuandengPointTwo = (ImageView) mViewHuandeng.findViewById(R.id.iv_huandeng_2nd_point_selected);
        mIVHuandengPointThree = (ImageView) mViewHuandeng.findViewById(R.id.iv_huandeng_3rd_point_selected);
        mIVHuandengPointFour = (ImageView) mViewHuandeng.findViewById(R.id.iv_huandeng_4th_point_selected);

        mArrayHuandengPoint = new ArrayList<>();
        mArrayHuandengPoint.add(mIVHuandengPointOne);
        mArrayHuandengPoint.add(mIVHuandengPointTwo);
        mArrayHuandengPoint.add(mIVHuandengPointThree);
        mArrayHuandengPoint.add(mIVHuandengPointFour);

        mArrayHuandengPic = new ArrayList<>();
        mViewHuandengPicOne = LayoutInflater.from(getActivity()).inflate(R.layout.item_huandeng_viewpager_pic, null);
        mViewHuandengPicTwo = LayoutInflater.from(getActivity()).inflate(R.layout.item_huandeng_viewpager_pic, null);
        mViewHuandengPicThree = LayoutInflater.from(getActivity()).inflate(R.layout.item_huandeng_viewpager_pic, null);
        mViewHuandengPicFour = LayoutInflater.from(getActivity()).inflate(R.layout.item_huandeng_viewpager_pic, null);
        mArrayHuandengPic.add(mViewHuandengPicOne);
        mArrayHuandengPic.add(mViewHuandengPicTwo);
        mArrayHuandengPic.add(mViewHuandengPicThree);
        mArrayHuandengPic.add(mViewHuandengPicFour);

    }

    //发送数据请求到服务器
    private void sendRequestData() {
        gson = new Gson();
        RequestParams params = new RequestParams();
        final NewsRequestRootClass request = new NewsRequestRootClass();
        setRequestData(request);

        try {
            params.setBodyEntity(new StringEntity(gson.toJson(request), "utf-8"));
            HttpUtils http = new HttpUtils(5000);
            http.send(HttpRequest.HttpMethod.POST,
                    NEWS_URL,
                    params,
                    new RequestCallBack<String>() {
                        @Override
                        public void onSuccess(ResponseInfo<String> responseInfo) {
                            if (mResult == null) {
                                //如果数据类为空，没有数据，则加载数据
                                mResult = gson.fromJson(responseInfo.result, NewsResultRootClass.class);
                                if (mResult.geterrorCode() == 1) {
                                    Log.i("test:", mStringName + mResult.geterrorMessage());
                                } else {
                                    //Log.i("test:", mStringName + "获取到数据:" + mResult.getresult().get(0).getchildElements().get(0).gettitle());
                                    mRelativeLayout.setVisibility(View.GONE);
                                    mListView.setVisibility(View.VISIBLE);
                                    setListViewContent();
                                    setListViewContentListener();
                                }
                                //如果数据类不为空，有数据，则在原对象后面添加数据
                            } else {
                                NewsResultRootClass tmpClass = gson.fromJson(responseInfo.result, NewsResultRootClass.class);
                                if (tmpClass.geterrorCode() == 1) {
                                    Toast.makeText(getActivity(),"加载失败",Toast.LENGTH_SHORT).show();
                                } else {
                                    for (int i = 0; i < tmpClass.getresult().size(); i++) {
                                        mResult.getresult().add(tmpClass.getresult().get(i));
                                    }
                                    setListViewContent();
                                }
                            }
                            mListView.refreshComplete();
                            mListView.loadComplete();
                        }

                        @Override
                        public void onStart() {
                            super.onStart();
                        }

                        @Override
                        public void onFailure(HttpException e, String s) {
                            if (getActivity() == null) {
                            } else {
                                Toast.makeText(getActivity(), "网络连接超时", Toast.LENGTH_SHORT).show();
                            }
                            mRelativeLayout.setVisibility(View.GONE);
                            mListView.setVisibility(View.VISIBLE);
                            mListView.refreshComplete();
                            mListView.loadComplete();
                        }
                    });
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    //设置请求数据相应参数
    private void setRequestData(NewsRequestRootClass request) {
        NewsRequest newsRequest = request.getrequest();
        newsRequest.setparentNodeId("news");
        newsRequest.setnodeIds(mStringNoteId);
        newsRequest.setpageIndex(mStringPageIndex);
        newsRequest.setelementsCountPerPage(20);
    }

    /**
     *通过图片的地址判断幻灯内容是否有更新
     * @return
     */
    private boolean isNewHeader() {
        String newURL = mResult.getresult().get(0).getchildElements().get(0).getthumbnailURLs().get(0);
        if (!newURL.equals(mTempURL)) {
            return true;
        }
        return false;
    }

    //设置幻灯中图片viewpager的布局数量为4*16，实现轮播
    private void setViewPagerHuandengPic() {
        //设置第一个幻灯的标题
        String title = mResult.getresult().get(0).getchildElements().get(0).gettitle();
        mTVHuandengTitle.setText(title);
        //保存第一个幻灯图片的地址，用以判断是否更新
        mTempURL = mResult.getresult().get(0).getchildElements().get(0).getthumbnailURLs().get(0);

        mViewPagerHuandeng.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return mArrayHuandengPic.size() * 16;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                //super.destroyItem(container, position, object);
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                position = position % mArrayHuandengPic.size();
                View view = mArrayHuandengPic.get(position);
                if (view.getParent() != null) {
                    container.removeView(view);
                }
                container.addView(view);
                setViewPagerPic(position);
                return view;
            }
        });
        //将当前幻灯页数设置中间的值，才可实现左滑
        mViewPagerHuandeng.setCurrentItem(mArrayHuandengPic.size() * 8);
    }

    /**
     * 获取幻灯的图片
     *
     * @param position
     */
    private void setViewPagerPic(int position) {
        BitmapUtils bitmapUtils = new BitmapUtils(getActivity());

        bitmapUtils.display(mArrayHuandengPic.get(position).findViewById(R.id.iv_huandeng_pic),
                mResult.getresult().get(0).getchildElements().get(position).getthumbnailURLs().get(0));
    }

    /**
     * 设置幻灯viewpager切换监听器
     */
    private void setViewPagerHuandengListener() {
        mViewPagerHuandeng.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            //通过对当前位子取摸，来设置相应图片位子的点显示
            @Override
            public void onPageSelected(int position) {
                initHuandengPoint();
                position = position % mArrayHuandengPoint.size();
                mArrayHuandengPoint.get(position).setVisibility(View.VISIBLE);
                String title = mResult.getresult().get(0).getchildElements().get(position).gettitle();
                mTVHuandengTitle.setText(title);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 设置幻灯点击监听器
     */
    private void setHuandengClickListener(){
        for(int i  = 0; i < mArrayHuandengPic.size();i++){
            mArrayHuandengPic.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(),WebViewActivity.class);
                    intent.putExtra(KEY_CONTENT_ID, Integer.toString(mResult.getresult().get(0).
                            getchildElements().get(mViewPagerHuandeng.getCurrentItem() % 4).getcontentId()));
                    startActivity(intent);
                }
            });
        }
    }

    /**
     * 初始化幻灯的指示点
     */
    private void initHuandengPoint() {
        for (int i = 0; i < mArrayHuandengPoint.size(); i++) {
            mArrayHuandengPoint.get(i).setVisibility(View.GONE);
        }
    }

    /**
     * 给主界面的listview通过MVC方式添加内容，并用addHeaderView添加幻灯
     * 由于刷新后幻灯内容可能会更新，若有更新，则去掉原来的幻灯头
     * （更好的方法是在适配器中给幻灯做布局判断，而不是以添加头的方式）
     */
    private void setListViewContent() {
        //判断是否有新的幻灯头，刷新后会有新的幻灯头，此时应去掉一个
        if (isNewHeader()) {
            mListView.removeHeaderView(mViewHuandeng);
        }
        //判断头的数量，加上下拉刷新布局和幻灯的头，应该有两个头，
        //第一次加载布局或幻灯内容因为有更新而被去掉时没有幻灯头，因此只有一个头，此时加载幻灯头的viewpager
        if (mListView.getHeaderViewsCount() == 1) {
            setViewPagerHuandengPic();
            setViewPagerHuandengListener();
            setHuandengClickListener();
            mListView.addHeaderView(mViewHuandeng,null,true);
        }

        //如果适配器为空，即第一次加载数据或刷新后，则重新设置适配器
        if (adapter == null) {
            adapter = new NewsListViewAdapter(mResult, getActivity());
            mListView.setInterface(this);
            mListView.setAdapter(adapter);
        }
        //如果不为空，即加载更多数据，则刷新适配器的新数据
        else {
            adapter.notifyDataSetChanged();
        }
    }

    //设置主内容Listview的监听
    private void setListViewContentListener() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), WebViewActivity.class);
                intent.putExtra(KEY_CONTENT_ID, Integer.toString(mResult.getresult().get(position - 2).getcontentId()));
                startActivity(intent);
            }
        });
    }

    public NewsResultRootClass getmResult() {
        return mResult;
    }

    public void setName(String name) {
        mStringName = name;
    }

    public void setNoteId(String noteId) {
        mStringNoteId = noteId;
    }

    @Override
    public void onRefresh() {
        initData();
        sendRequestData();
    }

    @Override
    public void onLoad() {
        mPageIndex = mPageIndex + 1;
        mStringPageIndex = Integer.toString(mPageIndex);
        sendRequestData();
    }
}
