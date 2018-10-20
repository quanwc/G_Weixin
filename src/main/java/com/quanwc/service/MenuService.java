package com.quanwc.service;

import org.apache.commons.httpclient.NameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quanwc.bean.Menu;
import com.quanwc.bean.ResponseMessage;
import com.quanwc.constant.WxConstant;
import com.quanwc.http.HttpClient;
import com.quanwc.http.Response;

import lombok.extern.slf4j.Slf4j;

/**
 * 自定义菜单service
 * @author quanwenchao
 * @date 2018/10/11 16:02:57
 */
@Slf4j
@Service
public class MenuService {

	@Autowired
	private TokenService tokenService;
	@Autowired
	private HttpClient httpClient;

	private static final String GET_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/get";
	private static final String CREATE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create";
	private static final String REMOVE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/delete";
	private static final String GET_CURRENT_SELF_MENU_INFO = "https://api.weixin.qq.com/cgi-bin/get_current_selfmenu_info";

	/**
	 * 查询菜单
	 * @return
	 */
	public String getMenu() {
		String access_token = tokenService.getAccessToken();
		Response response = httpClient.get(GET_MENU_URL,
				new NameValuePair[] { new NameValuePair("access_token", access_token) });

		String responseBody = response.getBody();
		log.info(responseBody);

		ObjectMapper objectMapper = new ObjectMapper();

		// 判断菜单是否存在
		try {
			if (responseBody.contains("errcode")) {
				ResponseMessage responseMessage = objectMapper.readValue(responseBody,
						ResponseMessage.class);
				if (null != responseMessage && WxConstant.CODE_MENU_NOT_EXIST
						.equals(responseMessage.getErrcode())) {
					log.info("menu does not exist!");
					throw new RuntimeException("menu does not exist!");
				}
			}
		}
		catch (Exception e) {
			log.error(e.getMessage());
		}
		return responseBody;
	}

	/**
	 * 获取自定义菜单
	 * @return
	 */
	public String getCurrentSelfMenu() {
		String access_token = tokenService.getAccessToken();
		Response response = httpClient.get(GET_CURRENT_SELF_MENU_INFO,
				new NameValuePair[] { new NameValuePair("access_token", access_token) });
		log.info(response.getBody());
		return response.getBody();
	}

	/**
	 * 创建菜单
	 * @param menu
	 */
	public String createMenu(Menu menu) {
		if (null == menu) {
			return null;
		}
		String accessToken = tokenService.getAccessToken();
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			String body = objectMapper.writeValueAsString(menu);
			Response response = httpClient
					.post(CREATE_MENU_URL,
							new NameValuePair[] {
									new NameValuePair("access_token", accessToken) },
							body);
			String responseBody = response.getBody();
			log.info(responseBody);
			ResponseMessage responseMessage = objectMapper.readValue(responseBody,
					ResponseMessage.class);
			if (null != response
					&& WxConstant.CODE_SUCCESS.equals(responseMessage.getErrcode())) {
				log.info("create menu success!");
			}
			else {
				log.info("create menu fail!");
			}
			return responseMessage.getErrmsg();
		}
		catch (Exception e) {
			log.error(e.getMessage());
			throw new RuntimeException("create menu fail");
		}
	}

	/**
	 * 删除菜单
	 * @return
	 */
	public String removeMenu() {
		String access_token = tokenService.getAccessToken();
		Response response = httpClient.get(REMOVE_MENU_URL,
				new NameValuePair[] { new NameValuePair("access_token", access_token) });

		String responseBody = response.getBody();
		log.info(responseBody);

		ObjectMapper objectMapper = new ObjectMapper();

		try {
			ResponseMessage responseMessage = objectMapper.readValue(responseBody,
					ResponseMessage.class);
			if (null != responseMessage
					&& WxConstant.CODE_SUCCESS.equals(responseMessage.getErrcode())) {
				log.info("remove menu success!");
			}
			else {
				log.info("remove menu fail!");
			}
			return responseMessage.getErrmsg();
		}
		catch (Exception e) {
			log.error(e.getMessage());
			throw new RuntimeException("remove menu fail!");
		}
	}

}
