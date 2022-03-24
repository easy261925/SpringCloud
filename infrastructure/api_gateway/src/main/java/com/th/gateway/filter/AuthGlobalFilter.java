package com.th.gateway.filter;

import com.google.gson.JsonObject;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import com.th.commonutils.JwtUtils;
import com.th.commonutils.ResultCode;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * 全局 filter,统一处理需要 token 接口与外部不允许访问的服务
 *
 * @author cc
 * @date 2020-12-10-下午8:51
 */
@Component
public class AuthGlobalFilter implements GlobalFilter, Ordered {

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getURI().getPath();

        // api 接口，校验用户必须登录
        if (antPathMatcher.match("/dicservice/**", path)
                || antPathMatcher.match("/aclservice/**", path)
                || antPathMatcher.match("/userservice/getUserInfo", path)
        ) {
            List<String> tokenList = request.getHeaders().get("token");
            if (null == tokenList) {
                ServerHttpResponse response = exchange.getResponse();
                return out(response);
            } else {
                boolean isCheck = JwtUtils.checkToken(tokenList.get(0));
                if (!isCheck) {
                    ServerHttpResponse response = exchange.getResponse();
                    return out(response);
                }
            }
        }

        // 内部服务接口，不允许外部访问
//        if (antPathMatcher.match("/**/inner/**", path)) {
//            ServerHttpResponse response = exchange.getResponse();
//            return out(response);
//        }

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }

    private Mono<Void> out(ServerHttpResponse response) {
        JsonObject message = new JsonObject();
        message.addProperty("success", false);
        message.addProperty("code", ResultCode.INVALID);
        message.addProperty("message", "访问令牌失效，请重新登录");
        byte[] bits = message.toString().getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = response.bufferFactory().wrap(bits);
         response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        return response.writeWith(Mono.just(buffer));
    }
}
