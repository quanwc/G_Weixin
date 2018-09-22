package com.quanwc.bean;

import lombok.Data;

/**
 * access token
 * @author quanwenchao
 * @date 2018/9/22 17:14:21
 */
@Data
public class AccessToken {
    private String access_token;
    private Long expires_in;
}
