package com.gelonghui.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by quanwenchao
 * 2018/6/1 16:49:09
 */
@RestController
@RequestMapping("/weixin")
public class WeixinController {

    /**
     *
     * @return
     */
    @GetMapping(value = "/check")
    public Object test1() {

        return null;
    }

}
