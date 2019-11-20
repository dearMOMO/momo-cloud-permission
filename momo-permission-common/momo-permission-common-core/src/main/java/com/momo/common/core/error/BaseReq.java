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

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: BaseReq
 * @Author: Jie Li
 * @Date 2019-11-14 16:03
 * @Description: 参数校验以及分页基类
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 **/
@Data
public class BaseReq  {


    //当前页
    private int pageNum = 1;
    //每页的数量
    private int pageSize = 20;


    /**
     * 检查参数是否合法
     *
     * @return
     */
    public void checkData() {
        Map<String, Object> checkParam = new HashMap<>();
        ParamsUtil.hasEmptyParamMap(checkParam);
    }

    public void trimData() {

    }

    public interface Add {

    }

    public interface save {

    }

    public interface page {

    }

    public interface select {

    }

    public interface Modify {

    }

    public interface Delete {

    }

    public interface Query {

    }

    public interface Status {

    }

    public interface Detail {

    }

    public interface Submit {

    }

    public interface Permission {

    }

    public interface Author {

    }
}
