package com.momo.mapper.req.systemconfig;

import com.momo.common.core.error.BaseReq;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @ClassName: DataDictDelsRes
 * @Author: Jie Li
 * @Date 2019-12-28 17:04
 * @Description: TODO
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 **/
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@EqualsAndHashCode(of = {"id"})
public class DataDictDelsReq extends BaseReq {

    /**
     * id ID.
     */
    private Long id;
    /**
     * sysDictCodeId 字段值(用于关联字典表里的 id ).
     */
    @NotNull(message = "关联 字段值id 必填", groups = {Page.class})
    private Long sysDictCodeId;
    /**
     * remark REMARK.
     */
    private String remark;

    /**
     * sysDictDelsName 参数名称.
     */
    private String sysDictDelsName;
    /**
     * sysDictCodeValue 字段值(用于关联字典表里的 参数值 ).
     */
    @NotBlank(message = "关联 字段值 必填", groups = {Page.class})
    private String sysDictCodeValue;
    /**
     * sysDictDelsValue 参数值.
     */
    private String sysDictDelsValue;
    /**
     * sysDictCodeHtmlLeft html左边值.
     */
    private String sysDictCodeHtmlLeft;
    /**
     * sysDictCodeHtmlRight html右边值.
     */
    private String sysDictCodeHtmlRight;
    /**
     * delFlag 删除状态(0-正常，1-删除).
     */
    private Integer delFlag;
    /**
     * disabledFlag 状态 0启用  1禁用.
     */
    private Integer disabledFlag;
    /**
     * sysDictDelsSeq 排序.
     */
    private Integer sysDictDelsSeq;
    /**
     * sysDictCodeHtmlApply 是否应用html值 0是 1否.
     */
    private Integer sysDictCodeHtmlApply;

}
