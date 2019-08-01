<#--获取字段list-->
<#function columnList xmlMappers tableName>
    <#list xmlMappers as xmlMapper>
        <#if xmlMapper.table.sqlName == tableName>
            <#return xmlMapper.table.columnList/>
        </#if>
    </#list>
</#function>

<#-- 字段默认值 -->
<#function columnVal column>
    <#if column.testVal??><#return column.testVal></#if>
    <#if column.javaName == "id"><#return "1"></#if>
    <#if column.javaName == "isDel"><#return "0"></#if>
    <#if column.javaType == "java.util.Date"><#return "1984-12-24"></#if>
    <#if column.javaType == "String"><#return column.javaName?substring(0,1)?cap_first></#if>
    <#return "1">
</#function>

<#-- test默认值 -->
<#function paramVal param columnList>
    <#list columnList as column>
        <#if column.javaName == param.param || "old${column.javaName?cap_first}" == param.param>
            <#if column.javaType == "java.util.Date"><#return "new Date()"></#if>
            <#assign columnVal = "0">

            <#if column.javaName == "id"><#assign columnVal = "1"></#if>
            <#if column.javaName == "isDel"><#assign columnVal = "0"></#if>
            <#if column.javaType == "String"><#assign columnVal = column.javaName?substring(0,1)?cap_first></#if>

            <#if column.javaType == "Long"><#return "${columnVal}L"></#if>
            <#if column.javaType == "String"><#return "\"${columnVal}\""></#if>

            <#if column.testVal??>
                <#if param.paramType?ends_with("Enum")>
                    <#return "${param.paramType}.${column.testVal}"/>
                </#if>
                <#return "TestParamValUtil.trans(${param.paramType}.class,\"${column.testVal}\")"/>
            </#if>
            <#return columnVal>
        </#if>
    </#list>

    <#if param.paramType?ends_with("Enum") && param.testVal??>
        <#return "${param.paramType}.${param.testVal}"/>
    </#if>

    <#if param.paramType == "String"><#return "\"${param.param}\""></#if>
    <#if param.paramType == "Long"><#return "1L"></#if>
    <#if param.paramType == "Integer"><#return "1"></#if>
    <#if param.paramType?starts_with("List<String>")><#return "Lists.newArrayList(\"S\")"></#if>
    <#if param.paramType?starts_with("List<Long>")><#return "Lists.newArrayList(1L)"></#if>
    <#if param.paramType?starts_with("List<Integer>")><#return "Lists.newArrayList(1)"></#if>
    <#if param.paramType?starts_with("List<Double>")><#return "Lists.newArrayList(1d)"></#if>
    <#if param.paramType?starts_with("List")><#return "Lists.newArrayList()"></#if>
    <#return "new ${param.paramType}()">
</#function>

<#-- 忽略不需要单元测试的类 -->
<#function neadTest methodName>
    <#assign ignoreMethods = ["insert","insertBatch","update", "getById"]>
    <#if ignoreMethods?seq_contains(methodName)><#return false></#if>
    <#if methodName != "insert" && methodName?starts_with("insert")><#return false></#if>
<#return true>
</#function>

<#function getByIdMethod motheds>
    <#list motheds as method>
        <#if method.name == "getById"><#return method></#if>
    </#list>
</#function>

<#function fanType2Type fanType>
    <#if fanType??>
        <#return fanType?substring(fanType?last_index_of("<")+1,fanType?last_index_of(">"))>
    <#else >
        <#return fanType>
    </#if>
</#function>

<#function methodClass method>
    <#if method.kvMap?? && method.kvMap == "true">
        <#if method.mapVType??>
            <#assign valType = method.mapVType>
        <#else >
            <#assign valType = fanType2Type(method.returnClass)>
        </#if>
        <#return "Map<${method.mapKType},${valType}>">
    <#elseif method.kvMap?? && method.kvMap == "list">
        <#if method.mapVType??>
            <#assign valType = method.mapVType>
        <#else >
            <#assign valType = fanType2Type(method.returnClass)>
        </#if>
        <#return "Map<${method.mapKType},List<${valType}>>">
    <#elseif method.kvMap?? && method.kvMap == "set">
        <#if method.mapVType??>
            <#assign valType = method.mapVType>
        <#else >
            <#assign valType = fanType2Type(method.returnClass)>
        </#if>
        <#return "Map<${method.mapKType},Set<${valType}>>">
    <#else>
        <#return method.returnClass>
    </#if>
</#function>

<#function javaTypeTrun javaType>
    <#if javaType?contains("<")>
        <#return javaType?substring(0,javaType?index_of("<"))/>
    </#if>
    <#return javaType/>
</#function>
