package com.quanwc.handler;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.quanwc.bean.WxMessage;
import com.quanwc.enumeration.MessageTypeEnum;
import com.quanwc.handler.impl.TextHandler;

/**
 * 消息的router路由转发
 * @author quanwenchao
 * @date 2018/9/11 17:53:52
 */
@Component
public class MessageRouter {

    private Map<String, IHandler> handlerMap = new HashMap<>();

    @PostConstruct
    public void init() {
        handlerMap.put(MessageTypeEnum.TEXT.getMessageType(), new TextHandler());
    }

    /**
     * 根据msgType，路由到对应的Handler处理消息
     * @param message
     * @return
     */
    public WxMessage router(WxMessage message) {

        if (null != handlerMap && handlerMap.containsKey(message.getMsgType())) {
            return handlerMap.get(message.getMsgType()).handle(message);
        }

        throw new IllegalArgumentException("MsgType [" + message.getMsgType() + "]" + " unimplements!");
    }

}
