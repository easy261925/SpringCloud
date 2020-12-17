package com.th.aclservice.service.impl;

import com.th.aclservice.entity.UserRole;
import com.th.aclservice.mapper.AclUserRoleMapper;
import com.th.aclservice.service.UserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cc
 * @since 2020-12-16
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<AclUserRoleMapper, UserRole> implements UserRoleService {

}
