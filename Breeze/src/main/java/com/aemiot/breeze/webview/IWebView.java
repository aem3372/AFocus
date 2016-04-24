package com.aemiot.breeze.webview;

import android.content.Context;

import com.aemiot.breeze.jsbridge.HybridPlugin;
import com.aemiot.breeze.jsbridge.JSBridge;
import com.aemiot.breeze.jsbridge.PluginManager;

/**
 * Created by fanye on 16/4/11.
 */
public interface IWebView {
    Context getWebContext();
    JSBridge getJSBridge();

    void registerPlugin(String name, HybridPlugin plugin);
    void unregisterPlugin(String name);
    HybridPlugin getPlugin(String name);

    void evaluateJavascript(String script);

    void showProgressBar();
    void hideProgressBar();
}
