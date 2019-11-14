package com.momo.common.log.event;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;

/**
 * @ClassName: SysLogListener
 * @Author: Jie Li
 * @Date 2019-11-14 14:41
 * @Description: TODO
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 **/
@Slf4j
@AllArgsConstructor
public class SysLogListener {

    @Async
    @Order
    @EventListener(SysLogEvent.class)
    public void saveSysLog(SysLogEvent event) {
        SysLogDO sysLog = (SysLogDO) event.getSource();
        log.info("AOP logs  {}", JSONObject.toJSONString(sysLog));
    }
}
