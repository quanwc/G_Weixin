package com.quanwc.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quanwc.service.UserService;

import lombok.extern.slf4j.Slf4j;

/**
 * user controller
 * @author quanwenchao
 * @date 2018/6/9 14:04:42
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * 测试接口
	 * @return
	 */
	@GetMapping(value = "/health")
	public String health() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		return sdf.format(date);
	}

	/**
	 * 黑名单列表
	 * @return
	 */
	@GetMapping(value = "/blackList")
	public String blackList() {
		return userService.blackList();
	}

	/**
	 * 获取用户基本信息
	 * @param openId
	 * @return
	 */
	@GetMapping(value = "/info")
	public String userInfo(@RequestParam(value = "openId") String openId) {
		return userService.getInfo(openId);
	}

	/**
	 * 获取用户列表
	 * @return
	 */
	@GetMapping(value = "/list")
	public String listUser() {
		return userService.listUser();
	}
}
