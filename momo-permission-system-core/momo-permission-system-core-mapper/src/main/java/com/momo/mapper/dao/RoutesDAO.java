package com.momo.mapper.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.momo.mapper.dataobject.RoutesDO;
import java.util.List;
import com.momo.mapper.mapper.RoutesDOMapper;

/**
* The Table sys_routes.
* SYS_ROUTES
*/
@Repository
public class RoutesDAO{

    @Autowired
    private RoutesDOMapper routesDOMapper;

    /**
     * desc:插入表:sys_routes.<br/>
     * @param entity entity
     * @return int
     */
    public int insert(RoutesDO entity){
        return routesDOMapper.insert(entity);
    }
    /**
     * desc:批量插入表:sys_routes.<br/>
     * @param list list
     * @return int
     */
    public int insertBatch(List<RoutesDO> list){
        return routesDOMapper.insertBatch(list);
    }
    /**
     * desc:根据主键删除数据:sys_routes.<br/>
     * @param pId pId
     * @return int
     */
    public int deleteByPId(Integer pId){
        return routesDOMapper.deleteByPId(pId);
    }
    /**
     * desc:根据主键获取数据:sys_routes.<br/>
     * @param pId pId
     * @return RoutesDO
     */
    public RoutesDO getByPId(Integer pId){
        return routesDOMapper.getByPId(pId);
    }
    /**
     * desc:根据唯一约束Id更新表:sys_routes.<br/>
     * @param entity entity
     * @return int
     */
    public int updateById(RoutesDO entity){
        return routesDOMapper.updateById(entity);
    }
    /**
     * desc:根据唯一约束Id删除数据:sys_routes.<br/>
     * @param sysId sysId
     * @return int
     */
    public int deleteById(String sysId){
        return routesDOMapper.deleteById(sysId);
    }
    /**
     * desc:根据唯一约束Id获取数据:sys_routes.<br/>
     * @param sysId sysId
     * @return RoutesDO
     */
    public RoutesDO getById(String sysId){
        return routesDOMapper.getById(sysId);
    }
    /**
     * desc:根据唯一约束SysName更新表:sys_routes.<br/>
     * @param entity entity
     * @return int
     */
    public int updateBySysName(RoutesDO entity){
        return routesDOMapper.updateBySysName(entity);
    }
    /**
     * desc:根据唯一约束SysName删除数据:sys_routes.<br/>
     * @param sysName sysName
     * @return int
     */
    public int deleteBySysName(String sysName){
        return routesDOMapper.deleteBySysName(sysName);
    }
    /**
     * desc:根据唯一约束SysName获取数据:sys_routes.<br/>
     * @param sysName sysName
     * @return RoutesDO
     */
    public RoutesDO getBySysName(String sysName){
        return routesDOMapper.getBySysName(sysName);
    }
    /**
     * desc:根据唯一约束SysUri更新表:sys_routes.<br/>
     * @param entity entity
     * @return int
     */
    public int updateBySysUri(RoutesDO entity){
        return routesDOMapper.updateBySysUri(entity);
    }
    /**
     * desc:根据唯一约束SysUri删除数据:sys_routes.<br/>
     * @param sysUri sysUri
     * @return int
     */
    public int deleteBySysUri(String sysUri){
        return routesDOMapper.deleteBySysUri(sysUri);
    }
    /**
     * desc:根据唯一约束SysUri获取数据:sys_routes.<br/>
     * @param sysUri sysUri
     * @return RoutesDO
     */
    public RoutesDO getBySysUri(String sysUri){
        return routesDOMapper.getBySysUri(sysUri);
    }
}
