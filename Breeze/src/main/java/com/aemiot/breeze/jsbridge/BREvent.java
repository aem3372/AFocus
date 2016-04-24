package com.aemiot.breeze.jsbridge;

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
