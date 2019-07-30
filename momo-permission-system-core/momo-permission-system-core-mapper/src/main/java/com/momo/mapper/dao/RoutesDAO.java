package com.momo.mapper.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.momo.mapper.dataobject.RoutesDO;
import com.momo.mapper.mapper.RoutesDOMapper;

/**
* The Table SYS_ROUTES.
* 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
* SYS_ROUTES
*/
@Repository
public class RoutesDAO{

    @Autowired
    private RoutesDOMapper routesDOMapper;

    /**
     * desc:插入表:SYS_ROUTES.<br/>
     * descSql =  SELECT LAST_INSERT_ID() INSERT INTO SYS_ROUTES( SYS_ID SYS_URI ,SYS_NAME ,SYS_FILTERS ,SYS_PREDICATES ,P_ID ,SYS_ORDER )VALUES( #{sysId,jdbcType=VARCHAR} #{sysUri,jdbcType=VARCHAR} ,#{sysName,jdbcType=VARCHAR} ,#{sysFilters,jdbcType=VARCHAR} ,#{sysPredicates,jdbcType=VARCHAR} ,#{pId,jdbcType=INTEGER} ,#{sysOrder,jdbcType=INTEGER} )
     * @param entity entity
     * @return int
     */
    public int insert(RoutesDO entity){
        return routesDOMapper.insert(entity);
    }
    /**
     * desc:更新表:SYS_ROUTES.<br/>
     * descSql =  UPDATE SYS_ROUTES SET SYS_ID = #{sysId,jdbcType=VARCHAR} ,SYS_URI = #{sysUri,jdbcType=VARCHAR} ,SYS_NAME = #{sysName,jdbcType=VARCHAR} ,SYS_FILTERS = #{sysFilters,jdbcType=VARCHAR} ,SYS_PREDICATES = #{sysPredicates,jdbcType=VARCHAR} ,SYS_ORDER = #{sysOrder,jdbcType=INTEGER} WHERE P_ID = #{pId,jdbcType=INTEGER}
     * @param entity entity
     * @return int
     */
    public int update(RoutesDO entity){
        return routesDOMapper.update(entity);
    }
    /**
     * desc:根据主键删除数据:SYS_ROUTES.<br/>
     * descSql =  DELETE FROM SYS_ROUTES WHERE P_ID = #{pId,jdbcType=INTEGER}
     * @param pId pId
     * @return int
     */
    public int deleteByPId(Integer pId){
        return routesDOMapper.deleteByPId(pId);
    }
    /**
     * desc:根据主键获取数据:SYS_ROUTES.<br/>
     * descSql =  SELECT * FROM SYS_ROUTES WHERE P_ID = #{pId,jdbcType=INTEGER}
     * @param pId pId
     * @return RoutesDO
     */
    public RoutesDO getByPId(Integer pId){
        return routesDOMapper.getByPId(pId);
    }
}
