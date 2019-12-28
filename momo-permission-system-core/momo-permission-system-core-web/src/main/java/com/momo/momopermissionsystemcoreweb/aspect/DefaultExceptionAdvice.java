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
package com.momo.momopermissionsystemcoreweb.aspect;

import com.momo.common.core.common.JSONResult;
import com.momo.common.core.error.BizException;
import com.momo.common.core.error.ErrorEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.List;


/**
 * @ClassName: a
 * @Author: Jie Li
 * @Date 2019-09-19 15:05
 * @Description: 异常通用处理
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 **/
@ResponseBody
@Slf4j
@ControllerAdvice
public class DefaultExceptionAdvice {
    /**
     * IllegalArgumentException异常处理返回json
     * 返回状态码:400
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({IllegalArgumentException.class})
    public JSONResult badRequestException(IllegalArgumentException e) {
        return defHandler(null, "参数解析失败", e);
    }

    /**
     * IllegalArgumentException异常处理返回json
     * 返回状态码:400
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({HttpMessageNotReadableException.class})
    public JSONResult httpMessageNotReadableException(HttpMessageNotReadableException e) {
        return defHandler(null, "参数解析失败", e);
    }

    /**
     * IllegalArgumentException异常处理返回json
     * 返回状态码:500
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public JSONResult argumentNotValidException(MethodArgumentNotValidException e) {
        List<FieldError> bindingResult = e.getBindingResult().getFieldErrors();
        StringBuilder sb = new StringBuilder();
        //请求参数错误
        sb.append(ErrorEnum.ERROR_PARAM.getErrorMessage()).append(" --> ");
        for (FieldError fieldError : bindingResult) {
            sb.append(fieldError.getField());
            sb.append(" : ");
            sb.append(fieldError.getDefaultMessage());
            sb.append(" ; ");
        }

        return defHandler(null, sb.toString(), e);
    }

    /**
     * illegalStateException异常处理返回json
     * 返回状态码:500
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({IllegalStateException.class})
    public JSONResult illegalStateException(IllegalStateException e) {
        return defHandler(null, "illegalStateException", e);
    }

    /**
     * 返回状态码:405
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public JSONResult handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        return defHandler(null, "不支持当前请求方法", e);
    }

    /**
     * 返回状态码:415
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({HttpMediaTypeNotSupportedException.class})
    public JSONResult handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
        return defHandler(null, "不支持当前媒体类型", e);
    }

    /**
     * SQLException sql异常处理
     * 返回状态码:500
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({SQLException.class})
    public JSONResult handleSQLException(SQLException e) {
        return defHandler(null, "服务运行SQLException异常", e);
    }

    /**
     * SQLException sql异常处理
     * 返回状态码:500
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({SQLSyntaxErrorException.class})
    public JSONResult sQLSyntaxErrorException(SQLException e) {
        return defHandler(null, "SQL 参数异常", e);
    }

    /**
     * BusinessException 业务异常处理
     * 返回状态码:500
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(BizException.class)
    public JSONResult handleException(BizException e) {
        return defHandler(null, e.getMessage(), e);
    }

    /**
     * BusinessException 没有找到对应的访问路径
     * 返回状态码:500
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(NoHandlerFoundException.class)
    public JSONResult noHandlerFoundException(BizException e) {
        return defHandler(null, "没有找到对应的访问路径", e);
    }


    /**
     * 所有异常统一处理
     * 返回状态码:500
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(Exception.class)
    public JSONResult handleException(Exception e) {
        return defHandler(null, "服务异常", e);
    }

    private JSONResult defHandler(Integer status, String msg, Exception e) {
        log.error(msg, e.getMessage(), e);
        return JSONResult.errorMsg(status, msg);
    }
}
