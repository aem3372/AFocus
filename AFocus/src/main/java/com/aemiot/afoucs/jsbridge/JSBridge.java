package com.aemiot.afoucs.jsbridge;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.webkit.JavascriptInterface;

import com.aemiot.afoucs.AFoucsSDK;
import com.aemiot.afoucs.webview.IWebView;

import org.json.JSONException;
import org.json.JSONObject;

public class JSBridge implements Handler.Callback{

    private final static String TAG = "JSBridge";

    private final static int CALL_METHOD = 0x1001;

    private IWebView mWebView;
    private Handler mHandler;

    public JSBridge(IWebView webView) {
        mWebView = webView;
        mHandler = new Handler(Looper.getMainLooper(), this);
    }

    // json协议 {plugin: "pluginName", method: "methodName", params: {}, info: {}}
    @JavascriptInterface
    public void dispatch(String protocol) {
        try {
            JSONObject root = new JSONObject(protocol);
            String plugin = root.getString("plugin");
            String method = root.getString("method");
            String params = root.getString("params");
            String context = root.getString("info");
            dispatch(plugin, method, params, context);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @JavascriptInterface
    public void dispatch(String plugin, String method, String params, String info) {
        Log.d(TAG, "[dispatch] {plugin:" + plugin + ",method:" + method + ",params:" + params
                + ",info:" + info);
        CallMethodContext jsContext = new CallMethodContext();
        jsContext.plugin = plugin;
        jsContext.method = method;
        jsContext.params = params;
        JSONObject json = null;
        try {
            json = new JSONObject(info);
            jsContext.token =  json.getInt("token");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        dispatch(jsContext);
    }

    public void dispatch(CallMethodContext jsContext) {
        Message msg = Message.obtain();
        msg.arg1 = CALL_METHOD;
        msg.obj = jsContext;
        mHandler.sendMessage(msg);
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.arg1) {
            case CALL_METHOD: {
                CallMethodContext jsContext = (CallMethodContext) msg.obj;

                HybridPlugin hybridPlugin = AFoucsSDK.getInstance().getPluginManager().getPlugin(jsContext.plugin);
                if(hybridPlugin != null) {
                    hybridPlugin.execute(jsContext.method, jsContext.params, jsContext);
                } else {
                    Log.e(TAG, "not find plugin");
                }
            }
            return true;
        }
        return false;
    }

    public void callback(CallMethodContext context) {
        mWebView.evaluateJavascript("lib.AFocus.callback(" + context.token + ",'successed','');");
    }
}
