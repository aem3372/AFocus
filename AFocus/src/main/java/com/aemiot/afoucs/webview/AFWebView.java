package com.aemiot.afoucs.webview;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.ValueCallback;
import android.webkit.WebView;

import com.aemiot.afoucs.client.AFWebChromeClient;
import com.aemiot.afoucs.client.AFWebClient;
import com.aemiot.afoucs.jsbridge.JSBridge;

public class AFWebView extends WebView implements IWebView{

    private JSBridge mJSBridge;

    public AFWebView(Context context) {
        super(context);
        init();
    }

    public AFWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AFWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        mJSBridge = new JSBridge(this);
        setWebViewClient(new AFWebClient());
        setWebChromeClient(new AFWebChromeClient());
        getSettings().setJavaScriptEnabled(true);
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
