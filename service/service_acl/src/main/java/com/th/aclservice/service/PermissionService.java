package com.th.aclservice.service;

import com.th.aclservice.entity.Permission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author cc
 * @since 2020-12-16
 */
public interface PermissionService extends IService<Permission> {

    // 根据用户id获取用户菜单
    List<String> selectPermissionValueByUserId(Integer id);
}
