package com.aemiot.afoucs.jsbridge;

/**
 * Created by fanye on 16/3/29.
 */
public interface HybridPlugin {
    JSBridgeResult execute(String method, String params, CallMethodContext jsContext);
}
