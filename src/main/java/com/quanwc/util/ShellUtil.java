package com.quanwc.util;

import com.quanwc.bean.dingtalk.At;
import com.quanwc.bean.dingtalk.Text;
import com.quanwc.bean.dingtalk.TextMessage;
import com.quanwc.config.DingTalkConfig;
import com.quanwc.http.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * @author quanwenchao
 * @date 2019/6/21 15:12:50
 */
@Service
public class ShellUtil {
    private static final String WEBHOOK_URL = " https://oapi.dingtalk.com/robot/send";
    private static final String ACCESS_TOKEN = " 54773f0c840242e40eee3c51329c62657f5ced0116533422bfd017cea28a0cd8";
    private static final String AT_MOBILES = "13923819654";

    @Autowired
    private  DingTalkConfig dingTalkConfig;

    public  void notifyShell() {
        Process process = null;
        BufferedReader br = null;
        try {
            String shellPath = "/home/shell/a.sh";
            process = Runtime.getRuntime().exec(shellPath.toString());
            br = new BufferedReader(new InputStreamReader(process.getInputStream()));

            StringBuffer sb = new StringBuffer();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            String result = sb.toString();
            System.out.println("result: " + result);

            if (process != null) {
                int extValue = process.waitFor(); //返回码 0 表示正常退出 1表示异常退出
                if (0 == extValue) {
                    System.out.println("=============启动脚本-执行完毕！");
                } else {
                    System.out.println("=============启动脚本-执行异常！");
                }
            }

        } catch (Exception e) {
            TextMessage textMessage = new TextMessage();
            textMessage.setMsgtype("text");

            Text text = new Text();
            text.setContent("定时任务出异常了11111");
            textMessage.setText(text);

            At at = new At();
            at.setAtMobiles(dingTalkConfig.getAtMobiles());
            textMessage.setAt(at);

            Response response = DingTalkUtil.sendTextMessage(dingTalkConfig.getWebhookUrl(),
                    dingTalkConfig.getAccessToken(), textMessage);
            System.out.println("response: " + response);
        } finally {
            if (process != null) {
                process.destroy();
            }
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
