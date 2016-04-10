package com.aemiot.afoucs;

import android.content.Context;

import com.aemiot.afoucs.jsbridge.PluginManager;
import com.aemiot.afoucs.jsbridge.plugin.NotifyPlugin;
import com.aemiot.afoucs.jsbridge.plugin.SensorPlugin;

/**
 * Created by fanye on 16/4/10.
 */
public class AFoucsSDK {

    private boolean isInitialization = false;

    private Context mContext;

    private PluginManager mPluginManager;

    private AFoucsSDK() {

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
        public static AFoucsSDK instance = new AFoucsSDK();
    }

    public static AFoucsSDK getInstance() {
        return SingleHolder.instance;
    }
}
