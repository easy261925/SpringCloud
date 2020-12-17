package com.th.userservice.controller;

import com.th.servicebase.exceptionhandler.CustomException;
import com.th.userservice.entity.User;
import com.th.userservice.entity.vo.UserVo;
import com.th.userservice.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.th.commonutils.ResponseResult;
import com.th.commonutils.ResultCode;

import javax.servlet.http.HttpServletRequest;

/**
 * @author cc
 * @date 2020-11-05-14:34
 */

@RestController
@RequestMapping("/userservice")
@Api(tags = {"登录注册接口"})
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("login")
    @ApiOperation(value = "登录接口")
    public ResponseResult login(@RequestBody UserVo userVo) {
        return loginService.login(userVo);
    }

    @PostMapping("register")
    @ApiOperation(value = "注册接口")
    public ResponseResult register(@RequestBody UserVo userVo) {
        return loginService.register(userVo);
    }

    @GetMapping("getUserInfo")
    @ApiModelProperty(value = "获取用户信息")
    public ResponseResult getUserInfo(HttpServletRequest request, String token) {
        User userInfo = loginService.getUserInfo(request, token);
        if (userInfo != null) {
            return ResponseResult.ok().data("userInfo", userInfo);
        } else {
            throw new CustomException(ResultCode.INVALID, "token已失效");
        }
    }
}
