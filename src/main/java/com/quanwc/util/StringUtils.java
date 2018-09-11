package com.quanwc.util;

/**
 * string工具类
 * @author quanwenchao
 * @date 2018/9/10 11:15:35
 */
public class StringUtils {

    /**
     * 判断是否包含为空的内容
     * @param strings param数组
     * @return true包含空的内容; false不包含空的内容
     */
    public static boolean isAnyBlank(String... strings) {
        for (String string : strings) {
            if (null == string || string.trim().isEmpty()) {
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        System.out.println("hello");
        boolean bo = isAnyBlank("f4159fe97425e6101c61a8ef6e5e6ec2f8e3ed50", "1536548185", "395556332", "5249223848689139197", " ");
        System.out.println(bo);
    }

}
