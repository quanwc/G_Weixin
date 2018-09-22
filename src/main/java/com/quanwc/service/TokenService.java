package com.quanwc.service;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.apache.commons.httpclient.NameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quanwc.bean.AccessToken;
import com.quanwc.config.WeixinConfig;
import com.quanwc.http.HttpClient;
import com.quanwc.http.Response;

import lombok.extern.slf4j.Slf4j;

/**
 * token service
 * @author quanwenchao
 * @date 2018/9/22 17:09:57
 */
@Slf4j
@Service
public class TokenService {

    @Autowired
    private HttpClient httpClient;
    @Autowired
    private WeixinConfig weixinConfig;

    private static final String TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential";

    private AccessToken accessToken;


    /**
     * acquire token
     * @return
     */
    public String getToken() {
        return accessToken.getAccess_token();
    }

    /**
     * refresh token
     */
    @PostConstruct
    private void refreshToken() {
        Response response = httpClient.get(TOKEN_URL, new NameValuePair[]{
                new NameValuePair("grant_type", "client_credential"),
                new NameValuePair("appid", weixinConfig.getAppId()),
                new NameValuePair("secret", weixinConfig.getAppSecret())});

        log.info(response.getBody());

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            AccessToken accessToken = objectMapper.readValue(response.getBody(), AccessToken.class);
            if (null != accessToken) {
                //this.accessToken = accessToken;
                this.accessToken.setAccess_token(accessToken.getAccess_token());
                this.accessToken.setExpires_in(accessToken.getExpires_in());
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }


    /**
     * 定时任务刷新token
     */
    @Scheduled
    private void checkToken() {
        if (null == accessToken || null == accessToken.getAccess_token() || accessToken.getAccess_token().isEmpty()) {
            refreshToken();
        } else if (null == accessToken.getExpires_in() || accessToken.getExpires_in() < 1000) {
            refreshToken();
        }
    }

}
