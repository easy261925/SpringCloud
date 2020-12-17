package com.th.aclservice.service.impl;

import com.th.aclservice.entity.Permission;
import com.th.aclservice.entity.User;
import com.th.aclservice.mapper.AclPermissionMapper;
import com.th.aclservice.service.PermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.th.aclservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author cc
 * @since 2020-12-16
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<AclPermissionMapper, Permission> implements PermissionService {

    @Autowired
    private UserService userService;

    @Override
    public List<String> selectPermissionValueByUserId(Integer id) {
        List<String> selectPermissionValueList = null;
        if (this.isSysAdmin(id.toString())) {
            //如果是系统管理员，获取所有权限
//            selectPermissionValueList = baseMapper.selectAllPermissionValue();
        } else {
//            selectPermissionValueList = baseMapper.selectPermissionValueByUserId(id);
        }
        return selectPermissionValueList;
    }

    /**
     * 判断用户是否系统管理员
     *
     * @param userId
     * @return
     */
    private boolean isSysAdmin(String userId) {
        User user = userService.getById(userId);

        if (null != user && "admin".equals(user.getUsername())) {
            return true;
        }
        return false;
    }
}
