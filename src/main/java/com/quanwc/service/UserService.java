package com.quanwc.service;

import org.apache.commons.httpclient.NameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quanwc.http.HttpClient;
import com.quanwc.http.Response;

import lombok.extern.slf4j.Slf4j;

/**
 * user service
 * @author quanwenchao
 * @date 2018/9/22 17:09:40
 */
@Slf4j
@Service
public class UserService {

	@Autowired
	private TokenService tokenService;
	@Autowired
	private HttpClient httpClient;

	private static final String BLACKLIST_URL = "https://api.weixin.qq.com/cgi-bin/tags/members/getblacklist";
	private static final String USER_INFO_URL = "https://api.weixin.qq.com/cgi-bin/user/info";
	private static final String USER_LIST_URL = "https://api.weixin.qq.com/cgi-bin/user/get";

	/**
	 * 黑名单列表
	 * @return
	 */
	public String blackList() {
		String accessToken = tokenService.getAccessToken();
		ObjectMapper objectMapper = new ObjectMapper();

		try {
			String body = objectMapper.writeValueAsString(null);
			Response response = httpClient
					.post(BLACKLIST_URL,
							new NameValuePair[] {
									new NameValuePair("access_token", accessToken) },
							body);
			String responseBody = response.getBody();
			log.info("blackList: " + responseBody);
			return responseBody;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取用户基本信息
	 * @param openId
	 * @return
	 */
	public String getInfo(String openId) {
		if (null == openId) {
			return null;
		}

		String accessToken = tokenService.getAccessToken();
		Response response = httpClient.get(USER_INFO_URL,
				new NameValuePair[] { new NameValuePair("access_token", accessToken),
						new NameValuePair("openid", openId),
						new NameValuePair("lang", "zh_CN") });

		String responseBody = response.getBody();
		log.info("userInfo: " + responseBody);
		return responseBody;
	}

	/**
	 * 获取用户列表
	 * @return
	 */
	public String listUser() {

		String accessToken = tokenService.getAccessToken();
		Response response = httpClient.get(USER_LIST_URL,
				new NameValuePair[] { new NameValuePair("access_token", accessToken) });

		String responseBody = response.getBody();
		log.info(responseBody);
		return responseBody;
	}
}
