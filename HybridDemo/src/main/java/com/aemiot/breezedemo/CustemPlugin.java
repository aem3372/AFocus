package com.aemiot.breezedemo;

import android.support.annotation.Nullable;
import android.util.Log;

import com.aemiot.breeze.jsbridge.CallMethodContext;
import com.aemiot.breeze.jsbridge.HybridPlugin;
import com.aemiot.breeze.jsbridge.ResultInfo;

import org.json.JSONException;
import org.json.JSONObject;

public class CustemPlugin extends HybridPlugin
{
    @Override
    public void execute(String method, String params, CallMethodContext jsContext) {
        if("custemMethod".equals(method)) {
            if(params != null) {
                try {
                    JSONObject jsonObject = new JSONObject(params);
                    if (jsonObject.has("value")) {
                        String value = jsonObject.getString("value");
                        custemMethod(value);
                        jsContext.success();
                    } else {
                        custemMethod(null);
                    }
                } catch (JSONException e) {
                    jsContext.fail();
                }
            }
        }
    }

    private void custemMethod(@Nullable String value) {

    }
}
