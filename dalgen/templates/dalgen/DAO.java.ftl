<@pp.dropOutputFile />
<#list dalgen.daos as dao>
    <@pp.changeOutputFile name = "/main/java/${dao.classPath}/${dao.className}.java" />
package ${dao.packageName};

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
<#list dao.importLists as import>
import ${import};
</#list>

/**
* The Table ${dao.tableName!}.
* 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
* ${dao.desc!}
*/
@Repository
public class ${dao.className}{

    @Autowired
    private ${dao.doMapper.className} ${dao.doMapper.className?uncap_first};

    <#list dao.motheds as method>
    /**
     * desc:${method.desc!method.name!}.<br/>
     * descSql = ${method.sql!}
        <#list  method.params as param>
     * @param ${param.param} ${param.param}
        </#list>
     * @return ${method.returnClass!}
     */
    public ${method.returnClass!} ${method.name}(<#list  method.params as param><#if param_index gt 0>,</#if>${param.paramType!} <#assign pagingParam = param.param/>${param.param}</#list>){
    <#if method.pagingFlag == "true">
        int total = ${dao.doMapper.className?uncap_first}.${method.name}Count(<#list  method.params as param><#if param_index gt 0>, </#if>${param.param}</#list>);
        if(total>0){
            ${pagingParam}.setDatas(${dao.doMapper.className?uncap_first}.${method.name}Result(<#list  method.params as param><#if param_index gt 0>, </#if>${param.param}</#list>));
        }
        ${pagingParam}.setTotal(total);
        return ${pagingParam};
    <#else>
        return ${dao.doMapper.className?uncap_first}.${method.name}(<#list  method.params as param><#if param_index gt 0>, </#if>${param.param}</#list>);
    </#if>
    }
    </#list>
}
</#list>
