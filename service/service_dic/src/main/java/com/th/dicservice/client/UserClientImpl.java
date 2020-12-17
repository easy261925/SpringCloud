package com.th.dicservice.client;

import org.springframework.stereotype.Component;
import com.th.commonutils.ResponseResult;
import com.th.commonutils.vo.CommonUserVo;

/**
 * @author cc
 * @date 2020-11-04-14:28
 */

@Component
public class UserClientImpl implements UserClient {
    @Override
    public ResponseResult getAll(int current, int pageSize, CommonUserVo user) {
        return ResponseResult.error().message("获取userData失败");
    }
}
