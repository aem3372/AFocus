package com.aemiot.afoucs.jsbridge;

/**
 * Created by fanye on 16/3/29.
 */
public interface HybridPlugin {
    void execute(String method, String params, CallMethodContext jsContext);
}
