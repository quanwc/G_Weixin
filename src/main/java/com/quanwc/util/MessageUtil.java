package com.quanwc.util;

import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.quanwc.entity.TextMessage;
import com.thoughtworks.xstream.XStream;

/**
 * 消息的工具类
 * Created by quanwenchao
 * 2018/6/2 10:41:52
 */
public class MessageUtil {

    /**
     * xml转为map集合
     *      注：xml是微信发给我们的，所以在request对象中获取
     * @param request
     * @return
     * @throws Exception
     */
    public static Map<String, String> xmlToMap(HttpServletRequest request) throws Exception{
        Map<String,String> map = new HashMap<>();

        SAXReader reader = new SAXReader();

        InputStream in = request.getInputStream();
        Document document = reader.read(in);

        Element root = document.getRootElement();

        List<Element> list = root.elements();

        for (Element e : list) {
            map.put(e.getName(), e.getText());
        }

        in.close();

        return map;
    }

    /**
     * 将TextMessage对象转为xml
     * @param textMessage
     * @return
     */
    public static String textMessageToXml(TextMessage textMessage) {
        XStream xStream = new XStream();
        xStream.alias("xml", TextMessage.class); // 将xml的根节点，转换为xml
        return xStream.toXML(textMessage);
    }


    /**
     * 主菜单
     * @return
     */
    public static String menuText() {
        StringBuffer sb = new StringBuffer();
        sb.append("欢迎关注，按照菜单提示进行操作: \n\n");
        sb.append("1：篮球\n");
        sb.append("2：足球\n\n");
        sb.append("?、？调出此菜单");
        return sb.toString();
    }


    /**
     * 拼接文本消息
     * @return
     */
    public static String initText(String toUserName, String fromUserName, String content) {
        // 创建文本消息对象
        TextMessage textMessage = new TextMessage();
        textMessage.setFromUserName(toUserName);
        textMessage.setToUserName(fromUserName);
        textMessage.setMsgType("text");
        textMessage.setCreateTime(new Date().getTime() + "");
        textMessage.setCreateTime("content: " + content);
        return textMessageToXml(textMessage);
    }


    public static String firstRepeat() {
        StringBuffer sb = new StringBuffer();
        sb.append("篮球basketball");
        return sb.toString();
    }

    public static String secondRepeat() {
        StringBuffer sb = new StringBuffer();
        sb.append("足球football");
        return sb.toString();
    }

}
