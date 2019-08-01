<@pp.dropOutputFile />
<#if dalgen.doMappers?size gt 0>
<#assign tmpTables = dalgen.tmpTables>
<#assign doMapper = dalgen.doMappers?first>
<#assign dbName=""/>
<#if !dalgen.getDataSourceSingle()><#assign dbName=dalgen.getDataSourceName()/></#if>
<@pp.changeOutputFile name = "/main/java/${doMapper.classPath}/${dbName}DBPreCheckMapper.java" />
package ${doMapper.packageName};
import java.util.List;
/**
 * 数据库字段检查
 */
public interface ${dbName}DBPreCheckMapper{

    List<String> dbColumnCheck();
}
</#if>
