package com.momo.mapper.mapper;

import com.momo.mapper.dataobject.RoutesDO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 由于需要对分页支持,请直接使用对应的DAO类
 * The Table sys_routes.
 * SYS_ROUTES
 */
public interface RoutesDOMapper{

    /**
     * desc:插入表:sys_routes.<br/>
     * descSql =  SELECT LAST_INSERT_ID() <![CDATA[ INSERT INTO sys_routes( UUID ,SYS_ID ,REMARK ,SYS_URI ,SYS_NAME ,CREATE_BY ,UPDATE_BY ,SYS_FILTERS ,SYS_PREDICATES ,P_ID ,FLAG ,DEL_FLAG ,SYS_ORDER ,CREATE_TIME ,UPDATE_TIME )VALUES( #{uuid,jdbcType=VARCHAR} , #{sysId,jdbcType=VARCHAR} , #{remark,jdbcType=VARCHAR} , #{sysUri,jdbcType=VARCHAR} , #{sysName,jdbcType=VARCHAR} , #{createBy,jdbcType=VARCHAR} , #{updateBy,jdbcType=VARCHAR} , #{sysFilters,jdbcType=VARCHAR} , #{sysPredicates,jdbcType=VARCHAR} , #{pId,jdbcType=INTEGER} , #{flag,jdbcType=INTEGER} , #{delFlag,jdbcType=INTEGER} , #{sysOrder,jdbcType=INTEGER} , #{createTime,jdbcType=TIMESTAMP} , #{updateTime,jdbcType=TIMESTAMP} ) ]]>
     * @param entity entity
     * @return int
     */
    int insert(RoutesDO entity);
    /**
     * desc:批量插入表:sys_routes.<br/>
     * descSql =  INSERT INTO sys_routes( UUID ,SYS_ID ,REMARK ,SYS_URI ,SYS_NAME ,CREATE_BY ,UPDATE_BY ,SYS_FILTERS ,SYS_PREDICATES ,P_ID ,FLAG ,DEL_FLAG ,SYS_ORDER ,CREATE_TIME ,UPDATE_TIME )VALUES ( #{item.uuid,jdbcType=VARCHAR} , #{item.sysId,jdbcType=VARCHAR} , #{item.remark,jdbcType=VARCHAR} , #{item.sysUri,jdbcType=VARCHAR} , #{item.sysName,jdbcType=VARCHAR} , #{item.createBy,jdbcType=VARCHAR} , #{item.updateBy,jdbcType=VARCHAR} , #{item.sysFilters,jdbcType=VARCHAR} , #{item.sysPredicates,jdbcType=VARCHAR} , #{item.pId,jdbcType=INTEGER} , #{item.flag,jdbcType=INTEGER} , #{item.delFlag,jdbcType=INTEGER} , #{item.sysOrder,jdbcType=INTEGER} , #{item.createTime,jdbcType=TIMESTAMP} , #{item.updateTime,jdbcType=TIMESTAMP} ) 
     * @param list list
     * @return int
     */
    int insertBatch(List<RoutesDO> list);
    /**
     * desc:根据主键删除数据:sys_routes.<br/>
     * descSql =  <![CDATA[ DELETE FROM sys_routes WHERE P_ID = #{pId,jdbcType=INTEGER} ]]>
     * @param pId pId
     * @return int
     */
    int deleteByPId(Integer pId);
    /**
     * desc:根据主键获取数据:sys_routes.<br/>
     * descSql =  SELECT * FROM sys_routes WHERE <![CDATA[ P_ID = #{pId,jdbcType=INTEGER} ]]>
     * @param pId pId
     * @return RoutesDO
     */
    RoutesDO getByPId(Integer pId);
    /**
     * desc:根据唯一约束Id更新表:sys_routes.<br/>
     * descSql =  <![CDATA[ UPDATE sys_routes SET UUID = #{uuid,jdbcType=VARCHAR} ,REMARK = #{remark,jdbcType=VARCHAR} ,SYS_URI = #{sysUri,jdbcType=VARCHAR} ,SYS_NAME = #{sysName,jdbcType=VARCHAR} ,CREATE_BY = #{createBy,jdbcType=VARCHAR} ,UPDATE_BY = #{updateBy,jdbcType=VARCHAR} ,SYS_FILTERS = #{sysFilters,jdbcType=VARCHAR} ,SYS_PREDICATES = #{sysPredicates,jdbcType=VARCHAR} ,FLAG = #{flag,jdbcType=INTEGER} ,DEL_FLAG = #{delFlag,jdbcType=INTEGER} ,SYS_ORDER = #{sysOrder,jdbcType=INTEGER} ,CREATE_TIME = #{createTime,jdbcType=TIMESTAMP} ,UPDATE_TIME = #{updateTime,jdbcType=TIMESTAMP} WHERE SYS_ID = #{sysId,jdbcType=VARCHAR} ]]>
     * @param entity entity
     * @return int
     */
    int updateById(RoutesDO entity);
    /**
     * desc:根据唯一约束Id删除数据:sys_routes.<br/>
     * descSql =  <![CDATA[ DELETE FROM sys_routes WHERE SYS_ID = #{sysId,jdbcType=VARCHAR} ]]>
     * @param sysId sysId
     * @return int
     */
    int deleteById(@Param("sysId")String sysId);
    /**
     * desc:根据唯一约束Id获取数据:sys_routes.<br/>
     * descSql =  SELECT * FROM sys_routes WHERE <![CDATA[ SYS_ID = #{sysId,jdbcType=VARCHAR} ]]>
     * @param sysId sysId
     * @return RoutesDO
     */
    RoutesDO getById(@Param("sysId")String sysId);
    /**
     * desc:根据唯一约束SysName更新表:sys_routes.<br/>
     * descSql =  <![CDATA[ UPDATE sys_routes SET UUID = #{uuid,jdbcType=VARCHAR} ,SYS_ID = #{sysId,jdbcType=VARCHAR} ,REMARK = #{remark,jdbcType=VARCHAR} ,SYS_URI = #{sysUri,jdbcType=VARCHAR} ,CREATE_BY = #{createBy,jdbcType=VARCHAR} ,UPDATE_BY = #{updateBy,jdbcType=VARCHAR} ,SYS_FILTERS = #{sysFilters,jdbcType=VARCHAR} ,SYS_PREDICATES = #{sysPredicates,jdbcType=VARCHAR} ,FLAG = #{flag,jdbcType=INTEGER} ,DEL_FLAG = #{delFlag,jdbcType=INTEGER} ,SYS_ORDER = #{sysOrder,jdbcType=INTEGER} ,CREATE_TIME = #{createTime,jdbcType=TIMESTAMP} ,UPDATE_TIME = #{updateTime,jdbcType=TIMESTAMP} WHERE SYS_NAME = #{sysName,jdbcType=VARCHAR} ]]>
     * @param entity entity
     * @return int
     */
    int updateBySysName(RoutesDO entity);
    /**
     * desc:根据唯一约束SysName删除数据:sys_routes.<br/>
     * descSql =  <![CDATA[ DELETE FROM sys_routes WHERE SYS_NAME = #{sysName,jdbcType=VARCHAR} ]]>
     * @param sysName sysName
     * @return int
     */
    int deleteBySysName(@Param("sysName")String sysName);
    /**
     * desc:根据唯一约束SysName获取数据:sys_routes.<br/>
     * descSql =  SELECT * FROM sys_routes WHERE <![CDATA[ SYS_NAME = #{sysName,jdbcType=VARCHAR} ]]>
     * @param sysName sysName
     * @return RoutesDO
     */
    RoutesDO getBySysName(@Param("sysName")String sysName);
    /**
     * desc:根据唯一约束SysUri更新表:sys_routes.<br/>
     * descSql =  <![CDATA[ UPDATE sys_routes SET UUID = #{uuid,jdbcType=VARCHAR} ,SYS_ID = #{sysId,jdbcType=VARCHAR} ,REMARK = #{remark,jdbcType=VARCHAR} ,SYS_NAME = #{sysName,jdbcType=VARCHAR} ,CREATE_BY = #{createBy,jdbcType=VARCHAR} ,UPDATE_BY = #{updateBy,jdbcType=VARCHAR} ,SYS_FILTERS = #{sysFilters,jdbcType=VARCHAR} ,SYS_PREDICATES = #{sysPredicates,jdbcType=VARCHAR} ,FLAG = #{flag,jdbcType=INTEGER} ,DEL_FLAG = #{delFlag,jdbcType=INTEGER} ,SYS_ORDER = #{sysOrder,jdbcType=INTEGER} ,CREATE_TIME = #{createTime,jdbcType=TIMESTAMP} ,UPDATE_TIME = #{updateTime,jdbcType=TIMESTAMP} WHERE SYS_URI = #{sysUri,jdbcType=VARCHAR} ]]>
     * @param entity entity
     * @return int
     */
    int updateBySysUri(RoutesDO entity);
    /**
     * desc:根据唯一约束SysUri删除数据:sys_routes.<br/>
     * descSql =  <![CDATA[ DELETE FROM sys_routes WHERE SYS_URI = #{sysUri,jdbcType=VARCHAR} ]]>
     * @param sysUri sysUri
     * @return int
     */
    int deleteBySysUri(@Param("sysUri")String sysUri);
    /**
     * desc:根据唯一约束SysUri获取数据:sys_routes.<br/>
     * descSql =  SELECT * FROM sys_routes WHERE <![CDATA[ SYS_URI = #{sysUri,jdbcType=VARCHAR} ]]>
     * @param sysUri sysUri
     * @return RoutesDO
     */
    RoutesDO getBySysUri(@Param("sysUri")String sysUri);
}
