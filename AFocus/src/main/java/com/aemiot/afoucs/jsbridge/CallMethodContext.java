package com.aemiot.afoucs.jsbridge;

import com.aemiot.afoucs.webview.IWebView;

/**
 * Created by fanye on 16/4/10.
 */
public class CallMethodContext {

    public String plugin;
    public String method;
    public String params;

    int token;

    ISuccessedCallback mSuccCallback;
    IFailedCallback mFailCallback;
}
