package com.quanwc.util;

import com.quanwc.bean.dingtalk.At;
import com.quanwc.bean.dingtalk.Text;
import com.quanwc.bean.dingtalk.TextMessage;
import com.quanwc.config.DingTalkConfig;
import com.quanwc.http.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author quanwenchao
 * @date 2019/5/13 18:59:56
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DingTalkUtilTest {

    @Autowired
    private DingTalkConfig dingTalkConfig;

    @Test
    public void testDingTalk() {

        TextMessage textMessage = new TextMessage();
        textMessage.setMsgtype("text");

        Text text = new Text();
        text.setContent("定时任务出异常了11111");
        textMessage.setText(text);

        At at = new At();
        at.setAtMobiles(dingTalkConfig.getAtMobiles());
        textMessage.setAt(at);

        try {
            Response response = DingTalkUtil.sendTextMessage(dingTalkConfig.getWebhookUrl(),
                    dingTalkConfig.getAccessToken(), textMessage);
            System.out.println(response.getBody());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}