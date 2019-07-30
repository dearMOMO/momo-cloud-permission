package com.momo.momopermissiongateway.exception;

/**
 * 错误码
 * @author jim
 * @date 16/10/12
 */
public enum ErrorEnum {
    /**
     * 错误码分内外两种
     * 对内使用最细粒度错误吗，对外使用统一错误码
     * 对外统一使用本类型第一个错误码。
     */
    //1开头返回接收细节错误码,其它返回父错误码
    RES_CODE_SYS_INVALID_TICKET(2, 2, "用户身份过期"),
    ERROR_HAS_NO_ACL_FAIL(2,402,"您无权限访问系统资源"),
    SYSTEM_ERROR(10000, 10000, "系统错误"),
    ERROR_BIZ_FAIL(10000, 10001, "业务失败"),
    ERROR_BIZ_UNIQUE_REQ_ID(10000, 10002, "唯一性约束重复"),
    SERVICE_ERROR(10000, 90001, "服务错误"),
    ERROR_NOT_FOUND(10000,500,"网关服务器资源繁忙，请稍后重试!"),

    //2开头为参数校验信息错误
    ERROR_PARAM(20000, 20000, "参数错误"),
    ERROR_PARAM_EMPTY(20000, 20001, "参数为空"),
    ERROR_PARAM_FORMAT(20000, 20002, "参数格式不正确"),
    ERROR_PARAM_KEY_NOT_EXIST(20000, 20003, "参数不存在"),;

    private final int errorCode;
    private final int parentCode;
    private final String errorMessage;

    ErrorEnum(int parentCode, int errorCode, String errorMessage) {
        this.parentCode = parentCode;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public int getParentCode() {
        if (String.valueOf(errorCode).startsWith("1")) {
            return errorCode;
        }

        return parentCode;
    }

    public ErrorEnum getOutError() {
        return getErrorByCode(getParentCode());
    }

    public static ErrorEnum getErrorByCode(int code) {
        for (ErrorEnum errorEnum : values()) {
            if (errorEnum.getErrorCode() == code) {
                return errorEnum;
            }
        }
        return null;
    }
}
