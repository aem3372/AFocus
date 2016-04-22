package com.aemiot.breeze.webview;

import android.content.Context;

import com.aemiot.breeze.jsbridge.JSBridge;

/**
 * Created by fanye on 16/4/11.
 */
public interface IWebView {
    Context getWebContext();
    JSBridge getJSBridge();
    void evaluateJavascript(String script);
}
