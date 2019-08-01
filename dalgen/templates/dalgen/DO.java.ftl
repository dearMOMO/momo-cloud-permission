<@pp.dropOutputFile />
<#list dalgen.dos as DO>
<@pp.changeOutputFile name = "/main/java/${DO.classPath}/${DO.className}.java" />
package ${DO.packageName};

<#list DO.importLists as import>
<#if !import?ends_with("${DO.className}")>
import ${import};
</#if>
</#list>

/**
 * The table ${DO.desc}
 */
public class ${DO.className}{

    <#list DO.fieldses as fields>
    /**
     * ${fields.name} ${fields.desc}.
     */
    private ${fields.javaType} ${fields.name};
    </#list>
    <#list DO.fieldses as fields>

    /**
     * Set ${fields.name} ${fields.desc}.
     */
    public void set${fields.name?cap_first}(${fields.javaType} ${fields.name}){
        this.${fields.name} = ${fields.name};
    }

    /**
     * Get ${fields.name} ${fields.desc}.
     *
     * @return the string
     */
    public ${fields.javaType} get${fields.name?cap_first}(){
        return ${fields.name};
    }
    </#list>
}
</#list>
