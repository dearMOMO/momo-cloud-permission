package com.momo.service.async;

import com.alibaba.fastjson.JSONObject;
import com.momo.common.util.DateUtils;
import com.momo.common.common.UserAgentGetter;
import com.momo.mapper.dataobject.LoginLogDO;
import com.momo.mapper.mapper.manual.LoginLogMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 李杰 on 2019/2/10.
 */
@Service
@Slf4j
public class LoginLogAsync {
    @Autowired
    private LoginLogMapper loginLogMapper;

    @Async("threadPoolTaskExecutor")
    public void loginLog(LoginLogDO entity, HttpServletRequest request){
        UserAgentGetter userAgentGetter = new UserAgentGetter(request);
        entity.setCreateTime(DateUtils.getDateTime());
        entity.setUserIp(userAgentGetter.getIpAddr());
        entity.setUserLoginDevice(userAgentGetter.getDevice());
        entity.setUserLoginSys(userAgentGetter.getOS());
        entity.setUserLoginBrowser(userAgentGetter.getBrowser());
        log.info("登录日志记录:{}", JSONObject.toJSONString(entity));
        loginLogMapper.insertSelective(entity);
    }
}

