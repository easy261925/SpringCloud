package com.th.userservice.service;

import com.th.userservice.entity.User;
import com.th.userservice.entity.vo.UserVo;
import com.th.commonutils.ResponseResult;

import javax.servlet.http.HttpServletRequest;

/**
 * @author cc
 * @date 2020-11-05-14:44
 */
public interface LoginService {
    ResponseResult login(UserVo userVo);

    ResponseResult register(UserVo userVo);

    User getUserInfo(HttpServletRequest request, String token);
}
