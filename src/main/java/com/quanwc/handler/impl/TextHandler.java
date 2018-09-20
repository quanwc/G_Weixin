package com.quanwc.handler.impl;

import com.quanwc.bean.WxMessage;
import com.quanwc.enumeration.MessageTypeEnum;
import com.quanwc.handler.AbstractHandler;

/**
 * 文本消息处理的Handler
 * @author quanwenchao
 * @date 2018/9/11 17:50:07
 */
public class TextHandler extends AbstractHandler {

    @Override
    public WxMessage handle(WxMessage message) {
        String toUserName = message.getToUserName();
        String fromUserName = message.getFromUserName();
        return initText(toUserName, fromUserName, "nihaowa");
    }

    /**
     * 初始化文本消息
     * @param toUserName
     * @param fromUserName
     * @param content
     * @return
     */
    public WxMessage initText(String toUserName, String fromUserName, String content) {
        // 创建微信消息对象
        WxMessage wxMessage = new WxMessage();
        wxMessage.setFromUserName(toUserName);
        wxMessage.setToUserName(fromUserName);
        wxMessage.setMsgType(MessageTypeEnum.TEXT.toString());
        wxMessage.setContent(content);
        wxMessage.setCreateTime(System.currentTimeMillis()/1000);

        return wxMessage;
    }
}
