package com.momo.mapper.res.systemconfig;

import com.google.common.collect.Lists;
import com.momo.mapper.dataobject.DataDictDO;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * @ClassName: DataDictTreeRes
 * @Author: Jie Li
 * @Date 2019-12-25 16:15
 * @Description: 数据字典树型
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 **/
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@EqualsAndHashCode(of = {"id"})
public class DataDictTreeRes extends DataDictDO {
    private String label;
    private String idStr;
    private String sysAclParentIdStr;

    private List<DataDictTreeRes> children = Lists.newArrayList();
    //默认选中/展开
    private List<String> defaultexpandedKeys;
    // 是否要默认选中
    private boolean checked = false;

    // 是否有权限操作
    private boolean hasAcl = false;
    // 是否有权限操作
    private boolean disabled = true;

    public static DataDictTreeRes dictTreeRes(DataDictDO dataDictDO) {
        DataDictTreeRes dataDictTreeRes = new DataDictTreeRes();
        BeanUtils.copyProperties(dataDictDO, dataDictTreeRes);
        dataDictTreeRes.setIdStr(dataDictDO.getId().toString());
        return dataDictTreeRes;
    }
}
