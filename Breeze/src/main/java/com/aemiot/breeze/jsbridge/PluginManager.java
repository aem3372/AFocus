package com.aemiot.breeze.jsbridge;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by fanye on 16/4/10.
 */
public class PluginManager {

    private static final String TAG = "PluginManager";

    private Map<String, HybridPlugin> mPluginMap;

    public PluginManager() {
        mPluginMap = new HashMap<>();
    }

    public void registerPlugin(String name, HybridPlugin plugin) {
        mPluginMap.put(name, plugin);
    }

    public void unregisterPlugin(String name) {
        mPluginMap.remove(name);
    }

    public HybridPlugin getPlugin(String name) {
        return mPluginMap.get(name);
    }
}
