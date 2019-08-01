<@pp.dropOutputFile />
<#import "../lib/lib.ftl" as lib/>
<#list dalgen.resultMaps as resultMap>
    <@pp.changeOutputFile name = "/main/java/${resultMap.classPath}/${resultMap.className}.java" />
package ${resultMap.packageName};

import java.io.Serializable;
<#list resultMap.importLists as import>
import ${import};
</#list>
<@lib.importByResultMap daos=dalgen.daos resultMap=resultMap/>
<#assign extend = "">
    <#list dalgen.dos as DO>
        <#if DO.tableName == resultMap.tableName?upper_case && resultMap.extend == "base">
        <#assign extend = "extends "+DO.className>
import ${DO.packageName}.${DO.className};
        </#if>
    </#list>


/**
 * The table ${resultMap.desc!resultMap.className}
 */
public class ${resultMap.className} ${extend} implements Serializable {

private static final long serialVersionUID = -1L;

    <#--属性-->
    <#list resultMap.fieldses as fields>
    /**
     * ${fields.name} ${fields.desc!}.
     */
    private ${fields.javaType} ${fields.name};
    </#list>

    <#--一对一-->
    <#list resultMap.associations as association>
    <#assign method = lib.getMethodByMethodName(dalgen.daos,association.tableName?upper_case,association.methodName)>
    /**
     * ${association.select} ${association.remark!}.
     */
    private ${method.returnClass!} ${association.property};
    </#list>

    <#--一对多-->
    <#list resultMap.collections as collection>
        <#assign method = lib.getMethodByMethodName(dalgen.daos,collection.tableName?upper_case,collection.methodName)>
    /**
     * ${collection.select} ${collection.remark!}.
     */
    private ${method.returnClass!} ${collection.property};
    </#list>

    <#list resultMap.fieldses as fields>

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


<#--一对一-->
    <#list resultMap.associations as association>
        <#assign method = lib.getMethodByMethodName(dalgen.daos,association.tableName?upper_case,association.methodName)>
    /**
     * Set ${association.property}.${association.remark!}.
     */
    public void set${association.property?cap_first}(${method.returnClass!} ${association.property}){
        this.${association.property} = ${association.property};
    }

    /**
     * Get ${association.property} ${association.remark!}.
     *
     * @return the ${method.returnClass}
     */
    public ${method.returnClass} get${association.property?cap_first}(){
        return ${association.property};
    }
    </#list>

<#--一对多-->
    <#list resultMap.collections as collection>
        <#assign method = lib.getMethodByMethodName(dalgen.daos,collection.tableName?upper_case,collection.methodName)>
    /**
     * Set ${collection.property}.${collection.remark!}.
     */
    public void set${collection.property?cap_first}(${method.returnClass!} ${collection.property}){
    this.${collection.property} = ${collection.property};
    }

    /**
     * Get ${collection.property} ${collection.remark!}.
     *
     * @return the ${method.returnClass}
     */
    public ${method.returnClass} get${collection.property?cap_first}(){
        return ${collection.property};
    }
    </#list>
}
</#list>
