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
package com.momo.momopermissiongateway.exception;

import com.google.common.collect.Maps;
import com.momo.common.core.error.ErrorEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.DefaultErrorWebExceptionHandler;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.reactive.function.server.*;

import java.util.Map;

/**
 * @ClassName: JsonExceptionHandler
 * @Author: Jie Li
 * @Date 2019-11-20 15:10
 * @Description: 自定义异常处理
 * 异常时用JSON代替HTML异常信息
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 **/
@Slf4j
public class JsonExceptionHandler extends DefaultErrorWebExceptionHandler {

    public JsonExceptionHandler(ErrorAttributes errorAttributes, ResourceProperties resourceProperties,
                                ErrorProperties errorProperties, ApplicationContext applicationContext) {
        super(errorAttributes, resourceProperties, errorProperties, applicationContext);
    }

    /**
     * 获取异常属性
     */
    @Override
    protected Map<String, Object> getErrorAttributes(ServerRequest request, boolean includeStackTrace) {

        int status = 500;
        Throwable error = super.getError(request);
        error.getCause();
        error.getMessage();
        error.printStackTrace();
        log.error("网关错误：{}==={}===={}", error.getMessage(), this.buildMessage(request, error), error.getStackTrace());
        if (error instanceof org.springframework.cloud.gateway.support.NotFoundException) {
            status = 404;
            return response(status, "服务未被发现,正在努力寻找,请稍后。。。。");
        } else if (error instanceof org.springframework.web.server.ResponseStatusException) {
            status = 404;
            return response(status, "服务正在启动，请稍后。。。。");
        } else if (error instanceof java.net.ConnectException) {
            status = 404;
            return response(status, "服务连接超时，请稍后。。。。");
        } else if (error instanceof com.netflix.client.ClientException) {
            status = 404;
            return response(status, "服务正在上线，请稍后。。。。");
        } else if (error instanceof HttpRequestMethodNotSupportedException) {
            status = 404;
            return response(status, "请求类型不被允许" + error.getMessage());
        }
        return response(status, ErrorEnum.ERROR_NOT_FOUND.getErrorMessage());
    }

    /**
     * 指定响应处理方法为JSON处理的方法
     *
     * @param errorAttributes
     */
    @Override
    protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
        return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse);
    }

    /**
     * 根据code获取对应的HttpStatus
     *
     * @param errorAttributes
     */
    @Override
    protected HttpStatus getHttpStatus(Map<String, Object> errorAttributes) {
        int statusCode = (int) errorAttributes.get("status");
        return HttpStatus.valueOf(statusCode);
    }

    /**
     * 构建异常信息
     *
     * @param request
     * @param ex
     * @return
     */
    private String buildMessage(ServerRequest request, Throwable ex) {
        StringBuilder message = new StringBuilder("Failed to handle request [");
        message.append(request.methodName());
        message.append(" ");
        message.append(request.uri());
        message.append("]");
        if (ex != null) {
            message.append(": ");
            message.append(ex.getMessage());
        }
        return message.toString();
    }

    /**
     * 构建返回的JSON数据格式
     *
     * @param status       状态码
     * @param errorMessage 异常信息
     * @return
     */
    public static Map<String, Object> response(int status, String errorMessage) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("status", status);
        map.put("message", errorMessage);
        map.put("msg", errorMessage);
        map.put("data", errorMessage);
        return map;
    }

}
