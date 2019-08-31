package com.quanwc.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quanwc.bean.dingtalk.TextMessage;
import com.quanwc.http.HttpClient;
import com.quanwc.http.Response;
import org.apache.commons.httpclient.NameValuePair;

/**
 * 钉钉util
 * @author quanwenchao
 * @date 2019/5/13 11:42:43
 */
public class DingTalkUtil {
//    private static HttpClient httpClient = new HttpClient();

    /**
     * 发送文本消息到钉钉
     *
     * @param webhookUrl
     * @param accessToken
     * @param textMessage
     *            文本消息对象
     * @return
     */
    public static Response sendTextMessage(String webhookUrl, String accessToken, TextMessage textMessage) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Response response = HttpClient.post(webhookUrl,
                new NameValuePair[] {new NameValuePair("access_token", accessToken)}, objectMapper.writeValueAsString(textMessage));
            return response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
