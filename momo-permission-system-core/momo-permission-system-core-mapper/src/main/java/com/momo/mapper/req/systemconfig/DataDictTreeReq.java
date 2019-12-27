package com.momo.mapper.req.systemconfig;

import lombok.*;

/**
 * @ClassName: DataDictTreeReq
 * @Author: Jie Li
 * @Date 2019-12-27 11:24
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
public class DataDictTreeReq {
    //父级id
    private Long sysDictCodeParentId;
    //父级参数值
    private String sysDictCodeParentValue;
}
