package com.aemiot.afoucs.jsbridge;

import java.util.Map;

/**
 * Created by fanye on 16/3/29.
 */
public interface HybridPlugin {
    void execute(String method, String params, JSBridgeContext jsContext);
}
