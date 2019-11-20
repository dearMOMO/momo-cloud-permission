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
package com.momo.common.core.error;




import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;


/**
 * @ClassName: ParamsUtil
 * @Author: Jie Li
 * @Date 2019-11-14 15:41
 * @Description: 参数校验工具类
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 **/
@Slf4j
public class ParamsUtil {

private static Logger logger= LoggerFactory.getLogger(ParamsUtil.class);
    /**
     * 当且仅当参数全部非空时,返回false
     *
     * @param paramsMap
     * @return
     */
    public static void hasEmptyParamMap(Map<String, Object> paramsMap) {
        for (String key : paramsMap.keySet()) {
            checkParam(key, paramsMap.get(key));
        }
    }

    private static void checkParam(String name, Object obj) {
        if (obj instanceof String) {
            checkString(String.valueOf(obj), name);
        } else {
            checkOther(obj, name);
        }
    }

    private static void checkString(String str, String name) {
        if (StringUtils.isEmpty(str)) {
            throw new BizException(ErrorEnum.ERROR_PARAM, name);
        }
    }

    public static void checkOther(Object obj, String name) {
        if (obj == null) {
            throw new BizException(ErrorEnum.ERROR_PARAM, name);
        }
    }

    /*public static void checkJsonArray(String json) {
        try {
            List list = (List) JSONArray.parse(json);
            if (list.size() == 0) {
                throw new BizException(ErrorEnum.ERROR_PARAM);
            }
        } catch (Exception e) {
            throw new BizException(ErrorEnum.ERROR_PARAM_FORMAT);
        }
    }*/

    /**
     * 当传递的参数为null或者不传递参数时,抛出参数异常
     * 当检测到参数为空时,抛出参数异常
     * 当且仅当参数全部非空时,返回false
     *
     * @param args
     * @return
     */
    public static boolean hasEmptyParam(Object... args) {
        if (args == null || args.length == 0) {
            logger.error("至少传递一个参数!");
            throw new BizException(ErrorEnum.ERROR_PARAM_EMPTY, "至少传递一个参数!");
        } else {
            for (Object arg : args) {
                if (arg instanceof String) {
                    String str = (String) arg;
                    if (StringUtils.isEmpty(str)) {
                        throw new BizException(ErrorEnum.ERROR_PARAM_EMPTY, str.concat("参数为空"));
                    }
                } else {
                    if (arg == null) {
                        throw new BizException(ErrorEnum.ERROR_PARAM_EMPTY, "参数为空");
                    }
                }
            }
        }
        return false;
    }

    /**
     * 检查list列表中element是否为空
     *
     * @param list
     * @param args 需检测的字段数组
     */
    public static void checkListElement(List<?> list, String... args) {
        if (list.size() == 0) {
            throw new BizException(ErrorEnum.ERROR_PARAM);
        } else {
            list.forEach(o ->
            {
                Class<?> clazz = o.getClass();
                for (String fieldName : args) {
                    try {
                        Field field = clazz.getDeclaredField(fieldName);
                        PropertyDescriptor pd = new PropertyDescriptor(field.getName(), clazz);
                        //获得get方法
                        Method getMethod = pd.getReadMethod();
                        //执行get方法返回一个Object
                        Object value = getMethod.invoke(o);
                        checkParam(field.getName(), value);
                    } catch (Exception e) {
                        throw new BizException(ErrorEnum.ERROR_PARAM_EMPTY, fieldName);
                    }
                }
            });
        }
    }

    /**
     * 检查list列表是否有元素
     *
     * @param list
     */
    public static void checkListHasElement(List<?> list) {
        if (list == null || list.size() == 0) {
            throw new BizException(ErrorEnum.ERROR_PARAM);
        }
    }

    /**
     * 检查数组是否有元素
     *
     * @param array
     */
    public static void longArrayHasEmptyElement(Long[]... array) {
        if (array == null) {
            throw new BizException(ErrorEnum.ERROR_PARAM);
        }

        for (Long[] subArray : array) {
            if (subArray == null || subArray.length == 0) {
                throw new BizException(ErrorEnum.ERROR_PARAM);
            }
        }
    }
}
