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
package com.momo.momopermissioneureka.listener;

import com.netflix.appinfo.InstanceInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.eureka.server.event.*;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName: EurekaStateChangeListener
 * @Author: Jie Li
 * @Date 2019-11-20 15:01
 * @Description: Eureka事件监听
 * 上面只是演示事件的效果，具体在什么事件中需要做什么操作，需要发邮件还是发短信，需要大家自己去实现
 * 注意：在Eureka集群环境下，每个节点都会触发事件，这个时候需要控制下发送通知的行为，不控制的话每个节点都会发送通知。
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 **/
@Component
@Slf4j
public class EurekaStateChangeListener {
    //服务下线事件
    @EventListener
    public void listen(EurekaInstanceCanceledEvent event) {
        log.error(event.getServerId() + "\t" + event.getAppName() + " 服务下线");
    }

    //服务注册事件
    @EventListener
    public void listen(EurekaInstanceRegisteredEvent event) {
        InstanceInfo instanceInfo = event.getInstanceInfo();
        log.error(instanceInfo.getAppName() + "进行注册");
    }

    //服务续约事件
    @EventListener
    public void listen(EurekaInstanceRenewedEvent event) {
        log.error(event.getServerId() + "\t" + event.getAppName() + " 服务进行续约");
    }

    //Eureka注册中心启动事件
    @EventListener
    public void listen(EurekaRegistryAvailableEvent event) {
        log.error("注册中心 启动");
    }

    //Eureka Server启动事件
    @EventListener
    public void listen(EurekaServerStartedEvent event) {
        log.error("Eureka Server 启动");
    }
}
