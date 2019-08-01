<@pp.dropOutputFile />
<#if dalgen.xmlMappers?size gt 0>
<#assign tmpTables = dalgen.tmpTables>
<#assign xmlMapper = dalgen.xmlMappers?first>
<#assign dbName=""/>
<#if !dalgen.getDataSourceSingle()><#assign dbName=dalgen.getDataSourceName()/></#if>
<@pp.changeOutputFile name = "/main/resources/${xmlMapper.doMapper.xmlPath}/${dbName}DBPreCheckMapper.xml" />
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="${xmlMapper.doMapper.packageName}.${dbName}DBPreCheckMapper">
<#assign idx = 0>
    <select id="dbColumnCheck" resultType="String">
        SELECT CONCAT('表结构不一致 tbName:',tb_name,' 期望字段:',exp_columns,' 数据库中字段:',db_columns) as msg
        FROM (
<#list tmpTables as tmpTable>
    <#if tmpTable.columnList?size gt 0><#assign idx = idx+1>
    <#if idx gt 1>
    UNION ALL
    </#if>
        SELECT
            COUNT(*)= ${tmpTable.columnList?size} as fg,'${tmpTable.physicalName}' as tb_name,group_concat(COLUMN_NAME) db_columns,
            '<#list tmpTable.columnList as column><#if column_index gt 0>,</#if>${column.sqlName}</#list>' exp_columns
        FROM INFORMATION_SCHEMA.COLUMNS
        WHERE
            TABLE_NAME = '${tmpTable.physicalName}'
            AND COLUMN_NAME in(<#list tmpTable.columnList as column><#if column_index gt 0>,</#if>'${column.sqlName}'</#list>)
        GROUP BY TABLE_NAME
    </#if>
</#list>
        )a
        WHERE fg=0
    </select>
</mapper>
</#if>
