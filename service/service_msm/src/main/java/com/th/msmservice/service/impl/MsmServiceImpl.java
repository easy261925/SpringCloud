package com.th.msmservice.service.impl;

import com.th.servicebase.exceptionhandler.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.th.commonutils.ResultCode;
import com.th.msmservice.service.MsmService;
import org.springframework.data.redis.core.RedisTemplate;
import com.th.msmservice.utils.RandomUtil;

import java.util.concurrent.TimeUnit;

@Service
public class MsmServiceImpl implements MsmService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public String sendVerifyCode(String phone) {
        if (StringUtils.isEmpty(phone)) {
            throw new CustomException(ResultCode.FAILED, "请输入手机号码");
        }
        String code = redisTemplate.opsForValue().get(phone);
        if (!StringUtils.isEmpty(code)) {
            return code;
        }
        code = RandomUtil.getFourBitRandom();
        redisTemplate.opsForValue().set(phone, code, 5, TimeUnit.MINUTES);
        return code;

        // aliyun 发送短信验证码
//        DefaultProfile profile =
//                DefaultProfile.getProfile("default", "LTAI4FvvVEWiTJ3GNJJqJnk7", "9st82dv7EvFk9mTjYO1XXbM632fRbG");
//        IAcsClient client = new DefaultAcsClient(profile);
//
//        //设置相关固定的参数
//        CommonRequest request = new CommonRequest();
//        //request.setProtocol(ProtocolType.HTTPS);
//        request.setMethod(MethodType.POST);
//        request.setDomain("dysmsapi.aliyuncs.com");
//        request.setVersion("2017-05-25");
//        request.setAction("SendSms");
//
//        //设置发送相关的参数
//        request.putQueryParameter("PhoneNumbers",phone); //手机号
//        request.putQueryParameter("SignName","签名名称"); //申请阿里云 签名名称
//        request.putQueryParameter("TemplateCode","SMS_180051135"); //申请阿里云 模板code
//        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(param)); //验证码数据，转换json数据传递
//
//        try {
//            //最终发送
//            CommonResponse response = client.getCommonResponse(request);
//            boolean success = response.getHttpResponse().isSuccess();
//            return success;
//        }catch(Exception e) {
//            e.printStackTrace();
//            return false;
//        }
    }
}
