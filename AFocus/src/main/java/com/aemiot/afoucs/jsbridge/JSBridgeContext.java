package com.aemiot.afoucs.jsbridge;

import com.aemiot.afoucs.webview.IWebView;

/**
 * Created by fanye on 16/4/10.
 */
public class JSBridgeContext {

    public JSBridgeContext() {

    }

    public JSBridgeContext(IWebView webView, String str) {
        mWebView = webView;
    }

    int token;

    public void callback() {
        mWebView.evaluateJavascript("lib.AFocus.callback(" + token + ",'successed','');");
    }

    IWebView mWebView;
    ISuccessedCallback mSuccCallback;
    IFailedCallback mFailCallback;
}
