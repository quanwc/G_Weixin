package com.quanwc.bean;

import lombok.Data;

/**
 * 请求的响应消息
 * @author quanwenchao
 * @date 2018/10/11 16:25:28
 */
@Data
public class ResponseMessage {
    /**
     * 返回码
     */
    private String errcode;

    /**
     * 返回信息
     */
    private String errmsg;
}
