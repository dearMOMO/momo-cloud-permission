<@pp.dropOutputFile />
<#assign tmpTables = dalgen.tmpCfTables>
<@pp.changeOutputFile name = "/main/resources/mybatis-config.xml" />
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<#assign enumTypes = pp.newWritableSequence()>
<#assign jsonTypes = pp.newWritableSequence()>
<#assign othTypes  = pp.newWritableSequence()>
<#--<#list tmpTables as tmpTable>
    <#list tmpTable.columns as column>
        <#if column.javatype?lower_case?starts_with(dalgen.config.getCustomerProperty("enumPathBase")!null)>
            <#if !enumTypes?seq_contains(column.javatype)>
            <@pp.add seq=enumTypes value="${column.javatype}" />
            </#if>
        <#elseif  column.javatype?lower_case?starts_with(dalgen.config.getCustomerProperty("jsonPathBase")!null)>
            <#if !jsonTypes?seq_contains(column.javatype)>
            <@pp.add seq=jsonTypes value="${column.javatype}" />
            </#if>
        <#elseif !othTypes?seq_contains(column.javatype) && !column.javatype?lower_case?starts_with("com.alibaba.hr.common.util.encrypt")>
            <@pp.add seq=othTypes value="${column.javatype}" />
        </#if>
    </#list>
</#list>-->
<configuration>
    <settings>
        <setting name="logImpl" value="SLF4J"/>
    </settings>

    <typeHandlers>
<#--
        <typeHandler handler="org.apache.ibatis.type.InstantTypeHandler" />
        <typeHandler handler="org.apache.ibatis.type.LocalDateTimeTypeHandler" />
        <typeHandler handler="org.apache.ibatis.type.LocalDateTypeHandler" />
        <typeHandler handler="org.apache.ibatis.type.LocalTimeTypeHandler" />
        <typeHandler handler="org.apache.ibatis.type.OffsetDateTimeTypeHandler" />
        <typeHandler handler="org.apache.ibatis.type.OffsetTimeTypeHandler" />
        <typeHandler handler="org.apache.ibatis.type.ZonedDateTimeTypeHandler" />
<#list enumTypes as enumType>
        <typeHandler handler="com.xxx.dal.typehandler.VarCharEnumHandler" javaType="${enumType}"/>
</#list>
<#list jsonTypes as jsonType>
        <typeHandler handler="com.xxx.dal.typehandler.VarCharJsonHandler" javaType="${jsonType}"/>
</#list>
<#list othTypes as othType>
        <typeHandler handler="com.xxx.dal.typehandler.VarCharOtherHandler" javaType="${othType}"/>
</#list>
-->

    </typeHandlers>
    <!-- 自动生成,如需修改请移步 mybatis-config.xml.ftl  -->
    <mappers>
    <#--<#list dalgen.doMappers as doMapper>-->
        <#--<mapper resource="${doMapper.classPath}/${doMapper.className}.xml"/>-->
    <#--</#list>-->
    </mappers>
</configuration>
