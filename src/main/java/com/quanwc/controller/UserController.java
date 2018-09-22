package com.quanwc.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * 用户
 * Created by quanwenchao
 * 2018/6/9 14:04:42
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

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




}
