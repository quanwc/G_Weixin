package com.quanwc.controller;

import com.quanwc.bean.Kiwi;
import com.quanwc.service.KiwiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 猕猴桃controller
 * @author quanwenchao
 * @date 2018/10/11 23:38:40
 */
@RestController
@RequestMapping("kiwi")
public class KiwiController {

    @Autowired
    private KiwiService kiwiService;

    public String save(Kiwi kiwi) {

        if (null == kiwi) {
            return "param is null";
        }
        kiwiService.save(kiwi);
        return null;
    }

    public String listKiwi() {
        return null;
    }

}
