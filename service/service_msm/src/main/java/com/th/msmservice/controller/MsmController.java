package com.th.msmservice.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.th.commonutils.ResponseResult;
import com.th.msmservice.service.MsmService;

/**
 * @author cc
 * @date 2020-11-05-16:31
 */

@RestController
@RequestMapping("/msmservice")
@Api(tags = {"短信中心"})
public class MsmController {

    @Autowired
    private MsmService msmService;

    @PostMapping("sendMsg")
    @ApiOperation(value = "发送验证码")
    public ResponseResult sendMsg(@RequestBody String phone) {
        String code = msmService.sendVerifyCode(phone);
        return ResponseResult.ok().data("verifyCode", code);
    }
}
