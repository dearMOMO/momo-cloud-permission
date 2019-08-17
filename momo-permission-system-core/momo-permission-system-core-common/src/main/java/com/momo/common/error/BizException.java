package com.momo.common.error;

import lombok.*;

/**
 * 业务错误
 *
 * @author jim
 * @date 16/10/12
 */
@Getter
@Setter
@ToString
@Builder
//@EqualsAndHashCode(of = {"id"})
public class BizException extends RuntimeException {
    private ErrorEnum error = null;
    private String message;

    public BizException() {

    }

    public BizException(String message) {
        this.message = message;
    }

    public BizException(ErrorEnum error) {
        this.error = error;
        this.message = error.getErrorMessage();
    }

    public BizException(ErrorEnum error, String message) {
        this.error = error;
        this.message = message;
    }

    public static BizException fail(String message) {
        return BizException.builder().message(message).error(ErrorEnum.ERROR_BIZ_FAIL).build();
    }

    public static BizException failHandler(String message) {
        return BizException.builder().message(message).error(ErrorEnum.ERROR_BIZ_FAIL_HANDLER).build();
    }

    public ErrorEnum getError() {
        return error;
    }

    public void setError(ErrorEnum error) {
        this.error = error;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
