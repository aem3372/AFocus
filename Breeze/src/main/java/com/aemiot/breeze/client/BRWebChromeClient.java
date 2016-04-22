package com.aemiot.breeze.client;

import android.webkit.JsPromptResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.aemiot.breeze.webview.IWebView;

/**
 * Created by fanye on 16/4/9.
 */
public class BRWebChromeClient extends WebChromeClient {
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
