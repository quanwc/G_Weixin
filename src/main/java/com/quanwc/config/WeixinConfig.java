package com.quanwc.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Weixin Config
 * @author quanwenchao
 * @date 2018/8/18 18:53:05
 */
@Component
@ConfigurationProperties("weixin")
public class WeixinConfig {
    private String unitTime;
    private String visitCount;
}
