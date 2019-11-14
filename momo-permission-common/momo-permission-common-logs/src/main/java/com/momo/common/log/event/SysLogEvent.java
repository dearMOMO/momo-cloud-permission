package com.momo.common.log.event;

import org.springframework.context.ApplicationEvent;

/**
 * @ClassName: SysLogEvent
 * @Author: Jie Li
 * @Date 2019-11-14 14:40
 * @Description: 系统日志事件
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 **/
public class SysLogEvent extends ApplicationEvent {

    public SysLogEvent(SysLogDO source) {
        super(source);
    }
}
