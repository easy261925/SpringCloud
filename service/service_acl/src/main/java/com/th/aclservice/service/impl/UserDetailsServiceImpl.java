//package com.th.aclservice.service.impl;
//
//import com.th.aclservice.entity.User;
//import com.th.aclservice.service.PermissionService;
//import com.th.aclservice.service.UserService;
//import com.th.serurity.entity.SecurityUser;
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//import java.util.List;
//
///**
// * 自定义userDetailsService - 认证用户详情
// *
// * @author cc
// * @date 2020-12-16-下午1:35
// */
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private PermissionService permissionService;
//
//    /***
//     * 根据账号获取用户信息
//     * @param username:
//     * @return: org.springframework.security.core.userdetails.UserDetails
//     */
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userService.selectByUsername(username);
//        if (null == user) {
//            throw new UsernameNotFoundException("用户名不存在");
//        }
//        // 返回UserDetails实现类
//        com.th.serurity.entity.User sUser = new com.th.serurity.entity.User();
//        BeanUtils.copyProperties(user, sUser);
//
//        // 查询用户权限
//        List<String> authorities = permissionService.selectPermissionValueByUserId(user.getId());
//        SecurityUser securityUser = new SecurityUser(sUser);
//        securityUser.setPermissionValueList(authorities);
//        return securityUser;
//    }
//}
