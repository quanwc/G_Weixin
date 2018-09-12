package com.quanwc.handler;

import com.quanwc.bean.WxMessage;

/**
 * 接口Handler
 * @author quanwenchao
 * @date 2018/9/11 17:14:49
 */
public interface IHandler {

    /**
     * 处理方法
     * @param message 请求的消息对象
     * @return 返回的消息对象
     */
    WxMessage handle(WxMessage message);
}
