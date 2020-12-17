package com.th.dicservice.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author cc
 * @since 2020-11-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "Dic对象", description = "")
public class Dic implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "createTime", fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    @TableField(value = "updateTime", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @ApiModelProperty(value = "是否启用")
    private Integer ban;

    @ApiModelProperty(value = "逻辑删除")
    private Integer deleted;

    @ApiModelProperty(value = "字典类别id")
    @TableField("typeId")
    private Integer typeId;

    @ApiModelProperty(value = "字典id")
    @TableField("dicId")
    private Integer dicId;

    @ApiModelProperty(value = "字典名称")
    @TableField("dicName")
    private String dicName;

    @ApiModelProperty(value = "备注")
    private String remark;

    @Version
    @ApiModelProperty(value = "版本号")
    @TableField(value = "VERSION", fill = FieldFill.INSERT)
    private Integer version;
}
