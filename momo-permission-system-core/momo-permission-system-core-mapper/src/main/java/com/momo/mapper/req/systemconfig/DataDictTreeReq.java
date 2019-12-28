package com.momo.mapper.req.systemconfig;

import com.momo.common.core.error.BaseReq;
import lombok.*;

import javax.validation.constraints.*;


/**
 * @ClassName: DataDictTreeReq
 * @Author: Jie Li
 * @Date 2019-12-27 11:24
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
public class DataDictTreeReq extends BaseReq {

    /**
     * id ID.
     */
    private Long id;
    /**
     * sysDictCodeParentId 父级id.
     */
    @NotNull(message = "父级id 必填,首级为0", groups = {Add.class})
    private Long sysDictCodeParentId;
    /**
     * remark 注释.
     */
    private String remark;
    /**
     * sysDictCodeName 参数名称.
     */
    @NotBlank(message = "参数名称 必填", groups = {Add.class})
    private String sysDictCodeName;
    /**
     * sysDictCodeLevel 字典层级.
     */
    private String sysDictCodeLevel;
    /**
     * sysDictCodeValue 参数值.
     */
    @NotBlank(message = "参数值 必填", groups = {Add.class})
    private String sysDictCodeValue;
    /**
     * sysDictCodeParentValue 上级参数值.
     */
    @NotBlank(message = "父级参数值 必填,首级为0", groups = {Add.class})
    private String sysDictCodeParentValue;
    /**
     * delFlag 删除状态(0-正常，1-删除).
     */
    private Integer delFlag;
    /**
     * disabledFlag 状态 0启用  1禁用.
     */
    @NotNull(message = "状态必填  0启用  1禁用.", groups = {Add.class})
    private Integer disabledFlag;
    /**
     * sysDictCodeSeq 排序.
     */
    @NotNull(message = "排序 必填", groups = {Add.class})
    @Pattern(regexp = "^[1-9]\\d*$", message = "排序必须为大于0的正整数", groups = {Add.class})
    @Min(value = 1, message = "最小值为1", groups = {Add.class})
    @Max(value = 2000000000, message = "最大值为 2000000000", groups = {Add.class})
    private String sysDictCodeSeq;
}
