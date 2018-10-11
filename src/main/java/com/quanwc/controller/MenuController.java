package com.quanwc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import com.quanwc.bean.Menu;
import com.quanwc.service.MenuService;

import lombok.extern.slf4j.Slf4j;

/**
 * 自定义菜单
 * @author quanwenchao
 * @date 2018/9/29 17:47:50
 */
@Slf4j
@Service
@RequestMapping("/menu")
public class MenuController {

	@Autowired
	private MenuService menuService;

	/**
	 * 查询菜单
	 */
	@GetMapping(value = "/get")
	public String getMenu() {
		return menuService.getMenu();
	}

    /**
     * 获取自定义菜单配置：
     *      与自定义菜单查询接口的不同之处在于，本接口无论公众号的接口是如何设置的，都能查询到接口；// 菜单的创建方式：编辑模式、开发者模式
     *      而自定义菜单查询接口则仅能查询到使用API设置的菜单配置
     * @return
     */
    @GetMapping(value = "/current-self-menu")
    public String getCurrentSelfMenu() {
        return menuService.getCurrentSelfMenu();
    }

	/**
	 * 创建菜单
	 */
	@PostMapping(value = "/create")
	public String createMenu(@RequestBody Menu menu) {
		log.info("create menu: " + menu);
		return menuService.createMenu(menu);
	}

    /**
     * 删除菜单
     */
    @DeleteMapping(value = "/remove")
    public String removeMenu() {
        return menuService.removeMenu();
    }

}