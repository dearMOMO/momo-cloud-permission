package com.momo.common.core.error;

/**
 * 服务错误
 * @author jim
 * @date 16/10/12
 */
public class ServiceException extends RuntimeException {
    private Integer errorCode;
    private String errorMessage;

    public ServiceException(ErrorEnum errorEnum) {
        this.errorCode = errorEnum.getErrorCode();
        this.errorMessage = errorEnum.getErrorMessage();
    }

    public ServiceException(ErrorEnum errorEnum, String errorMessage) {
        this.errorCode = errorEnum.getErrorCode();
        this.errorMessage = errorMessage;
    }

    public ServiceException(Integer errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
