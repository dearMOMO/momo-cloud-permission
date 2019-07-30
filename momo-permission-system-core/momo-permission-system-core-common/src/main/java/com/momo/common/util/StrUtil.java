package com.momo.common.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.UUID;

/**
 *
 */
public class StrUtil {
    private final static Log LOGGER = LogFactory.getLog(StrUtil.class);


    /**
     * 生成UUID主键
     *
     * @return
     */
    public static String genUUID() {

        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static String genUUIDsubstring() {

        return UUID.randomUUID().toString().replaceAll("-", "").substring(16);
    }

    public static String setNoNull(Object o) {
        if (o == null)
            return "";
        return o.toString();
    }

    public static String getProviderUUId() {
        int machineId = 1;//最大支持1-9个集群机器部署
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        if(hashCodeV < 0) {//有可能是负数
            hashCodeV = - hashCodeV;
        }
        // 0 代表前面补充0
        // 4 代表长度为4
        // d 代表参数为正数型
        return machineId + String.format("%010d", hashCodeV);
    }

    public static void main(String[] args) {
        for (int i=0;i<100;i++){
            System.out.println(genUUID());
        }

    }
}
