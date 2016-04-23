package com.aemiot.breeze.jsbridge;

import android.content.Context;

/**
 * Created by fanye on 16/3/29.
 */
public abstract class HybridPlugin {

    private Context mContext;
    private JSBridge mBridge;

    public Context getContext() {
        return mContext;
    }

    public JSBridge getBridge() {
        return mBridge;
    }

    public void attach(Context context, JSBridge jsBridge) {
        mContext = context;
        mBridge = jsBridge;
        onAttach();
    }

    public void active() {
        onActive();
    }

    public void inactive() {
        onInactive();
    }

    public void detach() {
        mContext = null;
        mBridge = null;
        onDetach();
    }

    protected void onAttach() {}
    protected void onActive() {}
    protected void onInactive() {}
    protected void onDetach() {}

    protected abstract void execute(String method, String params, CallMethodContext jsContext);
}
