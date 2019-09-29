package com.lollipop.spider.util;

public class CharacterUtil {


    /**
     * 把首字母变为大写
     *
     * @param name
     * @return
     */
    public static String captureName(String name) {
        char[] cs = name.toCharArray();
        if (cs[0] >= 97 && cs[0] <= 122)
            cs[0] -= 32;
        return String.valueOf(cs);
    }
}
