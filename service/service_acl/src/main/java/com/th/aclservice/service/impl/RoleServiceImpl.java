package com.th.aclservice.service.impl;

import com.th.aclservice.entity.Role;
import com.th.aclservice.mapper.AclRoleMapper;
import com.th.aclservice.service.RoleService;
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
public class RoleServiceImpl extends ServiceImpl<AclRoleMapper, Role> implements RoleService {

}
