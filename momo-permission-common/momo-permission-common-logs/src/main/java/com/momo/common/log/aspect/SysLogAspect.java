package com.momo.common.log.aspect;

import com.momo.common.core.util.SpringContextHolder;
import com.momo.common.log.annotation.SysLog;
import com.momo.common.log.event.SysLogDO;
import com.momo.common.log.event.SysLogEvent;
import com.momo.common.log.util.SysLogUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * @ClassName: SysLogAspect
 * @Author: Jie Li
 * @Date 2019-11-14 14:03
 * @Description: 日志切面
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 **/
@Aspect
@Slf4j
public class SysLogAspect {

    @Around("@annotation(sysLog)")
    @SneakyThrows
    public Object around(ProceedingJoinPoint point, SysLog sysLog) {
        String strClassName = point.getTarget().getClass().getName();
        String strMethodName = point.getSignature().getName();
        log.info("[类名]:{},[方法]:{}", strClassName, strMethodName);

        SysLogDO logVo = SysLogUtils.getSysLog();
        logVo.setTitle(sysLog.description());
        // 发送异步日志事件
        Long startTime = System.currentTimeMillis();
        Object obj = point.proceed();
        Long endTime = System.currentTimeMillis();
        logVo.setTime(endTime - startTime);
        SpringContextHolder.publishEvent(new SysLogEvent(logVo));
        return obj;
    }

}
