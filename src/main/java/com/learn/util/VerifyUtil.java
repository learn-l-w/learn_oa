package com.learn.util;


import com.learn.exception.ErrorMessage;
import com.learn.exception.IllegalRequestException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: liudi
 * Date: 17/4/29
 * Time: 下午22:54
 */
public class VerifyUtil {

    private static final String REGEX_EMAIL = "(\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*)";
    private static final String REGEX_XSS = "[><].*(a |xml|img|script|alert|src|onload|body|iframe|input|link|meta|style|div|table|embed|base|href|object).*[><]*";
    private static String emailRegex = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
    private static Pattern emailPattern = Pattern.compile(emailRegex);
    private static String mobileRegex = "^$";
    private static Pattern mobilePattern = Pattern.compile(mobileRegex);

    public static void checkEmail(String email) {

        if (email.length() == 0) {
            throw new IllegalRequestException(ErrorMessage.PARAMETER_TYPE_ERROR);
        } else {
            Pattern pattern = Pattern.compile(StringUtil.REGEX_EMAIL);
            Matcher matcher = pattern.matcher(email);
            boolean matched = matcher.matches();
            if (!matched) {
                throw new IllegalRequestException(ErrorMessage.PARAMETER_TYPE_ERROR);
            }
        }

    }

    public static void checkMobile(String mobile) {
        if (mobile.length() < 5) {
            throw new IllegalRequestException(ErrorMessage.PARAMETER_TYPE_ERROR);
        }
//        else {
//            Matcher matcher = mobilePattern.matcher(mobile);
//            boolean matched = matcher.matches();
//            if (!matched){
//                throw new IllegalRequestException(ErrorMessage.ERROR_MOBILE);
//            }
//        }
    }

    public static void checkXssString(String content) {
        if (!CommonUtil.isEmpty(content)) {
            content = StringUtil.filerString(content, "", "\\s");
            Pattern pattern = Pattern.compile(StringUtil.REGEX_XSS, Pattern.CASE_INSENSITIVE);
            Matcher m = pattern.matcher(content);
            if (m.find()) {
                throw new IllegalRequestException(ErrorMessage.TEXT_ILLEGAL);
            }
            pattern = Pattern.compile("document\\.[a-z0-9_]{1,20}\\(", Pattern.CASE_INSENSITIVE);
            m = pattern.matcher(content);
            if (m.find()) {
                throw new IllegalRequestException(ErrorMessage.TEXT_ILLEGAL);
            }
        }
    }
}
