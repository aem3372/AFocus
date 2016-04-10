package com.aemiot.afoucs.jsbridge;

import android.os.AsyncTask;
import android.util.Log;
import android.webkit.JavascriptInterface;

import com.aemiot.afoucs.AFoucsSDK;

public class JSBridge {

    private final static String TAG = "JSBridge";

    @JavascriptInterface
    public void dispatch(String plugin, String mothed, String params, String jsContext) {
        Log.d(TAG, "[dispatch] {plugin:" + plugin + ",mothed:" + mothed + ",params:" + params
                + ",jsContest:" + jsContext);
        AFoucsSDK.getInstance().getPluginManager().asyncExecutePluginMothed(
                plugin, mothed, params, new JSBridgeContext(jsContext));
    }
}
