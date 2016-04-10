package com.aemiot.afoucs.webview;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;

import com.aemiot.afoucs.jsbridge.JSBridge;

public class AFWebView extends WebView {

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
        mJSBridge = new JSBridge();

        getSettings().setJavaScriptEnabled(true);
        this.addJavascriptInterface(mJSBridge, "afocus");
    }

}
