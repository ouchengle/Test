package com.uplooking.dell.gamenews.strategy;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
import com.uplooking.dell.gamenews.RefreshLoadListView;
import com.uplooking.dell.myjsondata.strategyrequestlist.StrategyRequestRootClass;
import com.uplooking.dell.myjsondata.strategyresult.StrategyResultRootClass;

import org.apache.http.entity.StringEntity;

import java.io.UnsupportedEncodingException;

/**
 * Created by dell on 2016/1/27.
 */
public class StrategyAllContentFragment extends Fragment implements RefreshLoadListView.IRefreshListener{
    private static String STRATEGY_URL = "http://appapi2.gamersky.com/v2/strategy";
    private static String mStringName = "全部";
    private static String mStringPageIndex = "1";
    private static String mStringPageCount = "10000";
    private static String mType = "1";

    private RelativeLayout mRelativeLayout;
    private RefreshLoadListView mListView;

    private StrategyResultRootClass mResult;
    private StrategyAllContentAdapter mAdapter;

    private Gson gson;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.strategy_all_content, null);
        initData();
        findView();
        sendRequestData();
        return view;
    }

    private void initData() {
        mResult = null;
        mAdapter = null;
    }

    private void findView() {
        mRelativeLayout = (RelativeLayout) view.findViewById(R.id.strategy_all_load_rl);
        mListView = (RefreshLoadListView) view.findViewById(R.id.strategy_all_list_view);
    }

    private void sendRequestData() {
        gson = new Gson();
        RequestParams params = new RequestParams();
        StrategyRequestRootClass request = new StrategyRequestRootClass();

        request.getrequest().setpageIndex(mStringPageIndex);
        request.getrequest().setpageCount(mStringPageCount);
        request.getrequest().settype(mType);

        try {
            params.setBodyEntity(new StringEntity(gson.toJson(request), "utf-8"));
            HttpUtils http = new HttpUtils(5000);
            http.send(HttpRequest.HttpMethod.POST,
                    STRATEGY_URL,
                    params,
                    new RequestCallBack<String>() {
                        @Override
                        public void onSuccess(ResponseInfo<String> responseInfo) {
                            if (mResult == null) {
                                mResult = gson.fromJson(responseInfo.result, StrategyResultRootClass.class);
                                if (mResult.geterrorCode() == 1) {
                                    Log.i("test:", mStringName + mResult.geterrorMessage());
                                } else {
                                    mRelativeLayout.setVisibility(View.GONE);
                                    mListView.setVisibility(View.VISIBLE);
                                    setListViewContent();
                                    setListViewContentListener();
                                }
                            }
                            mListView.refreshComplete();
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

    private void setListViewContent() {
            mAdapter = new StrategyAllContentAdapter(mResult,getActivity());

            mListView.setInterface(this);

            mListView.setAdapter(mAdapter);
    }

    private void setListViewContentListener() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("test", "攻略中的  " + mStringName + "  的第 " + position + "条目被点击");
            }
        });
    }

    @Override
    public void onRefresh() {
        initData();
        sendRequestData();
    }

    @Override
    public void onLoad() {
        mListView.loadComplete();
    }
}
