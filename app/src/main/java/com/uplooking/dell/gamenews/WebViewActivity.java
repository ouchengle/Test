package com.uplooking.dell.gamenews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.uplooking.dell.gamenews.news.NewsContentFragment;
import com.uplooking.dell.myjsondata.newswebviewrequest.WebViewRequestRootClass;
import com.uplooking.dell.myjsondata.newswebviewresult.WebViewResultRootClass;

import org.apache.http.entity.StringEntity;

import java.io.UnsupportedEncodingException;

public class WebViewActivity extends AppCompatActivity {
    private static String mUrl = "http://appapi2.gamersky.com/v2/TwoArticle";
    private String contentId;

    private WebView mWebView;
    private RelativeLayout mWebProgressBar;

    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        mWebProgressBar = (RelativeLayout) findViewById(R.id.web_load_rl);
        mWebView = (WebView) findViewById(R.id.webView);
        getURLByInternet();
    }

    /**
     * 获取URL链接
     */
    private void getURLByInternet(){
        gson = new Gson();

        RequestParams params = new RequestParams();

        WebViewRequestRootClass request =  new WebViewRequestRootClass();
        request.getrequest().setcontentId(this.getIntent().getStringExtra(NewsContentFragment.KEY_CONTENT_ID));
        request.getrequest().setpageIndex("1");

        try {
            params.setBodyEntity(new StringEntity(gson.toJson(request),"utf-8"));
            HttpUtils http = new HttpUtils(5 * 1000);
            http.send(HttpRequest.HttpMethod.POST,
                    mUrl,
                    params,
                    new RequestCallBack<String>() {
                        @Override
                        public void onSuccess(ResponseInfo<String> responseInfo) {
                            WebViewResultRootClass result = gson.fromJson(responseInfo.result,WebViewResultRootClass.class);
                            if(result.geterrorCode() == 1){
                                Toast.makeText(WebViewActivity.this,"获取数据失败",Toast.LENGTH_SHORT).show();
                            }else {
                                mWebProgressBar.setVisibility(View.GONE);
                                mWebView.setVisibility(View.VISIBLE);
                                setWebViewAttr(result);
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

    /**
     * 设置webview的属性，并加载页面
     * @param result
     */
    private void setWebViewAttr(WebViewResultRootClass result){
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        mWebView.loadUrl(result.getresult().getoriginURL());
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }
}
