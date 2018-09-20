package com.quanwc.controller;

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

    @GetMapping(value = "/test")
    public String userInfo() {
        return new Date() + "";
    }


}
