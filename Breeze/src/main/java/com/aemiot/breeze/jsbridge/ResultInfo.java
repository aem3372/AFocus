package com.aemiot.breeze.jsbridge;

/**
 * Created by fanye on 16/4/11.
 */
public class ResultInfo {
    final static String SUCCESSED_CODE = "AF_SUCCESSED";
    final static String FAILED_CODE = "AF_FAILED";

    final static ResultInfo SUCCESSED_RESULT_INFO = new ResultInfo(SUCCESSED_CODE, "none");
    final static ResultInfo FAILED_RESULT_INFO = new ResultInfo(FAILED_CODE, "none");

    public ResultInfo(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String code;
    public String msg;
}
