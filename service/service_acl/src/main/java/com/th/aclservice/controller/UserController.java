package com.th.aclservice.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.th.aclservice.entity.User;
import com.th.aclservice.service.UserService;
import com.th.commonutils.ResponseResult;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author cc
 * @since 2020-12-16
 */
@RestController
@RequestMapping("/aclservice")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("user")
    @ApiOperation(value = "获取用户接口")
    public ResponseResult getAll(int current, int pageSize, User user) {
        Page<User> userPage = new Page(current, pageSize);
        IPage<User> page = userService.page(userPage, null);
        return ResponseResult.ok().data("list", page.getRecords()).data("total", page.getTotal());
    }

    @DeleteMapping("user/{id}")
    @ApiOperation(value = "删除用户接口")
    public ResponseResult deleteUser(@ApiParam(name = "id", value = "用户Id", required = true)
                                     @PathVariable Integer id) {
        return ResponseResult.ok().data("result", userService.removeById(id));
    }

    @PostMapping("user")
    @ApiOperation(value = "创建用户接口")
    public ResponseResult addUser(@RequestBody User user) {
        return ResponseResult.ok().data("result", userService.save(user));
    }

    @PutMapping("user")
    @ApiOperation(value = "修改用户接口")
    public ResponseResult updateUser(@RequestBody User user) {
        userService.updateById(user);
        return ResponseResult.ok();
    }
}

