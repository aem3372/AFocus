package com.aemiot.breeze.jsbridge;

public class ResultInfo {
    public final static String SUCCESSED_CODE = "BR_SUCCESSED";
    public final static String FAILED_CODE = "BR_FAILED";

    public final static ResultInfo SUCCESSED_RESULT_INFO = new ResultInfo(SUCCESSED_CODE, "none");
    public final static ResultInfo FAILED_RESULT_INFO = new ResultInfo(FAILED_CODE, "none");

    public ResultInfo(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String code;
    public String msg;
}
