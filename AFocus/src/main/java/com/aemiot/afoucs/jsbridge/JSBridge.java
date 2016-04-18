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

public class JSBridge implements Handler.Callback {

    private final static String TAG = "JSBridge";

    private final static int CALL_METHOD = 0x1001;

    private IWebView mWebView;
    private Handler mHandler;
    private CallbackDispatcher mCallbackDispatcher;

    public JSBridge(IWebView webView) {
        mWebView = webView;
        mHandler = new Handler(Looper.getMainLooper(), this);
        mCallbackDispatcher = new CallbackDispatcher();
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
        int token = -1;
        JSONObject json = null;
        try {
            json = new JSONObject(info);
            token =  json.getInt("token");
        } catch (JSONException e) {

        }
        CallMethodContext jsContext = new CallMethodContext(
                plugin, method, params, token, mCallbackDispatcher, mCallbackDispatcher);
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

                HybridPlugin hybridPlugin = AFoucsSDK.getInstance().getPluginManager().getPlugin(jsContext.getPlugin());
                if(hybridPlugin != null) {
                    hybridPlugin.execute(jsContext.getMethod(), jsContext.getParams(), jsContext);
                } else {
                    Log.e(TAG, "not find plugin");
                }
            }
            return true;
        }
        return false;
    }

    public void callbackSuccessed(CallMethodContext context, ResultInfo info) {
        callback(context, "successed", info);
    }

    public void callbackFailed(CallMethodContext context, ResultInfo info) {
        callback(context, "failed", info);
    }

    public void callback(CallMethodContext context, String result, ResultInfo info) {
        JSONObject resultInfo = new JSONObject();
        try {
            resultInfo.put("code", info.code);
            resultInfo.put("msg", info.msg);
        } catch (JSONException e) {

        }
        mWebView.evaluateJavascript("lib.AFocus.callback(" +
                context.getToken() + ",\'" + result + "\'," + resultInfo + ");");
    }

    private class CallbackDispatcher implements ISuccessedCallback, IFailedCallback{

        @Override
        public void failed(CallMethodContext context, ResultInfo info) {
            callbackFailed(context, info);
        }

        @Override
        public void successed(CallMethodContext context, ResultInfo info) {
            callbackSuccessed(context, info);
        }
    }
}
