package com.quanwc.controller;

import com.quanwc.bean.weibo.Attitudes;
import com.quanwc.bean.weibo.Status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * weibo controller
 *
 * @author quanwenchao
 * @date 2019/4/25 14:25:25
 */
@Slf4j
@RestController
@RequestMapping("weibo")
public class WeiboController {

    /**
     * 赞列表
     *
     * @return
     */
    @GetMapping("attitudes/show/biz.json")
    public Object attitudesList(@RequestParam("access_token") String access_token, @RequestParam("id") Long id) {

        log.info("access_token={}, id={}", access_token, id);

        Attitudes attitudes = new Attitudes();
        attitudes.setId(id);
        attitudes.setCreated_at(new Date());

        Status status = new Status();
        status.setStatusCode(100);
        attitudes.setStatus(status);

        attitudes.setTotal_number(500);

        return attitudes;
    }
}
