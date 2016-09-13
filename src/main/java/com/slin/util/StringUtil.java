package com.slin.util;

import java.util.Random;

/**
 * Created by slin on 16/9/5.
 */
public class StringUtil {

    /**
     * 返回指定长度的随机数
     * @param length
     * @return
     */
    public static String getRandomString(int length)
    {
        String str = "_abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; ++i)
        {
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }
}
