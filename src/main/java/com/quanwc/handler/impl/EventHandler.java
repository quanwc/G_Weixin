package com.quanwc.handler.impl;

import com.quanwc.bean.WxMessage;
import com.quanwc.handler.AbstractHandler;

/**
 * @author quanwenchao
 * @date 2018/10/27 12:53:52
 */
public class EventHandler extends AbstractHandler {
    @Override
    public WxMessage handle(WxMessage message) {
        return message;
    }
}
