package com.th.aclservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.th.aclservice.entity.User;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author cc
 * @since 2020-12-16
 */
public interface UserService extends IService<User> {
    // 从数据库中取出用户信息
    User selectByUsername(String username);
}
