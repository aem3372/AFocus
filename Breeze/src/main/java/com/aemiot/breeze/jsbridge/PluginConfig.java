package com.aemiot.breeze.jsbridge;

import com.aemiot.breeze.jsbridge.plugin.NotifcationPlugin;
import com.aemiot.breeze.jsbridge.plugin.ShakePlugin;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by fanye on 16/4/24.
 */
public class PluginConfig {
    private Map<String, Class<? extends HybridPlugin>> mDefaultPlugin = new HashMap<>();

    public PluginConfig() {
        configDefaultJsPlugin();
    }

    private void configDefaultJsPlugin() {
        mDefaultPlugin.put("notifcation", NotifcationPlugin.class);
        mDefaultPlugin.put("shake", ShakePlugin.class);
    }

    public Map<String, Class<? extends HybridPlugin>> getDefaultPlugin() {
        return Collections.unmodifiableMap(mDefaultPlugin);
    }
}
