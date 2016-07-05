package com.uplooking.dell.gamenews.strategy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;
import com.uplooking.dell.gamenews.R;
import com.uplooking.dell.myjsondata.strategyresult.StrategyResultRootClass;

/**
 * Created by dell on 2016/1/27.
 */
public class StrategyAllContentAdapter extends BaseAdapter {
    private Context context;
    private StrategyResultRootClass mResult;

    public StrategyAllContentAdapter(StrategyResultRootClass result,Context context){
        this.mResult = result;
        this.context = context;
    }

    @Override
    public int getCount() {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        BitmapUtils bitmapUtils = new BitmapUtils(context);

        ViewHolder holder = new ViewHolder();

        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_strategy_all,null);
            holder.tvTitle = (TextView) convertView.findViewById(R.id.tv_strategy_all_title);
            holder.tvSubCount = (TextView) convertView.findViewById(R.id.tv_strategy_sub_count);
            holder.ivPic = (ImageView) convertView.findViewById(R.id.iv_strategy_all);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder)convertView.getTag();
        }

        holder.tvTitle.setText(mResult.getresult().get(position).gettitle());
        holder.tvSubCount.setText(Integer.toString(mResult.getresult().get(position).getfavoriteCnt()));
        bitmapUtils.display(holder.ivPic,mResult.getresult().get(position).getthumbnailUrl());

        return convertView;
    }

    class ViewHolder{
        public TextView tvTitle;
        public TextView tvSubCount;
        public ImageView ivPic;
    }
}
