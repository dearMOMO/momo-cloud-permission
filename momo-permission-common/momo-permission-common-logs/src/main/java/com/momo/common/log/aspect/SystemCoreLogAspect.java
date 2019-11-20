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
package com.momo.common.log.aspect;

import com.momo.common.core.util.SpringContextHolder;
import com.momo.common.log.annotation.SystemCoreLog;
import com.momo.common.log.event.SystemCoreLogDO;
import com.momo.common.log.event.SystemCoreLogEvent;
import com.momo.common.log.util.SystemCoreLogUtils;
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
public class SystemCoreLogAspect {

    @Around("@annotation(sysLog)")
    @SneakyThrows
    public Object around(ProceedingJoinPoint point, SystemCoreLog sysLog) {
        String strClassName = point.getTarget().getClass().getName();
        String strMethodName = point.getSignature().getName();
        log.info("[类名]:{},[方法]:{}", strClassName, strMethodName);

        SystemCoreLogDO logVo = SystemCoreLogUtils.getSysLog();
        logVo.setTitle(sysLog.description());
        // 发送异步日志事件
        Long startTime = System.currentTimeMillis();
        Object obj = point.proceed();
        Long endTime = System.currentTimeMillis();
        logVo.setTime(endTime - startTime);
        SpringContextHolder.publishEvent(new SystemCoreLogEvent(logVo));
        return obj;
    }

}
