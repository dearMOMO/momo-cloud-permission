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
<#function insertVal column>
    <#if column.sqlName == "LAST_UPDATED" || column.sqlName == "DATE_CREATED"><#return "now()"></#if>
    <#if column.sqlName == "IS_DELETED"><#return "'N'"></#if>
    <#return '${"#"}{${column.javaName},jdbcType=${column.sqlType}}'/>
</#function>

<#-- Update 时字段处理 -->
<#function updateVal column>
    <#if column.sqlName == "LAST_UPDATED" || column.sqlName == "DATE_CREATED"><#return "now()"></#if>
    <#return '${"#"}{${column.javaName},jdbcType=${column.sqlType}}'/>
</#function>

<#-- update 中需要设置的字段 -->
<#function updateIncludeColumn column primaryKeys>
    <#if column.sqlName == "CREATOR" || column.sqlName == "DATE_CREATED">
        <#return false>
    </#if>
    <#list primaryKeys as pkcolumn>
        <#if pkcolumn.sqlName == column.sqlName><#return false></#if>
    </#list>
    <#return true>
</#function>
