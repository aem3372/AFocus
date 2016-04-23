package com.aemiot.breeze.jsbridge;

import android.util.Log;

public class CallMethodContext {

    private String mPlugin;
    private String mMethod;
    private String mParams;

    private int mToken;

    private JSBridge mBridge;

    public CallMethodContext(String plugin, String method, String params, int token,
                             JSBridge jsBridge) {
        mPlugin = plugin;
        mMethod = method;
        mParams = params;
        mToken = token;

        mBridge = jsBridge;
    }

    public String getPlugin() {
        return mPlugin;
    }

    public String getMethod() {
        return mMethod;
    }

    public String getParams() {
        return mParams;
    }

    public int getToken() {
        return mToken;
    }

    public void success(ResultInfo info) {
        if(mBridge != null) {
            mBridge.callbackSuccessed(this, info);
        }
    }

    public void fail(ResultInfo info) {
        if(mBridge != null) {
            mBridge.callbackFailed(this, info);
        }
    }

    public void success() {
        success(ResultInfo.SUCCESSED_RESULT_INFO);
    }

    public void fail() {
        fail(ResultInfo.FAILED_RESULT_INFO);
    }

    public void sendEvent(BREvent event) {
        if(mBridge != null) {
            mBridge.fire(event);
        }
    }
}
