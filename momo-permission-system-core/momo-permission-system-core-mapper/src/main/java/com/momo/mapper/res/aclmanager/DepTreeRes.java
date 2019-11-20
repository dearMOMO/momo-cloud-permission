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
