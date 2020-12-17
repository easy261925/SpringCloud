package com.th.userservice.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author cc
 * @date 2020-11-05-16:10
 */
@Data
public class UserVo {

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "验证码")
    private String verifyCode;

    @ApiModelProperty(value = "手机号码")
    private String phone;

    @ApiModelProperty(value = "登录类型 mobile/account")
    private String type;
}
