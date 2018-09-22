package com.quanwc.handler.impl;

import com.quanwc.bean.WxMessage;
import com.quanwc.enumeration.MessageTypeEnum;
import com.quanwc.handler.AbstractHandler;

/**
 * text Handler
 * @author quanwenchao
 * @date 2018/9/11 17:50:07
 */
public class TextHandler extends AbstractHandler {

    @Override
    public WxMessage handle(WxMessage message) {
        String toUserName = message.getToUserName();
        String fromUserName = message.getFromUserName();
        String content = message.getContent();

        WxMessage result = null;
        if (content.contains("音乐")) {
            result = initText(toUserName, fromUserName, musicReplyText());
        } else {
            result = initText(toUserName, fromUserName, "nihaowa");
        }
        return result;
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

    /**
     * music reply
     * @return
     */
    private String musicReplyText() {
        StringBuffer sb = new StringBuffer();
        sb.append("Eason Chen");
        return sb.toString();
    }
}
