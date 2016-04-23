package com.aemiot.breeze.webview;

import android.content.Context;

import com.aemiot.breeze.jsbridge.JSBridge;
import com.aemiot.breeze.jsbridge.PluginManager;

/**
 * Created by fanye on 16/4/11.
 */
public interface IWebView {
    Context getWebContext();
    JSBridge getJSBridge();
    PluginManager getPluginManager();

    void evaluateJavascript(String script);

    void showProgressBar();
    void hideProgressBar();
}
