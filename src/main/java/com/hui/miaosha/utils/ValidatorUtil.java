package com.hui.miaosha.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: CarlChen
 * @Despriction: 校验util
 * @Date: Create in 18:14 2019\4\13 0013
 */
public class ValidatorUtil {

    private static final Pattern MOBLIE_REGEX = Pattern.compile("1\\d{10}");

    public static boolean isMobile(String mobile){
        if (StringUtils.isEmpty(mobile)){
            return false;
        }
        Matcher matcher = MOBLIE_REGEX.matcher(mobile);
        return matcher.matches();
    }

}
