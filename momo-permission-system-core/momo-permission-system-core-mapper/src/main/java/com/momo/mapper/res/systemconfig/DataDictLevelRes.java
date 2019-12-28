package com.momo.mapper.res.systemconfig;

import lombok.*;

import java.util.List;

/**
 * @ClassName: DataDictLevelRes
 * @Author: Jie Li
 * @Date 2019-12-27 14:06
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
//@EqualsAndHashCode(of = {"id"})
public class DataDictLevelRes {
    private List<DataDictTreeRes> dataDictTreeRes;
    //默认展开
    private List<String> defaultexpandedKeys;
    //默认选中
    private List<String> defaultCheckedKeys;
}
