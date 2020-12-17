package com.th.dicservice.controller;


import com.th.dicservice.client.UserClient;
import com.th.dicservice.entity.Dic;
import com.th.dicservice.service.DicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.th.commonutils.ResponseResult;

import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author cc
 * @since 2020-11-04
 */
@RestController
@RequestMapping("/dicservice")
public class DicController {

    @Autowired
    private DicService dicService;

    @Autowired
    private UserClient userClient;

    @PostMapping("dic")
    public ResponseResult addDic(@RequestBody Dic dic) {
        ResponseResult all = userClient.getAll(1, 10, null);
        Map<String, Object> userData = all.getData();
        return ResponseResult.ok().data("result", dicService.save(dic)).data("userData", userData);
    }
}

