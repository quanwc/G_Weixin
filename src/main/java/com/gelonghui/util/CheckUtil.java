package com.gelonghui.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * 校验的工具类
 * Created by quanwenchao
 * 2018/6/2 9:52:21
 */
public class CheckUtil {

    private static final String token = "token_gelonghui";

    public static boolean checkSignature(String signature, String timestamp, String nonce) {

        String [] arr = new String[]{token, timestamp, nonce};

        // 排序
        Arrays.sort(arr);

        // 生成字符串
        StringBuffer sb = new StringBuffer();
        for (String str : arr) {
            sb.append(str);
        }

        // sha1加密
        String temp = getSha1(sb.toString());
        System.out.println(temp);

        return temp.equals(signature); // 将加密后的内容，与微信传过来的signature比较


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

}
