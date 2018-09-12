package com.quanwc.handler;

import com.quanwc.bean.WxMessage;

/**
 * 抽象类Handler
 * @author quanwenchao
 * @date 2018/9/11 17:47:13
 */
public abstract class AbstractHandler implements IHandler{

    public abstract WxMessage handle(WxMessage message);
}
