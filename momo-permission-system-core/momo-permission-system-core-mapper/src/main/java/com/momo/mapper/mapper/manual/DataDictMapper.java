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
package com.momo.mapper.mapper.manual;

import com.momo.mapper.dataobject.DataDictDO;
import org.apache.ibatis.annotations.Param;

import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * @ClassName: DataDictMapper
 * @Author: Jie Li
 * @Date 2019-12-27 11:03
 * @Description: TODO
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 **/
public interface DataDictMapper {

    int insertSelective(DataDictDO record);

    DataDictDO selectByPrimaryKey(Long id);

    DataDictDO selectByCodeValue(@Param("sys_dict_code_value") String codeValue);

    int updateByPrimaryKeySelective(DataDictDO record);

    List<DataDictDO> dataDiceGetAll(@Param("delFlag") Integer delFlag, @Param("sysDictCodeParentValue") String parentValue, @Param("sysDictCodeParentId") String parentId);

    /**
     * 校验字典值是否唯一
     * @param codeValue
     * @param id
     * @return
     */
    int checkCodeValue(@Param("sys_dict_code_value") String codeValue, @Param("id") Long id);

    /**
     * 校验同一层级名称是否唯一
     * @param parentId
     * @param parentValue
     * @param codeName
     * @param id
     * @return
     */
    int checkSameLevelName(@Param("sys_dict_code_parent_id") Long parentId, @Param("sys_dict_code_parent_value") String parentValue, @Param("sys_dict_code_name") String codeName, @Param("id") Long id);

    /**
     * 根据字典值获取子数据
     * @param codeLevel
     * @return
     */
    List<DataDictDO> getChildDictListByLevel(@Param("sys_dict_code_level") String codeLevel);

    int batchUpdateLevel(@Param("dictList") List<DataDictDO> aclModuleDOS);

}
