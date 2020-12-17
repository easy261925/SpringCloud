package com.th.dicservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.th.commonutils.ResponseResult;
import com.th.commonutils.vo.CommonUserVo;

/**
 * @author cc
 * @date 2020-11-04-13:22
 */

@Component
@FeignClient(value = "service-acl", fallback = UserClientImpl.class)
public interface UserClient {

    @GetMapping("/aclservice/user")
    public ResponseResult getAll(
            @RequestParam("current") int current,
            @RequestParam("pageSize") int pageSize,
            @RequestParam("User") CommonUserVo commonUserVo
    );

}
