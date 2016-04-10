package com.aemiot.afoucs.client;

import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by fanye on 16/4/9.
 */
public class AFWebClient extends WebViewClient {

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        return super.shouldOverrideUrlLoading(view, url);
    }
}
