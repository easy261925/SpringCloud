package com.th.userservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.th.servicebase.exceptionhandler.CustomException;
import com.th.userservice.entity.User;
import com.th.userservice.entity.vo.UserVo;
import com.th.userservice.mapper.UserMapper;
import com.th.userservice.service.LoginService;
import com.th.userservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import com.th.commonutils.JwtUtils;
import com.th.commonutils.MD5;
import com.th.commonutils.ResponseResult;
import com.th.commonutils.ResultCode;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author cc
 * @date 2020-11-05-14:44
 */

@Service
@Slf4j
public class LoginServiceImpl extends ServiceImpl<UserMapper, User> implements LoginService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private UserService userService;

    @Override
    public ResponseResult login(UserVo userVo) {
        String type = userVo.getType();
        if ("account".equals(type)) {
            String username = userVo.getUsername();
            String password = userVo.getPassword();
            if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
                throw new CustomException(ResultCode.FAILED, "用户名或密码不能为空");
            }
            QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
            userQueryWrapper.eq("username", username)
                    .eq("password", MD5.encrypt(password))
                    .eq("ban", 0);
            User currentUser = baseMapper.selectOne(userQueryWrapper);
            if (currentUser == null) {
                throw new CustomException(ResultCode.FAILED, "用户名密码错误");
            }
            String token = JwtUtils.getJwtToken(currentUser.getId().toString(), username);
            // 用户权限
            ArrayList<Object> currentAuthority = new ArrayList<>();
            return ResponseResult.ok().data("token", token).data("currentAuthority", currentAuthority);
        } else if ("mobile".equals(type)) {
            String phone = userVo.getPhone();
            String verifyCode = userVo.getVerifyCode();

            QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
            userQueryWrapper.eq("phone", phone)
                    .eq("ban", 0);
            User currentUser = baseMapper.selectOne(userQueryWrapper);
            if (currentUser == null) {
                throw new CustomException(ResultCode.FAILED, "该用户暂未注册");
            }
            String redisVerifyCode = redisTemplate.opsForValue().get(phone);
            if (!verifyCode.equals(redisVerifyCode)) {
                throw new CustomException(ResultCode.FAILED, "验证码错误");
            }
            String token = JwtUtils.getJwtToken(currentUser.getId().toString(), currentUser.getUsername());
            // 用户权限
            ArrayList<Object> currentAuthority = new ArrayList<>();
            return ResponseResult.ok().data("token", token).data("currentAuthority", currentAuthority);

        } else {
            throw new CustomException(ResultCode.FAILED, "登录失败");
        }
    }

    @Override
    public ResponseResult register(UserVo userVo) {
        String username = userVo.getUsername();
        String password = userVo.getPassword();
        String nickname = userVo.getNickname();
        String verifyCode = userVo.getVerifyCode();
        String phone = userVo.getPhone();
        if (StringUtils.isEmpty(username)
                || StringUtils.isEmpty(password)
                || StringUtils.isEmpty(nickname)
//                || StringUtils.isEmpty(verifyCode)
                || StringUtils.isEmpty(phone)
        ) {
            throw new CustomException(ResultCode.FAILED, "注册失败");
        }
        User user = new User();
        user.setBan(0);
        userVo.setPassword(MD5.encrypt(password));
        BeanUtils.copyProperties(userVo, user);
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("username", user.getUsername());
        List<User> users = baseMapper.selectList(userQueryWrapper);
        if (users.size() != 0) {
            throw new CustomException(ResultCode.FAILED, "该用户已注册");
        }
        String redisVerifyCode = redisTemplate.opsForValue().get(phone);

        if (!StringUtils.isEmpty(verifyCode) && !verifyCode.equals(redisVerifyCode)) {
            throw new CustomException(ResultCode.FAILED, "验证码错误");
        }
        baseMapper.insert(user);
        return ResponseResult.ok().message("注册成功");
    }

    @Override
    public User getUserInfo(HttpServletRequest request, String token) {
        String userId = JwtUtils.getUserIdByJwtToken(request, token);
        User userInfo = userService.getById(userId);
        if (null != userInfo) {
            return userInfo;
        } else {
            throw new CustomException(ResultCode.INVALID, "token 失效");
        }
    }
}
