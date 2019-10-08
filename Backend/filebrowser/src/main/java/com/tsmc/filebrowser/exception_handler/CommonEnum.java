package com.tsmc.filebrowser.exception_handler;

public enum CommonEnum implements BaseErrorInfoInterface {
    // 数据操作错误定义
    SUCCESS("200", "Success!"), 
    BODY_NOT_MATCH("400","The requested data format does not match!"),
    SIGNATURE_NOT_MATCH("401","The requested digital signature does not match!"),
    NOT_FOUND("404", "Resource not found!"), 
    INTERNAL_SERVER_ERROR("500", "Server internal error!"),
    SERVER_BUSY("503","Server is busy. Please try later!")
    ;

    /** 错误码 */
    private String resultCode;

    /** 错误描述 */
    private String resultMsg;

    CommonEnum(String resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }

    @Override
    public String getResultCode() {
        return resultCode;
    }

    @Override
    public String getResultMsg() {
        return resultMsg;
    }

}
