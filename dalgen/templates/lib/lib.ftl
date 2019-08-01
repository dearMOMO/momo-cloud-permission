<#--空格处理-->
<#function space param>
    <#if param?length gt 15><#return ""/>
    <#else>
        <#return "               "?substring(param?length)/>
    </#if>
</#function>

<#-- operation 2 sql -->
<#function operation2Sql param>
    <#if param?starts_with("insert")><#return "insert"/></#if>
    <#if param?starts_with("update")><#return "update"/></#if>
    <#if param?starts_with("delete")><#return "delete"/></#if>
    <#return "select"/>
</#function>

<#-- mapperxml result -->
<#function mapperResult operation>
    <#if operation.resultmap??><#return 'resultMap="${operation.resultmap}"'/></#if>
    <#if operation.resulttype??><#return 'resultType="${operation.resulttype}"'/></#if>
    <#if operation.name?starts_with("insert")><#return ''/></#if>
    <#if operation.name?starts_with("update")><#return ''/></#if>
    <#if operation.name?starts_with("delete")><#return ''/></#if>
    <#return 'resultMap="BaseResultMap"'/>
</#function>

<#function timeout operation>
    <#if operation.timeout??><#return ' timeout="${operation.timeout}"'/></#if>
    <#return ""/>
</#function>

<#-- insert  时字段处理 -->
<#function insertVal column dalgen>
    <#if column.sqlName == "GMT_MODIFIED" || column.sqlName == "GMT_CREATE"><#return "now()"></#if>
    <#if column.sqlName?upper_case == "ID"><#return "null"></#if>
    <#return '${"#"}{${column.javaName},jdbcType=${column.sqlType}}'/>
</#function>

<#-- insert  时字段处理 -->
<#function insertBatchVal column dalgen>
    <#if column.sqlName == "GMT_MODIFIED" || column.sqlName == "GMT_CREATE"><#return "now()"></#if>
    <#if column.sqlName?upper_case == "ID"><#return "null"></#if>
    <#return '${"#"}{item.${column.javaName},jdbcType=${column.sqlType}}'/>
</#function>

<#-- Update 时字段处理 -->
<#function updateVal column>
    <#if column.sqlName == "GMT_MODIFIED" || column.sqlName == "GMT_CREATE"><#return "now()"></#if>
    <#return '${"#"}{${column.javaName},jdbcType=${column.sqlType}}'/>
</#function>

<#-- update 中需要设置的字段 -->
<#function updateIncludeColumn dalgen column primaryKeys>
    <#if column.sqlName == "CREATOR" || column.sqlName == "GMT_CREATE">
        <#return false>
    </#if>
    <#list primaryKeys as pkcolumn>
        <#if pkcolumn.sqlName == column.sqlName><#return false></#if>
    </#list>
    <#return true>
</#function>

<#-- update 中需要设置的字段 -->
<#function updateIncludeUKColumn dalgen column primaryKeys uniqueColums>
    <#if column.sqlName == "CREATOR" || column.sqlName == "GMT_CREATE">
        <#return false>
    </#if>
    <#if primaryKeys?? && primaryKeys.columnList??>
    <#list primaryKeys.columnList as pkcolumn>
        <#if pkcolumn.sqlName == column.sqlName><#return false></#if>
    </#list>
    </#if>
    <#list uniqueColums as pkcolumn>
        <#if pkcolumn.sqlName == column.sqlName><#return false></#if>
    </#list>
    <#return true>
</#function>

<#function getMethodByMethodName daos tableName methodName>
    <#list daos as dao>
        <#if dao.tableName == tableName>
            <#list dao.motheds as method>
                <#if method.name == methodName>
                    <#return method>
                </#if>
            </#list>
        </#if>
    </#list>
</#function>

<#macro importByResultMap daos resultMap>
<#assign imports = []>
    <#list daos as dao>
        <#list resultMap.associations as association>
        <#if dao.tableName == association.tableName>
            <#list dao.motheds as method>
                <#if method.name == association.methodName>
                    <#list dao.importLists as import>
                        <#if import?ends_with(method.returnClass)>
                            <#if !imports?seq_contains(import)>
                            <#assign imports = imports+[import]>
                            </#if>
                        </#if>
                    </#list>
                </#if>
            </#list>
        </#if>
        </#list>
        <#list resultMap.collections as collection>
            <#if dao.tableName == collection.tableName>
                <#list dao.motheds as method>
                    <#if method.name == collection.methodName>
                        <#list dao.importLists as import>
                        <#if isClasz(import,method.returnClass)>
                            <#if !imports?seq_contains(import)>
                                <#assign imports = imports+[import]>
                            </#if>
                            <#if !imports?seq_contains("java.util.List")>
                                <#assign imports = imports+["java.util.List"]>
                            </#if>
                        </#if>
                        </#list>
                    </#if>
                </#list>
            </#if>
        </#list>
    </#list>
<#list imports as import>
import ${import};
</#list>
</#macro>

<#function genDOMapperNameSpace doMappers tableName>
    <#list doMappers as doMapper>
        <#if doMapper.tableName == tableName>
            <#return doMapper.packageName+"."+doMapper.className>
        </#if>
    </#list>
</#function>

<#function isClasz import listF>
    <#assign clazz = listF>
    <#if listF?contains("<") && import?ends_with(listF?substring(listF?index_of("<")+1,listF?index_of(">")))>
        <#return true>
    </#if>
<#return false>
</#function>
