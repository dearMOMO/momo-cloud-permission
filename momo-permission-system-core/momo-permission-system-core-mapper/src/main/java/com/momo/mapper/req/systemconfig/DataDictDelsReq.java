package com.momo.mapper.req.systemconfig;

import com.momo.common.core.error.BaseReq;
import lombok.*;

import javax.validation.constraints.*;

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
    @NotNull(message = "关联 字段值id 必填", groups = {Page.class, Add.class})
    private Long sysDictCodeId;
    /**
     * remark REMARK.
     */
    private String remark;

    /**
     * sysDictDelsName 参数名称.
     */
    @NotBlank(message = "参数名称 必填", groups = {Add.class})
    private String sysDictDelsName;
    /**
     * sysDictCodeValue 字段值(用于关联字典表里的 参数值 ).
     */
    @NotBlank(message = "关联 字段值 必填", groups = {Page.class, Add.class})
    private String sysDictCodeValue;
    /**
     * sysDictDelsValue 参数值.
     */
    @NotBlank(message = "参数值 必填", groups = {Add.class})
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
     * disabledFlag 状态 0启用  1禁用.
     */
    @NotNull(message = "状态 0启用  1禁用 必填", groups = {Add.class})
    private Integer disabledFlag;
    /**
     * sysDictDelsSeq 排序.
     */
    @NotNull(message = "排序 必填", groups = {Add.class})
    @Pattern(regexp = "^[1-9]\\d*$", message = "排序必须为大于0的正整数", groups = {Add.class, Modify.class})
    @Min(value = 1, message = "最小值为1", groups = {Add.class})
    @Max(value = 2000000000, message = "最大值为 2000000000", groups = {Add.class, Modify.class})
    private String sysDictDelsSeq;
    /**
     * sysDictCodeHtmlApply 是否应用html值 0是 1否.
     */
    @NotNull(message = "是否应用html值 0是 1否 必填", groups = {Add.class})
    private Integer sysDictCodeHtmlApply;

}
