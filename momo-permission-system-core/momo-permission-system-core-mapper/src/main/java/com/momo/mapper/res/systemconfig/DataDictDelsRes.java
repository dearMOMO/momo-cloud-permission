package com.momo.mapper.res.systemconfig;

import com.momo.mapper.dataobject.DataDictDelsDO;
import lombok.*;

/**
 * @ClassName: DataDictDelsRes
 * @Author: Jie Li
 * @Date 2019-12-28 17:23
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
public class DataDictDelsRes extends DataDictDelsDO {
    private String idStr;
}
