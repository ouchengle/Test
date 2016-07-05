package com.uplooking.dell.gamenews.handgame;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.uplooking.dell.gamenews.R;
import com.uplooking.dell.myjsondata.handgamerequestlist.HandGameRequestRootClass;
import com.uplooking.dell.myjsondata.handgameresult.HandGameResultRootClass;
import com.uplooking.dell.myjsondata.newsrequestlist.NewsRequest;
import com.uplooking.dell.myjsondata.newsrequestlist.NewsRequestRootClass;
import com.uplooking.dell.myjsondata.newsresult.NewsResultRootClass;

import org.apache.http.entity.StringEntity;

import java.io.UnsupportedEncodingException;

/**
 * Created by dell on 2016/1/8.
 * 分类的布局未做，导致没有写相应的数据获取代码
 */
public class HandGameContentFragment extends Fragment {
    private String HAND_GAME_SEARCH_URL = "http://appapi2.gamersky.com/v2/TwoGetSySearch";
    private String HAND_GAME_URL = "http://appapi2.gamersky.com/v2/AllChannelList";

    private String name;
    private String type;
    private String netType = "全部";
    private String gameType = "全部";
    private String gameTag = "全部";
    private String orderKey = "时间";
    private String searchOrder = "des";
    private String pageIndex;
    private Gson gson;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.hand_game_content,null);
        Log.i("test","~~手游界面");
        sendRequestData();
        return view;
    }

    private void sendRequestData(){
        gson = new Gson();
        RequestParams params = new RequestParams();
        final NewsRequestRootClass request = new NewsRequestRootClass();
        setRequestData(request);

        try {
            params.setBodyEntity(new StringEntity(gson.toJson(request),"utf-8"));
            HttpUtils http = new HttpUtils(5000);
            http.send(HttpRequest.HttpMethod.POST,
                    HAND_GAME_URL,
                    params,
                    new RequestCallBack<String>() {
                        @Override
                        public void onSuccess(ResponseInfo<String> responseInfo) {
                            HandGameResultRootClass result = gson.fromJson(responseInfo.result,HandGameResultRootClass.class);
                            if(result.geterrorCode() == 1){
                                Log.i("test:", name + result.geterrorMessage());
                            }else {
                                Log.i("test:",name + "获取到数据:" + result.getresult().get(0).gettitle());

                            }
                        }

                        @Override
                        public void onFailure(HttpException e, String s) {

                        }
                    });
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private void setRequestData(NewsRequestRootClass request){
        NewsRequest request1 = request.getrequest();
        request1.setparentNodeId("shouyou");
        request1.setnodeIds("0");
        request1.setpageIndex("1");
        request1.setType(type);
        request1.setelementsCountPerPage(20);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }
}
