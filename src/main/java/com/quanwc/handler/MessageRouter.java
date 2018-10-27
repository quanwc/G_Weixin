package com.quanwc.handler;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.quanwc.bean.WxMessage;
import com.quanwc.enumeration.MessageTypeEnum;
import com.quanwc.handler.impl.*;

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
		handlerMap.put(MessageTypeEnum.EVENT.getMessageType(), new EventHandler());
		handlerMap.put(MessageTypeEnum.IMAGE.getMessageType(), new ImageHandler());
		handlerMap.put(MessageTypeEnum.VOICE.getMessageType(), new VoiceHandler());
		handlerMap.put(MessageTypeEnum.VIDEO.getMessageType(), new VideoHandler());

		handlerMap.put(MessageTypeEnum.EVENT_SUBSCRIBE.getMessageType(),
				new EventSubscribeHandler());
		handlerMap.put(MessageTypeEnum.EVENT_UNSUBSCRIBE.getMessageType(),
				new EventUnsubscribeHandler());
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
				return handlerMap.get(message.getMsgType() + ":" + message.getEvent())
						.handle(message);
			}
			else {
				// common message
				return handlerMap.get(message.getMsgType()).handle(message);
			}
		}

		throw new IllegalArgumentException(
				"MsgType [" + message.getMsgType() + "]" + " unimplements!");
	}

}
