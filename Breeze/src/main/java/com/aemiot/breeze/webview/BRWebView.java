package com.aemiot.breeze.webview;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.aemiot.breeze.client.BRWebChromeClient;
import com.aemiot.breeze.client.BRWebClient;
import com.aemiot.breeze.jsbridge.JSBridge;

public class BRWebView extends WebView implements IWebView{

    public static final String TAG = "AFWebView";

    private JSBridge mJSBridge;

    public BRWebView(Context context) {
        super(context);
        init();
    }

    public BRWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BRWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        mJSBridge = new JSBridge(this);
        setWebViewClient(new BRWebClient());
        setWebChromeClient(new BRWebChromeClient());
        WebSettings settings = getSettings();
        settings.setJavaScriptEnabled(true);
        String ua = settings.getUserAgentString();
        if(ua == null) {
            ua = "";
        } else {
            ua += " ";
        }
        settings.setUserAgentString(ua + "AFocus/1.0");
        Log.i(TAG, "UA:" + settings.getUserAgentString());
    }

    @Override
    public Context getWebContext() {
        return getContext();
    }

    @Override
    public JSBridge getJSBridge() {
        return mJSBridge;
    }

    @Override
    public void evaluateJavascript(final String script) {
        new Handler(getContext().getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                loadUrl("javascript:" + script);
            }
        });
    }
}
