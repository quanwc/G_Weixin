package com.quanwc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.quanwc.aes.AesException;
import com.quanwc.aes.WXBizMsgCrypt;
import com.quanwc.bean.WxEncryptMessage;
import com.quanwc.bean.WxMessage;
import com.quanwc.config.WeixinConfig;
import com.quanwc.handler.MessageRouter;
import com.quanwc.util.StringUtils;
import com.quanwc.util.WeixinUtils;
import com.quanwc.util.XmlUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * Weixin Controller
 * @author quanwenchao
 * @date 2018/6/1 16:49:09
 */
@Slf4j
@RestController
@RequestMapping("/weixin")
public class WeixinController {

	@Autowired
	private WeixinConfig weixinConfig;
	@Autowired
	private MessageRouter messageRouter;
	private WXBizMsgCrypt wxBizMsgCrypt;

	/**
	 * 校验消息是否来自微信服务器
	 * @return
	 */
	@GetMapping(value = "/deal")
	public Object getMethod(@RequestParam("signature") String signature,
			@RequestParam("timestamp") String timestamp,
			@RequestParam("nonce") String nonce,
			@RequestParam("echostr") String echostr) {

		log.info("Accept check signature from weixin: [ {}, {}, {}, {}]", signature,
				timestamp, nonce, echostr);

		if (StringUtils.isAnyBlank(signature, timestamp, nonce, echostr)) {
			throw new IllegalArgumentException("Request param is error!");
		}

		if (!WeixinUtils.checkSignature(signature, timestamp, nonce,
				weixinConfig.getToken())) {
			throw new IllegalArgumentException("Signature failed!");
		}

		return echostr;
	}

	/**
	 * 消息的请求与响应:
	 * 成为开发者后，用户每次向公众号发送消息、或者产生自定义菜单、或产生微信支付订单等情况时，开发者填写的服务器配置URL将得到微信服务器推送过来的消息和事件，开发者可以依据自身业务逻辑进行响应，如回复消息。
	 *
	 * 该post方法处理请求消息：通过@RequestBody注解获取请求参数(文本内容、关注事件消息等)
	 * @return
	 */
	@PostMapping(value = "/deal")
	public Object postMethod(@RequestBody String requestBody,
			@RequestParam("signature") String signature,
			@RequestParam(name = "encrypt_type", required = false) String encType,
			@RequestParam(name = "msg_signature", required = false) String msgSignature,
			@RequestParam("timestamp") String timestamp,
			@RequestParam("nonce") String nonce) {

		log.info(
				"Accept request from weixin [signature=[{}], encType=[{}], msgSignature=[{}],"
						+ "timestamp=[{}], nonce=[{}], requestBody=[\n{}\n]]",
				signature, encType, msgSignature, timestamp, nonce, requestBody);

		if (!WeixinUtils.checkSignature(signature, timestamp, nonce,
				weixinConfig.getToken())) {
			throw new IllegalArgumentException("Signature failed!");
		}

		WxMessage wxMessage = null;
		if (null == encType) {
			// 明文逻辑
			wxMessage = XmlUtils.xmlToObject(WxMessage.class, requestBody);
			return XmlUtils.objectToXml(messageRouter.router(wxMessage));
		}
		else if ("aes".equals(encType)) {
			// 密文逻辑
			WxEncryptMessage encryptMessage = XmlUtils.xmlToObject(WxEncryptMessage.class,
					requestBody);
			try {
				if (null != encryptMessage && null != encryptMessage.getEncrypt()) {
					String decryptMsg = wxBizMsgCrypt.decryptMsg(msgSignature, timestamp,
							nonce, encryptMessage.getEncrypt());
					log.info(decryptMsg);
					// 未解密字串转Object
					wxMessage = XmlUtils.xmlToObject(WxMessage.class, requestBody);
					String encryptMsg = wxBizMsgCrypt.encryptMsg(
							XmlUtils.objectToXml(messageRouter.router(wxMessage)),
							timestamp, nonce);

					// 解密字串转Object
					wxMessage = XmlUtils.xmlToObject(WxMessage.class, encryptMsg);
					return XmlUtils.objectToXml(messageRouter.router(wxMessage));
				}
				else {
					throw new IllegalArgumentException("AesException error!");
				}
			}
			catch (AesException ex) {
				ex.printStackTrace();
				log.info(ex.getMessage());
				throw new IllegalArgumentException("AesException error!");
			}
		}
		return "Nothing";
	}

}
