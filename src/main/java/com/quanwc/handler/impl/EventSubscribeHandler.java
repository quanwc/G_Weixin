package com.quanwc.handler.impl;

import com.quanwc.bean.WxMessage;
import com.quanwc.enumeration.MessageTypeEnum;
import com.quanwc.handler.AbstractHandler;

import lombok.extern.slf4j.Slf4j;

/**
 * subscribe Handler
 * @author quanwenchao
 * @date 2018/9/22 15:45:24
 */
@Slf4j
public class EventSubscribeHandler extends AbstractHandler {

	@Override
	public WxMessage handle(WxMessage message) {
		log.info("subscribe handler : " + message.toString());
		String toUserName = message.getToUserName();
		String fromUserName = message.getFromUserName();
		String msgType = message.getMsgType();
		String event = message.getEvent();

		WxMessage result = initSubscribeText(toUserName, fromUserName,
				subscribeReplyText());
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
	 * subscribe reply
	 * @return
	 */
	private String subscribeReplyText() {
		StringBuffer sb = new StringBuffer();
		sb.append("来来来，欢迎关注活动观赏鱼！\n\n");
		sb.append("很高兴邂逅你，个人账号\n");
		sb.append("分享交流：读书、编程、句子、美食（猕猴桃）\n");
		sb.append("但不止这些，也开车。\n\n");
		sb.append("正常情况这里每周推送新文章。\n");
		sb.append(
				"往期文章戳 -->：<a href='https://mp.weixin.qq.com/mp/profile_ext?action=home&__biz=Mzg4NjAwMTQzMg==#wechat_redirect'>我的过去</a>\n");
		sb.append("初次见面，菜单栏里，有导航页");
		return sb.toString();
	}

}
