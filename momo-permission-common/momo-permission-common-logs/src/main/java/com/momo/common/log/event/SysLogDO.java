package com.momo.common.log.event;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * @ClassName: SysLogDO
 * @Author: Jie Li
 * @Date 2019-11-14 14:39
 * @Description: 日志
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 **/
@Data
public class SysLogDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    private Long id;
    /**
     * 日志类型
     */
    private String type;
    /**
     * 日志标题
     */
    private String title;
    /**
     * 创建者
     */
    private String createBy;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
    /**
     * 操作IP地址
     */
    private String remoteAddr;
    /**
     * 用户代理
     */
    private String userAgent;
    /**
     * 请求URI
     */
    private String requestUri;
    /**
     * 操作方式
     */
    private String method;
    /**
     * 操作提交的数据
     */
    private String params;
    /**
     * 执行时间
     */
    private Long time;

    /**
     * 异常信息
     */
    private String exception;

    /**
     * 服务ID
     */
    private String serviceId;

    /**
     * 删除标记
     */
    private String delFlag;


}
