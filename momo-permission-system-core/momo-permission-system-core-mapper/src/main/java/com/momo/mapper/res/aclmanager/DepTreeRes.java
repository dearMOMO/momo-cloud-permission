package com.momo.mapper.res.aclmanager;

import com.momo.mapper.dataobject.DeptDO;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * @ClassName: DepTreeRes
 * @Author: Jie Li
 * @Date 2019-11-15 16:37
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
public class DepTreeRes extends DeptDO {
    private String idStr;
    private List<DepTreeRes> depTreeResList;

    public static DepTreeRes depTreeRes(DeptDO deptDO) {
        DepTreeRes depTreeRes = new DepTreeRes();
        BeanUtils.copyProperties(deptDO, depTreeRes);
        depTreeRes.setIdStr(deptDO.getId().toString());
        return depTreeRes;
    }
}
