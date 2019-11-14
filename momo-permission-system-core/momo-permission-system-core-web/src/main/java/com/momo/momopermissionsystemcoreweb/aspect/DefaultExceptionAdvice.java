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
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({IllegalArgumentException.class})
    public JSONResult badRequestException(IllegalArgumentException e) {
        return defHandler(null, "参数解析失败", e);
    }

    /**
     * IllegalArgumentException异常处理返回json
     * 返回状态码:400
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({HttpMessageNotReadableException.class})
    public JSONResult httpMessageNotReadableException(HttpMessageNotReadableException e) {
        return defHandler(null, "参数解析失败", e);
    }

    /**
     * IllegalArgumentException异常处理返回json
     * 返回状态码:500
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public JSONResult argumentNotValidException(MethodArgumentNotValidException e) {
        List<FieldError> bindingResult = e.getBindingResult().getFieldErrors();
        StringBuilder sb = new StringBuilder();
        //请求参数错误
        sb.append(ErrorEnum.ERROR_PARAM.getErrorMessage());
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
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({IllegalStateException.class})
    public JSONResult illegalStateException(IllegalStateException e) {
        return defHandler(null, "illegalStateException", e);
    }

    /**
     * 返回状态码:405
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public JSONResult handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        return defHandler(null, "不支持当前请求方法", e);
    }

    /**
     * 返回状态码:415
     */
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler({HttpMediaTypeNotSupportedException.class})
    public JSONResult handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
        return defHandler(null, "不支持当前媒体类型", e);
    }

    /**
     * SQLException sql异常处理
     * 返回状态码:500
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({SQLException.class})
    public JSONResult handleSQLException(SQLException e) {
        return defHandler(null, "服务运行SQLException异常", e);
    }

    /**
     * BusinessException 业务异常处理
     * 返回状态码:500
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(BizException.class)
    public JSONResult handleException(BizException e) {
        return defHandler(null, e.getMessage(), e);
    }

    /**
     * BusinessException 没有找到对应的访问路径
     * 返回状态码:500
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(NoHandlerFoundException.class)
    public JSONResult noHandlerFoundException(BizException e) {
        return defHandler(null, "没有找到对应的访问路径", e);
    }


    /**
     * 所有异常统一处理
     * 返回状态码:500
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public JSONResult handleException(Exception e) {
        return defHandler(null, "服务异常", e);
    }

    private JSONResult defHandler(Integer status, String msg, Exception e) {
        log.error(msg, e.getMessage(), e);
        return JSONResult.errorMsg(status, msg);
    }
}
