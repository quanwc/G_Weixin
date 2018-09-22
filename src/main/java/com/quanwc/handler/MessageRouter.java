package com.quanwc.handler;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.quanwc.bean.WxMessage;
import com.quanwc.enumeration.MessageTypeEnum;
import com.quanwc.handler.impl.SubscribeHandler;
import com.quanwc.handler.impl.TextHandler;

/**
 * message router
 * @author quanwenchao
 * @date 2018/9/11 17:53:52
 */
@Component
public class MessageRouter {

    private Map<String, IHandler> handlerMap = new HashMap<>();

    @PostConstruct
    public void init() {
        handlerMap.put(MessageTypeEnum.TEXT.getMessageType(), new TextHandler());
        handlerMap.put(MessageTypeEnum.SUBSCRIBE.getMessageType(), new SubscribeHandler());
    }

    /**
     * 根据msgType，router到Handler处理消息
     * @param message
     * @return
     */
    public WxMessage router(WxMessage message) {
        String msgType = message.getMsgType();
        if (null != handlerMap && handlerMap.containsKey(msgType)) {
            if (MessageTypeEnum.EVENT.getMessageType().equals(msgType)) {
                // event message
                return handlerMap.get(message.getEvent()).handle(message);
            } else {
                // common message
                return handlerMap.get(message.getMsgType()).handle(message);
            }
        }

        throw new IllegalArgumentException("MsgType [" + message.getMsgType() + "]" + " unimplements!");
    }

}
