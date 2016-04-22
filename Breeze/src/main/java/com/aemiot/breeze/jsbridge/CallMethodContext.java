package com.aemiot.breeze.jsbridge;

import java.lang.ref.WeakReference;

/**
 * Created by fanye on 16/4/10.
 */
public class CallMethodContext {

    private String mPlugin;
    private String mMethod;
    private String mParams;

    private int mToken;

    private WeakReference<ISuccessedCallback> mSuccCallback;
    private WeakReference<IFailedCallback> mFailCallback;

    public CallMethodContext(String plugin, String method, String params, int token,
                             ISuccessedCallback succCallback, IFailedCallback failCallback) {
        mPlugin = plugin;
        mMethod = method;
        mParams = params;
        mToken = token;

        mSuccCallback = new WeakReference<>(succCallback);
        mFailCallback = new WeakReference<>(failCallback);
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
        if(mSuccCallback.get() != null) {
            mSuccCallback.get().successed(this, info);
        }
    }

    public void fail(ResultInfo info) {
        if(mFailCallback.get() != null) {
            mFailCallback.get().failed(this, info);
        }
    }

    public void success() {
        success(ResultInfo.SUCCESSED_RESULT_INFO);
    }

    public void fail() {
        fail(ResultInfo.FAILED_RESULT_INFO);
    }
}
