package com.momo.mapper.mapper;

import com.momo.mapper.dataobject.UserDO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 由于需要对分页支持,请直接使用对应的DAO类
 * The Table sys_user.
 * 用户
 */
public interface UserDOMapper{

    /**
     * desc:插入表:sys_user.<br/>
     * descSql =  SELECT LAST_INSERT_ID() <![CDATA[ INSERT INTO sys_user( ID ,GROUP_ID ,SYS_USER_AREA ,SYS_USER_CITY ,SYS_USER_PROVINCE ,UUID ,REMARK ,CREATE_BY ,UPDATE_BY ,SYS_USER_NAME ,SYS_USER_EMAIL ,SYS_USER_PHONE ,SYS_USER_AREA_NAME ,SYS_USER_CITY_NAME ,SYS_USER_PROVINCE_NAME ,FLAG ,DEL_FLAG ,CREATE_TIME ,UPDATE_TIME )VALUES( null , #{groupId,jdbcType=BIGINT} , #{sysUserArea,jdbcType=BIGINT} , #{sysUserCity,jdbcType=BIGINT} , #{sysUserProvince,jdbcType=BIGINT} , #{uuid,jdbcType=VARCHAR} , #{remark,jdbcType=VARCHAR} , #{createBy,jdbcType=VARCHAR} , #{updateBy,jdbcType=VARCHAR} , #{sysUserName,jdbcType=VARCHAR} , #{sysUserEmail,jdbcType=VARCHAR} , #{sysUserPhone,jdbcType=VARCHAR} , #{sysUserAreaName,jdbcType=VARCHAR} , #{sysUserCityName,jdbcType=VARCHAR} , #{sysUserProvinceName,jdbcType=VARCHAR} , #{flag,jdbcType=INTEGER} , #{delFlag,jdbcType=INTEGER} , #{createTime,jdbcType=TIMESTAMP} , #{updateTime,jdbcType=TIMESTAMP} ) ]]>
     * @param entity entity
     * @return int
     */
    int insert(UserDO entity);
    /**
     * desc:批量插入表:sys_user.<br/>
     * descSql =  INSERT INTO sys_user( ID ,GROUP_ID ,SYS_USER_AREA ,SYS_USER_CITY ,SYS_USER_PROVINCE ,UUID ,REMARK ,CREATE_BY ,UPDATE_BY ,SYS_USER_NAME ,SYS_USER_EMAIL ,SYS_USER_PHONE ,SYS_USER_AREA_NAME ,SYS_USER_CITY_NAME ,SYS_USER_PROVINCE_NAME ,FLAG ,DEL_FLAG ,CREATE_TIME ,UPDATE_TIME )VALUES ( null , #{item.groupId,jdbcType=BIGINT} , #{item.sysUserArea,jdbcType=BIGINT} , #{item.sysUserCity,jdbcType=BIGINT} , #{item.sysUserProvince,jdbcType=BIGINT} , #{item.uuid,jdbcType=VARCHAR} , #{item.remark,jdbcType=VARCHAR} , #{item.createBy,jdbcType=VARCHAR} , #{item.updateBy,jdbcType=VARCHAR} , #{item.sysUserName,jdbcType=VARCHAR} , #{item.sysUserEmail,jdbcType=VARCHAR} , #{item.sysUserPhone,jdbcType=VARCHAR} , #{item.sysUserAreaName,jdbcType=VARCHAR} , #{item.sysUserCityName,jdbcType=VARCHAR} , #{item.sysUserProvinceName,jdbcType=VARCHAR} , #{item.flag,jdbcType=INTEGER} , #{item.delFlag,jdbcType=INTEGER} , #{item.createTime,jdbcType=TIMESTAMP} , #{item.updateTime,jdbcType=TIMESTAMP} ) 
     * @param list list
     * @return int
     */
    int insertBatch(List<UserDO> list);
    /**
     * desc:根据主键删除数据:sys_user.<br/>
     * descSql =  <![CDATA[ DELETE FROM sys_user WHERE ID = #{id,jdbcType=BIGINT} ]]>
     * @param id id
     * @return int
     */
    int deleteById(Long id);
    /**
     * desc:根据主键获取数据:sys_user.<br/>
     * descSql =  SELECT * FROM sys_user WHERE <![CDATA[ ID = #{id,jdbcType=BIGINT} ]]>
     * @param id id
     * @return UserDO
     */
    UserDO getById(Long id);
    /**
     * desc:根据普通索引SysUserName获取数据:sys_user.<br/>
     * descSql =  SELECT * FROM sys_user WHERE <![CDATA[ SYS_USER_NAME = #{sysUserName,jdbcType=VARCHAR} ]]>
     * @param sysUserName sysUserName
     * @return List<UserDO>
     */
    List<UserDO> queryBySysUserName(@Param("sysUserName")String sysUserName);
    /**
     * desc:根据普通索引SysUserPhone获取数据:sys_user.<br/>
     * descSql =  SELECT * FROM sys_user WHERE <![CDATA[ SYS_USER_PHONE = #{sysUserPhone,jdbcType=VARCHAR} ]]>
     * @param sysUserPhone sysUserPhone
     * @return List<UserDO>
     */
    List<UserDO> queryBySysUserPhone(@Param("sysUserPhone")String sysUserPhone);
    /**
     * desc:根据普通索引SysUserUuid获取数据:sys_user.<br/>
     * descSql =  SELECT * FROM sys_user WHERE <![CDATA[ UUID = #{uuid,jdbcType=VARCHAR} ]]>
     * @param uuid uuid
     * @return List<UserDO>
     */
    List<UserDO> queryBySysUserUuid(@Param("uuid")String uuid);
}
