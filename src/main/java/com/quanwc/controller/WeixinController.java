package com.gelonghui.controller;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gelonghui.constant.MsgTypeConstant;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gelonghui.entity.TextMessage;
import com.gelonghui.util.CheckUtil;
import com.gelonghui.util.MessageUtil;

/**
 * Created by quanwenchao
 * 2018/6/1 16:49:09
 */
@RestController
//@RequestMapping("/weixin")
public class WeixinController {

    /**
     * 校验消息是否来自微信服务器
     * @return
     */
    @GetMapping(value = "/deal")
    public Object test1(String signature, String timestamp, String nonce, String echostr) {
        System.out.println(signature);
        System.out.println(timestamp);
        System.out.println(nonce);
        System.out.println(echostr);
        if(CheckUtil.checkSignature(signature, timestamp, nonce)) {
            return echostr;
        }
        return "ok";
    }


    /**
     * 消息的请求与响应
     * @return
     */
    @PostMapping(value = "/deal")
    public Object text2(HttpServletRequest request, HttpServletResponse response) throws IOException{

        //request.setCharacterEncoding("UTF-8");
        //response.setCharacterEncoding("UTF-8");

        try {
            Map<String, String> map = MessageUtil.xmlToMap(request);

            String toUserName = map.get("ToUserName");
            String fromUserName = map.get("FromUserName");
            String createTime = map.get("CreateTime");
            String msgType = map.get("MsgType");
            String content = map.get("Content");
            String msgId = map.get("MsgId");


            // 如果是文本消息，进行回复
            String message = null;
            if (MsgTypeConstant.MESSAGE_TEXT.equals(msgType)) { // 文本
                if ("1".equals(content)) {
                    message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.firstRepeat());
                } else if ("2".equals(content)) {
                    message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.secondRepeat());
                } else if ("?".equals(content) || "？".equals(content)) {
                    message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.menuText());
                }
            } else if (MsgTypeConstant.MESSAGE_EVNET.equals(msgType)) {
                String eventType = map.get("Event");
                if (MsgTypeConstant.MESSAGE_SUBSCRIBE.equals(eventType)) { // 关注后，回复一个主菜单
                    message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.menuText());
                }
            }


            System.out.println("message格式: " + message);

            return message;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }

        return null;

    }

}
