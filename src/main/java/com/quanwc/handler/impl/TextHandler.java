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
		if (content.equals("id")) {
			result = initText(toUserName, fromUserName, "openId: " + fromUserName);
		}
		else if (content.contains("音乐") || content.contains("music")) {
			result = initText(toUserName, fromUserName, musicReplyText());
		}
		else if (content.contains("阅读") || content.contains("read")) {
			result = initText(toUserName, fromUserName, musicReplyText());
		}
		else if (content.contains("篮球") || content.contains("basketball")) {
			result = initText(toUserName, fromUserName, musicReplyText());
		}
		else {
			result = initText(toUserName, fromUserName, defaultReplyText());
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
		wxMessage.setCreateTime(System.currentTimeMillis() / 1000);
		return wxMessage;
	}

	/**
	 * music reply
	 * @return
	 */
	private String musicReplyText() {
		StringBuffer sb = new StringBuffer();
		sb.append("http://pianke.me/pages/radio/radioType.html?type=4");
		return sb.toString();
	}

	/**
	 * read reply
	 * @return
	 */
	private String readReplyText() {
		StringBuffer sb = new StringBuffer();
		sb.append("http://pianke.me/pages/read/read.html");
		return sb.toString();
	}

	/**
	 * basketball reply
	 * @return
	 */
	private String basketballReplyText() {
		StringBuffer sb = new StringBuffer();
		sb.append("https://nba.hupu.com/");
		return sb.toString();
	}

	/**
	 * default reply
	 * @return
	 */
	private String defaultReplyText() {
		StringBuffer sb = new StringBuffer();
		sb.append("no match content!\n\n");
		sb.append("try: id: music、read、basketball");
		return sb.toString();
	}
}
