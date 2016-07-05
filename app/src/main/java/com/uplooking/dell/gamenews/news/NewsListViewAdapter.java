package com.uplooking.dell.gamenews.news;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.cache.MD5FileNameGenerator;
import com.uplooking.dell.gamenews.R;
import com.uplooking.dell.myjsondata.newsrequestlist.NewsRequestRootClass;
import com.uplooking.dell.myjsondata.newsresult.NewsResultRootClass;

import java.util.ArrayList;

/**
 * Created by dell on 2016/1/15.
 */
public class NewsListViewAdapter extends BaseAdapter {
    private Context context;
    private NewsResultRootClass mResult;

    private static final int XIN_WEN = 0;
    private static final int THREE_PIC = 1;
    private static final int ZHUAN_TI = 2;
    private static final int HENG_TU = 3;
    private static final int HUAN_DENG = 4;

    public NewsListViewAdapter(NewsResultRootClass result, Context context) {
        this.mResult = result;
        this.context = context;
    }

    //加载条目数时，第一次为21-1，后面做上滑加载时应该做判断该为20
    @Override
    public int getCount() {
        /*if (mResult.getresult().size() == 21) {
        } else {
            return mResult.getresult().size();
        }*/
        return mResult.getresult().size();
    }

    @Override
    public Object getItem(int position) {
        return 0;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        if ("xinwen".equals(mResult.getresult().get(position).gettype())) {
            return XIN_WEN;
        }
        if ("santu".equals(mResult.getresult().get(position).gettype())) {
            return THREE_PIC;
        }
        if ("zhuanti".equals(mResult.getresult().get(position).gettype())) {
            return ZHUAN_TI;
        }
        if ("hengtu".equals(mResult.getresult().get(position).gettype())) {
            return HENG_TU;
        }
        if ("huandeng".equals(mResult.getresult().get(position).gettype())) {
            return HUAN_DENG;
        }
        return -1;
    }

    @Override
    public int getViewTypeCount() {
        return 5;
    }

    //需要根据页面数改变position的值
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BitmapUtils bitmapUtils = new BitmapUtils(context);

        ViewHolder holderNews = new ViewHolder();

        int type = getItemViewType(position);

        if (convertView == null) {
            switch (type) {
                case XIN_WEN:
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_news, null);
                    holderNews.tvTitle = (TextView) convertView.findViewById(R.id.tv_news_title);
                    holderNews.tvComNum = (TextView) convertView.findViewById(R.id.tv_news_comments_num);
                    holderNews.ivOne = (ImageView) convertView.findViewById(R.id.iv_news);
                    convertView.setTag(holderNews);
                    break;
                case HENG_TU:
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_news, null);
                    holderNews.tvTitle = (TextView) convertView.findViewById(R.id.tv_news_title);
                    holderNews.tvComNum = (TextView) convertView.findViewById(R.id.tv_news_comments_num);
                    holderNews.ivOne = (ImageView) convertView.findViewById(R.id.iv_news);
                    convertView.setTag(holderNews);
                    break;
                case THREE_PIC:
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_three_pic, null);
                    holderNews.tvTitle = (TextView) convertView.findViewById(R.id.tv_three_pic_title);
                    holderNews.tvComNum = (TextView) convertView.findViewById(R.id.tv_three_pic_comments_num);
                    holderNews.ivOne = (ImageView) convertView.findViewById(R.id.iv_three_pic_1st);
                    holderNews.ivTwo = (ImageView) convertView.findViewById(R.id.iv_three_pic_2nd);
                    holderNews.ivThree = (ImageView) convertView.findViewById(R.id.iv_three_pic_3rd);
                    convertView.setTag(holderNews);
                    break;
                case ZHUAN_TI:
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_news_special, null);
                    break;
                case HUAN_DENG:
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_empty, null);
                    break;
            }
        } else {
            holderNews = (ViewHolder) convertView.getTag();
        }

        switch (type) {
            case XIN_WEN:
            case HENG_TU:
                holderNews.tvTitle.setText(mResult.getresult().get(position).gettitle());
                holderNews.tvComNum.setText(Integer.toString(mResult.getresult().get(position).getcommentsCount()));
                if (((ArrayList) mResult.getresult().get(position).getthumbnailURLs()).size() == 0) {
                    holderNews.ivOne.setVisibility(View.GONE);
                } else {
                    holderNews.ivOne.setVisibility(View.VISIBLE);
                    holderNews.ivOne.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    bitmapUtils.display(holderNews.ivOne, (String) ((ArrayList) mResult.getresult().get(position).getthumbnailURLs()).get(0));
                }
                break;
            case THREE_PIC:
                holderNews.tvTitle.setText(mResult.getresult().get(position).gettitle());
                holderNews.tvComNum.setText(Integer.toString(mResult.getresult().get(position).getcommentsCount()));
                holderNews.ivOne.setScaleType(ImageView.ScaleType.CENTER_CROP);
                bitmapUtils.display(holderNews.ivOne, (String) ((ArrayList) mResult.getresult().get(position).getthumbnailURLs()).get(0));
                holderNews.ivTwo.setScaleType(ImageView.ScaleType.CENTER_CROP);
                bitmapUtils.display(holderNews.ivTwo, (String) ((ArrayList) mResult.getresult().get(position).getthumbnailURLs()).get(1));
                holderNews.ivThree.setScaleType(ImageView.ScaleType.CENTER_CROP);
                bitmapUtils.display(holderNews.ivThree, (String) ((ArrayList) mResult.getresult().get(position).getthumbnailURLs()).get(2));
                break;
            case ZHUAN_TI:
                break;
            case HUAN_DENG:
                break;
        }
        return convertView;
    }

    class ViewHolder {
        public TextView tvTitle;
        public TextView tvComNum;
        public ImageView ivOne;
        public ImageView ivTwo;
        public ImageView ivThree;
    }
}
