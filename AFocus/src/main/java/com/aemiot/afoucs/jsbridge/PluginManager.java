package com.aemiot.afoucs.jsbridge;

import android.os.AsyncTask;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by fanye on 16/4/10.
 */
public class PluginManager {

    private static final String TAG = "PluginManager";

    Map<String, HybridPlugin> mPluginMap;

    public PluginManager() {
        mPluginMap = new HashMap<>();
    }

    public void registerPlugin(String name, HybridPlugin plugin) {
        mPluginMap.put(name, plugin);
    }

    public void unregisterPlugin(String name) {
        mPluginMap.remove(name);
    }

    public void executePluginMethod(String plugin, String method, String params, JSBridgeContext jsContext) {
        HybridPlugin hybridPlugin = mPluginMap.get(plugin);
        if(hybridPlugin != null) {
            JSBridgeResult result = hybridPlugin.execute(method, params, jsContext);
            jsContext.callback();
        } else {
            Log.e(TAG, "not find plugin");
        }
    }

    public void asyncExecutePluginMethod(final String plugin, final String method, final String params, final JSBridgeContext jsContext) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... args) {
                executePluginMethod(plugin, method, params, jsContext);
                return null;
            }
        }.execute();
    }
}
