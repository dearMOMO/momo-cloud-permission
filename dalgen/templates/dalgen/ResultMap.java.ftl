<@pp.dropOutputFile />
<#list dalgen.resultMaps as resultMap>
    <@pp.changeOutputFile name = "/main/java/${resultMap.classPath}/${resultMap.className}.java" />
package ${resultMap.packageName};

import java.io.Serializable;
<#list resultMap.importLists as import>
import ${import};
</#list>

/**
 * The table ${resultMap.desc!resultMap.className}
 * 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
 */
public class ${resultMap.className} implements Serializable {

private static final long serialVersionUID = -1L;

    <#list resultMap.fieldses as fields>
    /**
     * ${fields.name} ${fields.desc}.
     */
    private ${fields.javaType} ${fields.name};
    </#list>
    <#list resultMap.fieldses as fields>

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
