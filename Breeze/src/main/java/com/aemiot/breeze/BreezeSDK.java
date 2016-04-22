package com.aemiot.breeze;

import android.content.Context;

import com.aemiot.breeze.jsbridge.PluginManager;
import com.aemiot.breeze.jsbridge.plugin.NotifyPlugin;
import com.aemiot.breeze.jsbridge.plugin.SensorPlugin;

/**
 * Created by fanye on 16/4/10.
 */
public class BreezeSDK {

    private boolean isInitialization = false;

    private Context mContext;

    private PluginManager mPluginManager;

    private BreezeSDK() {

    }

    public void initialization(Context context) {
        if(isInitialization)
            return;
        isInitialization = true;

        mContext = context;
        mPluginManager = new PluginManager();
        configDefaultJsPlugin();
    }

    public PluginManager getPluginManager() {
        return mPluginManager;
    }

    private void configDefaultJsPlugin() {
        mPluginManager.registerPlugin("notify", new NotifyPlugin(mContext));
        mPluginManager.registerPlugin("sensor", new SensorPlugin(mContext));
    }

    private static class SingleHolder {
        public static BreezeSDK instance = new BreezeSDK();
    }

    public static BreezeSDK getInstance() {
        return SingleHolder.instance;
    }
}
