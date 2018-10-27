package com.quanwc.handler.impl;

import com.quanwc.bean.WxMessage;
import com.quanwc.enumeration.MessageTypeEnum;
import com.quanwc.handler.AbstractHandler;

/**
 * unsubscribe handler
 * @author quanwenchao
 * @date 2018/10/27 13:08:00
 */
public class EventUnsubscribeHandler extends AbstractHandler {

	@Override
	public WxMessage handle(WxMessage message) {
		String toUserName = message.getToUserName();
		String fromUserName = message.getFromUserName();
		String msgType = message.getMsgType();
		String event = message.getEvent();

		WxMessage result = initSubscribeText(toUserName, fromUserName,
				unSubscribeReplyText());
        System.out.println(result);
		return result;
	}

	/**
	 * reply text content
	 * @param toUserName
	 * @param fromUserName
	 * @param content
	 * @return
	 */
	private WxMessage initSubscribeText(String toUserName, String fromUserName,
			String content) {
		WxMessage wxMessage = new WxMessage();
		wxMessage.setFromUserName(toUserName);
		wxMessage.setToUserName(fromUserName);
		wxMessage.setMsgType(MessageTypeEnum.TEXT.toString());
		wxMessage.setContent(content);
		wxMessage.setCreateTime(System.currentTimeMillis() / 1000);
		return wxMessage;
	}

	/**
	 * unSubscribe reply
	 * @return
	 */
	private String unSubscribeReplyText() {
		StringBuffer sb = new StringBuffer();
		sb.append("Welcome next subscribe ！\n");
		return sb.toString();
	}

}
