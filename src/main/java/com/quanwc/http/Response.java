package com.quanwc.http;

import lombok.Data;

/**
 * response
 * @author quanwenchao
 * @date 2018/9/22 17:29:22
 */
@Data
public class Response {
    /**
     * 响应码
     */
    private Integer statusCode;

    /**
     * 响应结果
     */
    private String body;
}
