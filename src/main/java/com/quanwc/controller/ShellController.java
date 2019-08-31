package com.quanwc.controller;

import com.quanwc.util.ShellUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author quanwenchao
 * @date 2019/6/21 15:16:57
 */
@Slf4j
@RestController
@RequestMapping("/shell")
public class ShellController {

    @Autowired
    private ShellUtil shellUtil;

    /**
     * 测试接口
     * @return
     */
    @GetMapping(value = "/test")
    public String health() {
        shellUtil.notifyShell();
        return "true";
    }

}
