package com.uplooking.dell.gamenews.strategy;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.uplooking.dell.gamenews.R;
import com.uplooking.dell.myjsondata.strategyrequestlist.StrategyRequest;
import com.uplooking.dell.myjsondata.strategyrequestlist.StrategyRequestRootClass;
import com.uplooking.dell.myjsondata.strategyresult.StrategyResultRootClass;

import org.apache.http.entity.StringEntity;

import java.io.UnsupportedEncodingException;

/**
 * Created by dell on 2016/1/8.
 */
public class StrategyFollowContentFragment extends Fragment {
    private static String STRATEGY_URL = "http://appapi2.gamersky.com/v2/strategy";
    private static String mStringName = "关注";
    private static String mStringPageIndex = "1";
    private static String mStringPageCount = "20";
    private static String mType = "0";

    private RelativeLayout mRelativeLayout;
    private GridView mGridView;

    private StrategyResultRootClass mResult;

    private Gson gson;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.strategy_follow_content,null);
        findView();
        sendRequestData();
        return view;
    }

    private void findView(){
        mRelativeLayout = (RelativeLayout)view.findViewById(R.id.strategy_load_rl);
        mGridView = (GridView)view.findViewById(R.id.strategy_follow_gridView);
    }

    private void sendRequestData(){
        gson = new Gson();
        RequestParams params = new RequestParams();
        final StrategyRequestRootClass request = new StrategyRequestRootClass();
        setRequestData(request);

        try {
            params.setBodyEntity(new StringEntity(gson.toJson(request),"utf-8"));
            HttpUtils http = new HttpUtils(5000);
            http.send(HttpRequest.HttpMethod.POST,
                    STRATEGY_URL,
                    params,
                    new RequestCallBack<String>() {
                        @Override
                        public void onSuccess(ResponseInfo<String> responseInfo) {
                            mResult = gson.fromJson(responseInfo.result,StrategyResultRootClass.class);
                            if(mResult.geterrorCode() == 1){
                                Log.i("test:", mStringName + mResult.geterrorMessage());
                            }else {
                                Log.i("test:", mStringName + "获取到数据:" + mResult.getresult().get(0).gettitle());
                                mRelativeLayout.setVisibility(View.GONE);
                                mGridView.setVisibility(View.VISIBLE);
                                setGridViewContent();
                                setGridViewContentListener();
                            }
                        }

                        @Override
                        public void onFailure(HttpException e, String s) {
                            if(getActivity() ==null){
                            }else {
                                Toast.makeText(getActivity(), "网络连接超时", Toast.LENGTH_SHORT).show();
                            }
                            mRelativeLayout.setVisibility(View.GONE);
                            mGridView.setVisibility(View.VISIBLE);
                        }
                    });
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private void setRequestData(StrategyRequestRootClass request){
        StrategyRequest strategyRequest= request.getrequest();
        strategyRequest.setpageIndex(mStringPageIndex);
        strategyRequest.setpageCount(mStringPageCount);
        strategyRequest.settype(mType);
    }

    private void setGridViewContent(){
        StrategyFollowAdapter adapter = new StrategyFollowAdapter(mResult,getActivity());

        mGridView.setAdapter(adapter);
    }

    private void setGridViewContentListener(){
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("test", "攻略中的  " + mStringName + "  的第 " + position + "项被点击");
            }
        });
    }

    public void setName(String name) {
        mStringName = name;
    }

    public void setPageCount(String pageCount) {
        mStringPageCount = pageCount;
    }

    public void setType(String type) {
        mType = type;
    }
}
