package com.momo.netty.utils;

import com.momo.common.core.common.UserChannelCurrentMap;
import lombok.*;

/**
 * @ClassName: MOMORespnse
 * @Author: Jie Li
 * @Date 2019-09-21 21:52
 * @Description: TODO
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 **/
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@EqualsAndHashCode(callSuper = true, of = {"id"})
public class MOMONettyResponse {
    //标题
    private String title = "基于最流行RBAC拓展模型，打造分布式权限管理系统";
    //文档地址
    private String document = "http://www.mqgnsds.top:9527";
    //源码
    private String gitee = "https://gitee.com/momoriven/momo-cloud-permission";
    //长链接地址
    private String websocketAddress = "ws://www.mqgnsds.top:502/ws";
    //在线数量
    private Integer onlineNum = UserChannelCurrentMap.managerChannelSize();

    public static MOMONettyResponse momoRespnse(){
        return new MOMONettyResponse();
    }
}
