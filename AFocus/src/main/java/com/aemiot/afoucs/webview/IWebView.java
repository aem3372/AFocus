package com.aemiot.afoucs.webview;

import com.aemiot.afoucs.jsbridge.JSBridge;

/**
 * Created by fanye on 16/4/11.
 */
public interface IWebView {
    JSBridge getJSBridge();
    void evaluateJavascript(String script);
}
