package com.benchmark.util;

/**
 * @author hejianglong
 * @Desc
 * @date 2018/12/22 上午12:33
 */
public class StringUtils {

    public static boolean isEmpty(String val) {
        return val == null || val.trim().length() == 0;
    }

    public static boolean isNotEmpty(String val) {
        return val != null && val.trim().length() > 0;
    }
}
