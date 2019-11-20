/**
 * Copyright (c) 2018-2019, Jie Li 李杰 (mqgnsds@163.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.momo.common.core.error;

/**
 * @ClassName: ErrorEnum
 * @Author: Jie Li
 * @Date 2019-11-14 15:41
 * @Description: 错误码
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 **/
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
    ERROR_BIZ_FAIL_HANDLER(10000, -1, "业务失败"),
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
