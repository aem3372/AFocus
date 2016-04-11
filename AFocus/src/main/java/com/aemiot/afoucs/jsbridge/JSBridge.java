package com.aemiot.afoucs.jsbridge;

import android.util.Log;
import android.webkit.JavascriptInterface;

import com.aemiot.afoucs.AFoucsSDK;
import com.aemiot.afoucs.webview.IWebView;

import org.json.JSONException;
import org.json.JSONObject;

public class JSBridge {

    private final static String TAG = "JSBridge";

    private IWebView mWebView;

    public JSBridge(IWebView webView) {
        mWebView = webView;
    }

    @JavascriptInterface
    public void dispatch(String plugin, String method, String params, String jsContext) {
        Log.d(TAG, "[dispatch] {plugin:" + plugin + ",method:" + method + ",params:" + params
                + ",jsContest:" + jsContext);
        AFoucsSDK.getInstance().getPluginManager().asyncExecutePluginMethod(
                plugin, method, params, new JSBridgeContext(mWebView, jsContext));
    }

    public void dispatch(String protocol) {
        try {
            JSONObject root = new JSONObject(protocol);
            String plugin = root.getString("plugin");
            String method = root.getString("method");
            String params = root.getString("params");
            String context = root.getString("context");
            dispatch(plugin, method, params, context);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
