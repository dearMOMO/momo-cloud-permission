package com.momo.mapper.mapper;

import com.momo.mapper.dataobject.RoutesDO;
import org.apache.ibatis.annotations.Param;

/**
 * 由于需要对分页支持,请直接使用对应的DAO类
 * 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
 * The Table SYS_ROUTES.
 * SYS_ROUTES
 */
public interface RoutesDOMapper{

    /**
     * desc:插入表:SYS_ROUTES.<br/>
     * descSql =  SELECT LAST_INSERT_ID() INSERT INTO SYS_ROUTES( SYS_ID SYS_URI ,SYS_NAME ,SYS_FILTERS ,SYS_PREDICATES ,P_ID ,SYS_ORDER )VALUES( #{sysId,jdbcType=VARCHAR} #{sysUri,jdbcType=VARCHAR} ,#{sysName,jdbcType=VARCHAR} ,#{sysFilters,jdbcType=VARCHAR} ,#{sysPredicates,jdbcType=VARCHAR} ,#{pId,jdbcType=INTEGER} ,#{sysOrder,jdbcType=INTEGER} )
     * @param entity entity
     * @return int
     */
    int insert(RoutesDO entity);
    /**
     * desc:更新表:SYS_ROUTES.<br/>
     * descSql =  UPDATE SYS_ROUTES SET SYS_ID = #{sysId,jdbcType=VARCHAR} ,SYS_URI = #{sysUri,jdbcType=VARCHAR} ,SYS_NAME = #{sysName,jdbcType=VARCHAR} ,SYS_FILTERS = #{sysFilters,jdbcType=VARCHAR} ,SYS_PREDICATES = #{sysPredicates,jdbcType=VARCHAR} ,SYS_ORDER = #{sysOrder,jdbcType=INTEGER} WHERE P_ID = #{pId,jdbcType=INTEGER}
     * @param entity entity
     * @return int
     */
    int update(RoutesDO entity);
    /**
     * desc:根据主键删除数据:SYS_ROUTES.<br/>
     * descSql =  DELETE FROM SYS_ROUTES WHERE P_ID = #{pId,jdbcType=INTEGER}
     * @param pId pId
     * @return int
     */
    int deleteByPId(Integer pId);
    /**
     * desc:根据主键获取数据:SYS_ROUTES.<br/>
     * descSql =  SELECT * FROM SYS_ROUTES WHERE P_ID = #{pId,jdbcType=INTEGER}
     * @param pId pId
     * @return RoutesDO
     */
    RoutesDO getByPId(Integer pId);
}
