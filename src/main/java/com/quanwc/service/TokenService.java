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

	private static final String TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token";

	private AccessToken accessToken;

	/**
	 * acquire token
	 * @return
	 */
	public String getAccessToken() {
		return accessToken.getAccess_token();
	}

	/**
	 * refresh token
	 */
	@PostConstruct
	private void refreshToken() {
		Response response = httpClient.get(TOKEN_URL,
				new NameValuePair[] {
						new NameValuePair("grant_type", "client_credential"),
						new NameValuePair("appid", weixinConfig.getAppId()),
						new NameValuePair("secret", weixinConfig.getAppSecret()) });

		log.info(response.getBody());

		ObjectMapper objectMapper = new ObjectMapper();
		try {
			AccessToken accessToken = objectMapper.readValue(response.getBody(),
					AccessToken.class);
			if (null != accessToken) {
				this.accessToken = accessToken;
				// 设置token的过期时间：
				this.accessToken.setExpires_in(this.accessToken.getExpires_in() * 1000
						+ System.currentTimeMillis());
			}
		}
		catch (IOException e) {
			log.error(e.getMessage());
		}
	}

	/**
	 * 定时任务刷新token: 20分钟检查一次；如果离失效时间小于30分钟就重新刷新
	 */
	@Scheduled(initialDelay = 10 * 1000, fixedRate = 20 * 60 * 1000)
	private void checkToken() {
		if (null == accessToken || null == accessToken.getAccess_token()
				|| accessToken.getAccess_token().isEmpty()) {
			refreshToken();
		}
		else if (null == accessToken.getExpires_in() || accessToken.getExpires_in()
				- System.currentTimeMillis() <= 30 * 60 * 1000) {
			refreshToken();
		}
	}

}
