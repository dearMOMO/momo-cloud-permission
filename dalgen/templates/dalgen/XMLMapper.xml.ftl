<@pp.dropOutputFile />
<#import "../lib/lib.ftl" as lib/>
<#import "../lib/function.ftl" as fun/>
<#list dalgen.xmlMappers as xmlMapper>
<@pp.changeOutputFile name = "/main/resources/${xmlMapper.doMapper.xmlPath}/${xmlMapper.doMapper.className}.xml" />
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="${xmlMapper.doMapper.packageName}.${xmlMapper.doMapper.className}">
    <!-- 自动生成,请修改 ${xmlMapper.table.sqlName}.xml -->
    <#-- 自定义分页时参数 limit {pageLimit}-->
    <#--生成BaseResultMap-->
    <resultMap id="BaseResultMap"  type="${xmlMapper.doClass.packageName}.${xmlMapper.doClass.className}">
<#list xmlMapper.table.columnList as column>
        <#if column.sqlName?upper_case =="ID"><id column="${column.sqlName}" property="${column.javaName}" jdbcType="${column.sqlType}" javaType="${fun.javaTypeTrun(column.javaType)}"/><#else><result column="${column.sqlName}" property="${column.javaName}" jdbcType="${column.sqlType}" javaType="${fun.javaTypeTrun(column.javaType)}"/></#if>
</#list>
    </resultMap>

    <#--生成自定义ResultMap-->
<#list xmlMapper.resultMaps as resultMap>
    <#assign extends = "">
    <#if resultMap.extend == "base">
        <#assign extends = "extends=\"BaseResultMap\"">
    </#if>
    <resultMap id="${resultMap.name}"  type="${resultMap.packageName}.${resultMap.className}" ${extends}>
    <#list resultMap.columnList as column>
        <#if column.sqlName?upper_case =="ID"><id column="${column.sqlName}" property="${column.javaName}"  javaType="${fun.javaTypeTrun(column.javaType)}"/><#else><result column="${column.sqlName}" property="${column.javaName}"  javaType="${fun.javaTypeTrun(column.javaType)}"/></#if>
    </#list>
    <#list resultMap.associations as association>
        <association property="${association.property}" column="${association.column}"  select="${lib.genDOMapperNameSpace(dalgen.doMappers,association.tableName)}.${association.methodName}"/>
    </#list>
    <#list resultMap.collections as collection>
        <collection property="${collection.property}" column="${collection.column}"  select="${lib.genDOMapperNameSpace(dalgen.doMappers,collection.tableName)}.${collection.methodName}"/>
    </#list>
    </resultMap>
</#list>

    <#if xmlMapper.sqlMap?size == 0 >
    <#-- baseSql -->
    <sql id="Base_Column_List">
        <#list xmlMapper.table.columnList as column><#if column_index gt 0>,</#if>${column.sqlName}</#list>
    </sql>

    <#-- baseSql -->
    <sql id="Base_SF_Column_List">
        <#list xmlMapper.table.columnList as column><#if column_index gt 0>,</#if>sf.${column.sqlName}</#list>
    </sql>
    <#else><#list xmlMapper.sqlMap?values as sqlXML>
    ${sqlXML}
    </#list>
    </#if>

    <#-- sql部分  -->
    <#list xmlMapper.cfTable.operations as operation>

<#if operation.multiplicity.code=="paging"><#--分页-->
    <#if operation.pagingCntType.code=="pagingCustom"><#--分页-->
    <!--${operation.remark!operation.name}-->
    <${lib.operation2Sql(operation.name)} id="${operation.name}Result" ${lib.mapperResult(operation)}${lib.timeout(operation)}>
    ${operation.cdata!}
    </${lib.operation2Sql(operation.name)}>
    <#else>
    <!--${operation.remark!operation.name} pageCount-->
    <${lib.operation2Sql(operation.name)} id="${operation.name}Count"  resultType="int"${lib.timeout(operation)}>
${operation.cdataPageCount!}
    </${lib.operation2Sql(operation.name)}>
    <!--${operation.remark!operation.name} pageResult-->
    <${lib.operation2Sql(operation.name)} id="${operation.name}Result"  ${lib.mapperResult(operation)}${lib.timeout(operation)}>
${operation.cdata!}
        <#if operation.pagingCntType.code=="paging" || operation.pagingCntType.code=="pagingExtCnt">
        limit ${"#"}{startRow},${"#"}{limit}
        </#if>
    </${lib.operation2Sql(operation.name)}>
        </#if>
    <#else><#--非分页-->
    <!--${operation.remark!operation.name}-->
    <${lib.operation2Sql(operation.name)} id="${operation.name}" ${lib.mapperResult(operation)}${lib.timeout(operation)}>
${operation.cdata!}
    </${lib.operation2Sql(operation.name)}>
</#if>
    </#list>
</mapper>
</#list>
