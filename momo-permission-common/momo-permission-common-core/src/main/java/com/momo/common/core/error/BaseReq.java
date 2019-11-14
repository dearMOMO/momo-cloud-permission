package com.momo.common.core.error;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: BaseReq
 * @Author: Jie Li
 * @Date 2019-11-14 16:03
 * @Description: TODO
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
