<@pp.dropOutputFile />
<#list dalgen.doMappers as doMapper>
<@pp.changeOutputFile name = "/main/java/${doMapper.classPath}/${doMapper.className}.java" />
package ${doMapper.packageName};

<#list doMapper.importLists as import>
import ${import};
</#list>
import org.apache.ibatis.annotations.Param;

/**
 * 由于需要对分页支持,请直接使用对应的DAO类
 * 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
 * The Table ${doMapper.tableName!}.
 * ${doMapper.desc!}
 */
public interface ${doMapper.className}{

    <#list doMapper.motheds as method>
    /**
     * desc:${method.desc!method.name!}.<br/>
     * descSql = ${method.sql!}
    <#list  method.params as param>
     * @param ${param.param} ${param.param}
    </#list>
     * @return ${method.returnClass!}
     */
    ${method.returnClass!} ${method.name}(<#list  method.params as param><#if param_index gt 0>,</#if><#if method.params?size gt 1>@Param("${param.param}")</#if>${param.paramType!} ${param.param}</#list>);
    </#list>
}
</#list>
