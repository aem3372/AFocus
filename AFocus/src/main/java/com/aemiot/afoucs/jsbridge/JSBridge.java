package com.aemiot.afoucs.jsbridge;

import android.os.AsyncTask;
import android.util.Log;
import android.webkit.JavascriptInterface;

import com.aemiot.afoucs.AFoucsSDK;
import com.aemiot.afoucs.webview.IWebView;

public class JSBridge {

    private final static String TAG = "JSBridge";

    private IWebView mWebView;

    public JSBridge(IWebView webView) {
        mWebView = webView;
    }

    @JavascriptInterface
    public void dispatch(String plugin, String mothed, String params, String jsContext) {
        Log.d(TAG, "[dispatch] {plugin:" + plugin + ",mothed:" + mothed + ",params:" + params
                + ",jsContest:" + jsContext);
        AFoucsSDK.getInstance().getPluginManager().asyncExecutePluginMothed(
                plugin, mothed, params, new JSBridgeContext(mWebView, jsContext));
    }
}
