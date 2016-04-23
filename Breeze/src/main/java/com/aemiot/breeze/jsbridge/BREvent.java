package com.aemiot.breeze.jsbridge;

/**
 * Created by fanye on 16/4/23.
 */
public class BREvent {
    public String code;
    public String info;

    public BREvent() {

    }

    public BREvent(String code) {
        this(code, "{}");
    }

    public BREvent(String code, String info) {
        this.code = code;
        this.info = info;
    }
}
