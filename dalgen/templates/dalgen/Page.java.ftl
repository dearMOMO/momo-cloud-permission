<@pp.dropOutputFile />
<#list dalgen.pagings as paging>
    <@pp.changeOutputFile name = "/main/java/${paging.classPath}/${paging.className}.java" />
package ${paging.packageName};

<#list paging.importLists as import>
import ${import};
</#list>

/**
 * The table ${paging.desc!}
 * 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
 */
public class ${paging.className} extends BasePage<${paging.resultType}>{

    <#list paging.fieldses as fields>
    /**
     * ${fields.name} ${fields.desc!}.
     */
    private ${fields.javaType} ${fields.name};
    </#list>
    <#list paging.fieldses as fields>

    /**
     * Set ${fields.name} ${fields.desc!}.
     */
    public void set${fields.name?cap_first}(${fields.javaType} ${fields.name}){
        this.${fields.name} = ${fields.name};
    }

    /**
     * Get ${fields.name} ${fields.desc!}.
     *
     * @return the string
     */
    public ${fields.javaType} get${fields.name?cap_first}(){
        return ${fields.name};
    }
    </#list>
}
</#list>
