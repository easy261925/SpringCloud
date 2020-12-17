package com.th.aclservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.th.aclservice.entity.User;
import com.th.aclservice.mapper.AclUserMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.th.aclservice.service.UserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author cc
 * @since 2020-12-16
 */
@Service
public class UserServiceImpl extends ServiceImpl<AclUserMapper, User> implements UserService {

    @Override
    public User selectByUsername(String username) {
        return baseMapper.selectOne(new QueryWrapper<User>().eq("username", username));
    }
}
