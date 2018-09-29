package com.quanwc.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * Weixin Config
 * @author quanwenchao
 * @date 2018/8/18 18:53:05
 */
@Data
@Component
@ConfigurationProperties(prefix = "weixin")
public class WeixinConfig {
    private String appId;
    private String appSecret;
    private String token;
    private String aesKey;
}
