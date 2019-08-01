<@pp.dropOutputFile />
<#import "../lib/lib.ftl" as lib/>
<#list dalgen.tables as table>
<@pp.changeOutputFile name = "/${dalgen.tablesPath}/${table.sqlName}.xml" />
<!DOCTYPE table SYSTEM "../config/table-config-1.0.dtd">
<table sqlname="${table.sqlName?lower_case}" physicalName="${table.physicalName?lower_case}"<#if table.remark??> remark="${table.remark!}"</#if>>
    <!--  特殊字符说明 &lt;&gt; <> -->
    <!-- baseSql option中 select * 会自动替换为 include -->
    <sql id="Base_Column_List">
        <#list table.columnList as column><#if column_index gt 0><#if column_index%5==0>${" \n"}        </#if>,</#if>${column.sqlName}</#list>
    </sql>

    <!-- baseSql option中 select sf.* 会自动替换为 include -->
    <sql id="Base_SF_Column_List">
        <#list table.columnList as column><#if column_index gt 0><#if column_index%5==0>${"\n"}        </#if>,</#if>sf.${column.sqlName}</#list>
    </sql>

    <operation name="insert" paramtype="object" remark="插入表:${table.sqlName?lower_case}">
        <#if dalgen.dbType=="MySQL">
        <selectKey resultType="java.lang.Long" keyProperty="id" order="AFTER">
            SELECT
            LAST_INSERT_ID()
        </selectKey>
        </#if>
        <![CDATA[
        INSERT INTO ${table.sqlName?lower_case}(
        <#list table.columnList as column>
            <#if column_index gt 0>,</#if>${column.sqlName}
        </#list>
        )VALUES(
        <#list table.columnList as column>
            <#if column_index gt 0>,</#if> ${lib.insertVal(column,dalgen)}
        </#list>
        )
        ]]>
    </operation>

    <!-- foreach 可以自定义类型，paramtype="primitive" foreach->javatype="自己书写的类"  -->
    <!-- 只有一个参数且为List时必须将参数命名为list -->
    <operation name="insertBatch" paramtype="objectList" remark="批量插入表:${table.sqlName?lower_case}">
        INSERT INTO ${table.sqlName?lower_case}(
        <#list table.columnList as column>
            <#if column_index gt 0>,</#if>${column.sqlName}
        </#list>
        )VALUES
        <foreach collection="list"  item="item" separator=",">
            (
            <#list table.columnList as column>
                <#if column_index gt 0>,</#if> ${lib.insertBatchVal(column,dalgen)}
            </#list>
            )
        </foreach>
    </operation>

<#if table.primaryKeys??>
    <!-- 不推荐使用全 update 有需要自己打开
    <operation name="update" paramtype="object" remark="更新表:${table.sqlName?lower_case}">
        <![CDATA[
        UPDATE ${table.sqlName?lower_case}
        SET
        <#assign c_idx = 0>
        <#list table.columnList as column>
            <#if lib.updateIncludeColumn(dalgen,column,table.primaryKeys.columnList)><#assign c_idx = c_idx+1>
            <#if c_idx gt 1>,</#if>${column.sqlName}${lib.space(column.sqlName)} = ${lib.updateVal(column)}
            </#if>
        </#list>
        WHERE
        <#list table.primaryKeys.columnList as column>
            <#if column_index gt 0>AND </#if>${column.sqlName}${lib.space(column.sqlName)} = ${"#"}{${column.javaName},jdbcType=${column.sqlType}}
        </#list>
        ]]>
    </operation>
    -->
    <operation name="deleteBy${table.primaryKeys.pkName}"  remark="根据主键删除数据:${table.sqlName?lower_case}">
        <![CDATA[
        <#-- 硬删除 -->
        DELETE FROM ${table.sqlName?lower_case}
        WHERE
        <#list table.primaryKeys.columnList as column>
            <#if column_index gt 0>AND </#if>${column.sqlName} = ${"#"}{${column.javaName},jdbcType=${column.sqlType}}
        </#list>
        ]]>
    </operation>

    <operation name="getBy${table.primaryKeys.pkName}" multiplicity="one" remark="根据主键获取数据:${table.sqlName?lower_case}">
        SELECT *
        FROM ${table.sqlName?lower_case}
        WHERE
        <![CDATA[
        <#list table.primaryKeys.columnList as column>
            <#if column_index gt 0>AND </#if>${column.sqlName} = ${"#"}{${column.javaName},jdbcType=${column.sqlType}}
        </#list>
        ]]>
    </operation>
</#if>
<#list table.uniqueIndexs as uniqueIndex>

    <!-- 根据唯一约束操作数据 -->
    <operation name="updateBy${uniqueIndex.ukName}" paramtype="object" remark="根据唯一约束${uniqueIndex.ukName}更新表:${table.sqlName?lower_case}">
        <![CDATA[
        UPDATE ${table.sqlName?lower_case}
        SET
        <#assign c_idx = 0>
        <#list table.columnList as column>
            <#if lib.updateIncludeUKColumn(dalgen,column,table.primaryKeys!,uniqueIndex.columnList)><#assign c_idx = c_idx+1>
            <#if c_idx gt 1>,</#if>${column.sqlName}${lib.space(column.sqlName)} = ${lib.updateVal(column)}
            </#if>
        </#list>
        WHERE
        <#list uniqueIndex.columnList as column>
            <#if column_index gt 0>AND </#if>${column.sqlName}${lib.space(column.sqlName)} = ${"#"}{${column.javaName},jdbcType=${column.sqlType}}
        </#list>
        ]]>
    </operation>

    <operation name="deleteBy${uniqueIndex.ukName}" remark="根据唯一约束${uniqueIndex.ukName}删除数据:${table.sqlName?lower_case}">
        <![CDATA[
        DELETE FROM ${table.sqlName?lower_case}
        WHERE
        <#list uniqueIndex.columnList as column>
            <#if column_index gt 0>AND </#if>${column.sqlName}${lib.space(column.sqlName)} = ${"#"}{${column.javaName},jdbcType=${column.sqlType}}
        </#list>
        ]]>
    </operation>

    <operation name="getBy${uniqueIndex.ukName}" multiplicity="one" remark="根据唯一约束${uniqueIndex.ukName}获取数据:${table.sqlName?lower_case}">
        SELECT *
        FROM ${table.sqlName?lower_case}
        WHERE
        <![CDATA[
        <#list uniqueIndex.columnList as column>
            <#if column_index gt 0>AND </#if>${column.sqlName}${lib.space(column.sqlName)} = ${"#"}{${column.javaName},jdbcType=${column.sqlType}}
        </#list>
        ]]>
    </operation>
</#list>

<#list table.normalIndexs as normalIndex>
    <!-- 根据普通索引查询数据 -->
    <operation name="queryBy${normalIndex.idxName}" multiplicity="many" remark="根据普通索引${normalIndex.idxName}获取数据:${table.sqlName?lower_case}">
        SELECT *
        FROM ${table.sqlName?lower_case}
        WHERE
        <![CDATA[
        <#list normalIndex.columnList as column>
            <#if column_index gt 0>AND </#if>${column.sqlName}${lib.space(column.sqlName)} = ${"#"}{${column.javaName},jdbcType=${column.sqlType}}
        </#list>
        ]]>
    </operation>
</#list>
</table>
</#list>
