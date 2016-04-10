package com.aemiot.afoucs.jsbridge.plugin;

import android.content.Context;
import android.os.Handler;
import android.util.JsonReader;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import com.aemiot.afoucs.jsbridge.HybridPlugin;
import com.aemiot.afoucs.jsbridge.JSBridgeContext;

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
        new Handler(mContext.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(mContext, text, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void showToast()
    {
        showToast("toast test");
    }

    @Override
    public void execute(String method, String params, JSBridgeContext jsContext) {
        if("showToast".equals(method)) {
            if(params != null) {
                try {
                    JSONObject jsonObject = new JSONObject(params);
                    if (jsonObject.has("text")) {
                        String text = jsonObject.getString("text");
                        showToast(text);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            showToast();
        }
    }
}
