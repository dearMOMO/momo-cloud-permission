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
package com.momo.momopermissionsystemcoreweb.controller.aclmanager;

import com.momo.common.core.common.JSONResult;
import com.momo.service.service.aclmanager.SysDeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName: SysDeptController
 * @Author: Jie Li
 * @Date 2019-11-15 16:47
 * @Description: 部门相关
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 **/
@RestController
@RequestMapping(value = "/platform/dept", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
@Slf4j
public class SysDeptController {

    @Autowired
    private SysDeptService sysDeptService;

    /**
     * 部门树
     *
     * @return
     */
    @PostMapping("/deptTree/v1")
    public JSONResult sysUserList() {
        return JSONResult.ok(sysDeptService.deptTree());
    }
}
