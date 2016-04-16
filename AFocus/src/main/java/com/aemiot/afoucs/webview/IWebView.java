package com.aemiot.afoucs.webview;

import android.content.Context;

import com.aemiot.afoucs.jsbridge.JSBridge;

/**
 * Created by fanye on 16/4/11.
 */
public interface IWebView {
    Context getWebContext();
    JSBridge getJSBridge();
    void evaluateJavascript(String script);
}
