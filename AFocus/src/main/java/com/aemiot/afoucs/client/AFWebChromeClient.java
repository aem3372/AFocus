package com.aemiot.afoucs.client;

import android.webkit.JsPromptResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.aemiot.afoucs.webview.IWebView;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by fanye on 16/4/9.
 */
public class AFWebChromeClient extends WebChromeClient {
    @Override
    public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
        if("hybrid://protocol/af".equals(defaultValue)) {
            if(view instanceof IWebView && ((IWebView) view).getJSBridge() != null) {
                ((IWebView) view).getJSBridge().dispatch(message);
                result.confirm("");
                return true;
            }
        }
        return super.onJsPrompt(view, url, message, defaultValue, result);
    }
}
