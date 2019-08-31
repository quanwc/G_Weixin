package com.quanwc.bean.dingtalk;

import lombok.Data;

import java.util.List;

/**
 * @author quanwenchao
 * @date 2019/5/12 16:10:10
 */
@Data
public class At {
    /**
     * 可以通过群成员的绑定手机号来艾特具体的群成员
     */
    private List<String> atMobiles;
    /**
     * 是否艾特所有人
     */
    private boolean isAtAll;
}
