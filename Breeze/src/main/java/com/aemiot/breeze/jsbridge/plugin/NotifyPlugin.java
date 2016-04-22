package com.aemiot.breeze.jsbridge.plugin;

import android.content.Context;
import android.widget.Toast;

import com.aemiot.breeze.jsbridge.HybridPlugin;
import com.aemiot.breeze.jsbridge.CallMethodContext;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 自定义的Android代码和JavaScript代码之间的桥梁类
 */
public class NotifyPlugin implements HybridPlugin
{
    Context mContext;

    public NotifyPlugin(Context c)
    {
        mContext = c;
    }

    public void showToast(final String text)
    {
        Toast.makeText(mContext, text, Toast.LENGTH_SHORT).show();
    }

    public void showToast()
    {
        showToast("toast test");
    }

    @Override
    public void execute(String method, String params, CallMethodContext jsContext) {
        if("showToast".equals(method)) {
            if(params != null) {
                try {
                    JSONObject jsonObject = new JSONObject(params);
                    if (jsonObject.has("text")) {
                        String text = jsonObject.getString("text");
                        showToast(text);
                        jsContext.success();
                    }
                } catch (JSONException e) {
                    jsContext.fail();
                }
            }
            showToast();
            jsContext.success();
        }
    }
}
