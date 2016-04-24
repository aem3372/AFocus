package com.aemiot.breeze;

import android.net.Uri;

import com.aemiot.breeze.jsbridge.PluginConfig;
import com.aemiot.breeze.jsbridge.plugin.NotifcationPlugin;
import com.aemiot.breeze.jsbridge.plugin.ShakePlugin;

public class BreezeSDK {

    private boolean isInitialization = false;
    private Uri mUri;

    private PluginConfig mPluginConfig;

    private BreezeSDK() {

    }

    public void initialization() {
        if(isInitialization) {
            return;
        }
        isInitialization = true;

        mPluginConfig = new PluginConfig();
    }

    public PluginConfig getDefaultPluginConfig() {
        return mPluginConfig;
    }

    public void setBrowserUri(Uri uri) {
        mUri = uri;
    }

    public Uri getBrowserUri() {
        return mUri;
    }

    private static class SingleHolder {
        public static BreezeSDK instance = new BreezeSDK();
    }

    public static BreezeSDK getInstance() {
        return SingleHolder.instance;
    }
}
