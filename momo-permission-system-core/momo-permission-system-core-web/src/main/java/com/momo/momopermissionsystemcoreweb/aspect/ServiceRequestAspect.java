package com.momo.momopermissionsystemcoreweb.aspect;

import com.alibaba.fastjson.JSONObject;
import com.momo.common.common.JSONResult;
import com.momo.common.error.BaseReq;
import com.momo.common.error.BizException;
import com.momo.common.error.ErrorEnum;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Random;

/**
 * 服务请求拦截
 *
 * @author jim
 * @date 16/10/12
 */
@Aspect
@Component
@Slf4j
public class ServiceRequestAspect {
private final static Logger LOGGER=LoggerFactory.getLogger(ServiceRequestAspect.class);
    //    @Pointcut("execution(public * com.momo.netty.web.*ontroller.*.*(..))")
    @Pointcut("execution(public * com.momo.*.*(..))")
    private void allMethod() {
    }

    @Around("allMethod()")
    public Object doAround(ProceedingJoinPoint call) throws Throwable {
        if (call.getArgs().length == 0) {
            return call.proceed();
        }

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String reqParams = JSONObject.toJSONString(call.getArgs());
        String api = request.getRequestURI();

        String reqId = getRandom(10);
        String ip = getIpAddress(request);
        log.info("[" + reqId + ",REQUEST]" + api + "," + ip + "[" + reqParams + "]");
        Date startDate = new Date();
        boolean requestResult = false;


        try {
            log.info("" + call.getArgs());
            BaseReq req = (BaseReq) call.getArgs()[0];
            //2.请求参数验证
            req.checkData();
            //3.请求处理
            Object object = call.proceed();
            log.info("[" + reqId + ",RESPONSE]" + JSONObject.toJSONString(object));
            requestResult = true;
            return object;
        } catch (BizException e) {
            log.error(e.getMessage(),e);
            return JSONResult.errorException(e.getError().getErrorCode(), e.getError().getErrorMessage() + ": " + e.getMessage(), "");
        } catch (Exception e) {
            log.error("服务请求处理异常", e);
            return JSONResult.errorException(ErrorEnum.SYSTEM_ERROR.getErrorMessage());
        } finally {
            Date endDate = new Date();
            long runTime = endDate.getTime() - startDate.getTime();
            String log = String.format("[%s]PERFORMANCE:%s,%s,%sms", reqId, api, requestResult, runTime);
            LOGGER.info(log);
        }
    }

    private <T extends Annotation> T getAnnotation(ProceedingJoinPoint jp, Class<T> clazz) {
        MethodSignature joinPointObject = (MethodSignature) jp.getSignature();
        Method method = joinPointObject.getMethod();
        return method.getAnnotation(clazz);
    }

    private static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    private static Random random = new Random();

    private static String getRandom(int length) {
        StringBuilder ret = new StringBuilder();

        for (int i = 0; i < length; ++i) {
            boolean isChar = random.nextInt(2) % 2 == 0;
            if (isChar) {
                int choice = random.nextInt(2) % 2 == 0 ? 65 : 97;
                ret.append((char) (choice + random.nextInt(26)));
            } else {
                ret.append(Integer.toString(random.nextInt(10)));
            }
        }

        return ret.toString();
    }
}
