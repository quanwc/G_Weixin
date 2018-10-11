package com.quanwc.bean;

import lombok.Data;

/**
 * 按钮bean
 * @author quanwenchao
 * @date 2018/10/11 16:05:18
 */
@Data
public class Button {
    private String type;
    private String name;
    private String key;
    private Button[] sub_button;
    private String url;

    // 小程序
    private String appid;
    private String pagepath;
}
