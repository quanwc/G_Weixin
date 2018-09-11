package com.quanwc.util;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.quanwc.domain.entity.TextMessage;
import com.thoughtworks.xstream.XStream;

/**
 * 消息的工具类
 * Created by quanwenchao
 * 2018/6/2 10:41:52
 */
public class WeixinUtils {

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



    public static boolean checkSignature(String signature, String timestamp, String nonce, String token) {
        String [] arr = new String[]{token, timestamp, nonce};
        // 排序
        Arrays.sort(arr);

        // 生成字符串
        StringBuffer sb = new StringBuffer();
        for (String str : arr) {
            sb.append(str);
        }
        System.out.println(sb.toString());
        // sha1加密
        String sign = getSha1(sb.toString());
        System.out.println(sign);

        // 将加密后的内容，与微信传过来的signature比较
        return sign.equals(signature);
    }


    /**
     * sha1加密
     * @param str
     * @return
     */
    private static String getSha1(String str){
        if (null == str || 0 == str.length()){
            return null;
        }
        char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
            mdTemp.update(str.getBytes("UTF-8"));

            byte[] md = mdTemp.digest();
            int j = md.length;
            char[] buf = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                buf[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(buf);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }


    /*public static boolean checkSignature2(String signature, String timestamp, String nonce, String token){
        String[] arr = new String[] {token, timestamp, nonce};
        Arrays.sort(arr);
        StringBuilder content = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            content.append(arr[i]);
        }

        String sign = getSHA1String2(content.toString());

        return sign.equals(signature);
    }

    private static String getSHA1String2(String data){
        return DigestUtils.sha1Hex(data);
    }*/

}
