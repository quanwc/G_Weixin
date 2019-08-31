package com.quanwc.bean.weibo;

import lombok.Data;

import java.util.Date;

/**
 * @author quanwenchao
 * @date 2019/4/25 14:41:36
 */
@Data
public class Attitudes {
    private long id;
    private Date created_at;
    private String attitude;
    private String last_attitude;
    private String source;
    private Status status;

    private Integer total_number;
}



