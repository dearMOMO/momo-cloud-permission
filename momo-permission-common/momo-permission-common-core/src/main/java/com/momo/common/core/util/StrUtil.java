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
package com.momo.common.core.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.UUID;


/**
 * @ClassName: StrUtil
 * @Author: Jie Li
 * @Date 2019-11-20 14:54
 * @Description: uuid工具类
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 **/
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
        System.out.println(getProviderUUId());
        for (int i=0;i<100;i++){
            System.out.println(genUUID());
        }

    }
}
