package com.th.userservice.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;

import java.io.Serializable;

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
 * @since 2020-12-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "User对象", description = "")
@TableName("acl_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "逻辑删除")
    private Integer deleted;

    @ApiModelProperty(value = "是否禁用")
    private Integer ban;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "createTime", fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "updateTime", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @ApiModelProperty(value = "用户类型")
    @TableField("userType")
    private String userType;

    @ApiModelProperty(value = "性别")
    @TableField("gender")
    private String gender;

    @ApiModelProperty(value = "登录IP")
    @TableField("loginIp")
    private String loginIp;

    @ApiModelProperty(value = "登录时间")
    @TableField("loginTime")
    private Date loginTime;

    @ApiModelProperty(value = "版本号")
    @TableField(value = "VERSION", fill = FieldFill.INSERT)
    private Integer version;

    @ApiModelProperty(value = "电话号码")
    @TableField("phone")
    private String phone;

    @ApiModelProperty(value = "微信openid")
    @TableField("openId")
    private String openId;

    @ApiModelProperty(value = "用户头像")
    @TableField("avatar")
    private String avatar;

    @ApiModelProperty(value = "用户签名")
    @TableField("sign")
    private String sign;


}
