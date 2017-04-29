package com.learn.util;

import org.apache.commons.lang3.StringUtils;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: liudi
 * Date: 17/4/29
 * Time: 下午22:54
 */
public class StringUtil {

    public static final String REGEX_EMAIL = "(\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*)";
    public static final String REGEX_TEL_NUM = "(1[345678][0-9０-９\\-－ 　]{9,11})|([0-9]{3,4}[\\- ][0-9０-９\\-－ 　]{7,8}|\\d{7,8})";
    public static final String REGEX_QQ_NUM = "(qq|扣扣).{0,3}[1-9０-９][0-9０-９\\s]{3,}[0-9０-９]";
    public static final String REGEX_URL_NUM = "([a-zA-z]+://[^\\s]*)|([/]*[\\w-]+\\.[\\w-]+\\.[\\w-]*)";
    public static final String REGEX_XSS = "[><].*(a |xml|img|script|alert|src|onload|body|iframe|input|link|meta|style|div|table|embed|base|href|object).*[><]*";
    public static final String DEFAULT_REPLACE_STR = "";
    public static final String DEFAULT_DELIMITER = ",";


    public static String filterEmail(String content) {
        return filterEmail(content, "***");
    }

    public static String filterEmail(String content, String replaceStr) {
        Pattern pattern = Pattern.compile(REGEX_EMAIL);
        Matcher m = pattern.matcher(content);
        return m.replaceAll(replaceStr);
    }

    public static String filterTelNumber(String content, String replaceStr) {
        Pattern pattern = Pattern.compile(REGEX_TEL_NUM);
        Matcher m = pattern.matcher(content);
        return m.replaceAll(replaceStr);
    }

    public static String filterTelNumber(String content) {
        return filterTelNumber(content, "***");
    }

    public static String filterQQNumber(String content, String replaceStr) {
        Pattern pattern = Pattern.compile(REGEX_QQ_NUM);
        Matcher m = pattern.matcher(content);
        return m.replaceAll(replaceStr);
    }

    public static String filterQQNumber(String content) {
        return filterQQNumber(content, "***");
    }

    public static String filterUrl(String content, String replaceStr) {
        Pattern pattern = Pattern.compile(REGEX_URL_NUM);
        Matcher m = pattern.matcher(content);
        return m.replaceAll(replaceStr);
    }

    public static String filterUrl(String content) {
        return filterUrl(content, "***");
    }

    public static String filerString(String content, String replaceStr, String... regex) {
        if (content != null && regex != null && regex.length > 0) {
            if (replaceStr == null) {
                replaceStr = DEFAULT_REPLACE_STR;
            }
            Pattern pattern;
            Matcher matcher;
            for (String val : regex) {
                pattern = Pattern.compile(val, Pattern.CASE_INSENSITIVE);
                matcher = pattern.matcher(content);
                content = matcher.replaceAll(replaceStr);
            }
        }
        return content;
    }

    public static List<String> eachAppend(List<String> list, String appendStr) {
        if (list != null && list.size() > 0) {
            List<String> result = new ArrayList<String>(list.size());
            for (String item : list) {
                result.add(item + appendStr);
            }
            return result;
        }
        return list;
    }


    public static String urlEncoder(String url) {
        try {
            url = URLEncoder.encode(url, "utf-8").replaceAll("%2C", ",").replaceAll("%2F", "/").replaceAll("%3A", ":").replaceAll("%3B", ";").replaceAll("%7B", "{").replaceAll("%22", "\"").replaceAll("%7D", "}").replaceAll("\\+", "%20");
        } catch (Exception ignore) {
        }
        return url;
    }

    public static String getEmailDomain(String email) {
        String[] strings = StringUtils.split(email, "@");
        if (strings.length == 2) {
            return strings[1].toLowerCase();
        }
        return "";
    }

    public static String generateKey(int length, boolean onlyNum) {
        char[] str;
        if (onlyNum) {
            str = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        } else {
            str = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        }
        StringBuilder result = new StringBuilder();
        int randomMaxRange = str.length;
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            result.append(str[random.nextInt(randomMaxRange)]);
        }
        return result.toString();
    }

    public static boolean checkRegex(String regex, String value) {
        if (CommonUtil.isEmpty(regex)) {
            return false;
        }
        Pattern pattern = Pattern.compile(regex);
        Matcher m = pattern.matcher(value);
        return m.find();
    }

    public static String removeSign(String string) {
        if (string == null) {
            return null;
        }
        return string.replaceAll("\\pP|\\pS", "");
    }

}
