package com.learn.util;


import com.google.common.base.Joiner;

import javax.ws.rs.core.Cookie;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: liudi
 * Date: 17/4/29
 * Time: 下午5:54
 */
public class CommonUtil {
    /**
     * 判断对象是否为空
     *
     * @param obj if object is null, string/array/list object is null or its length/size equal 0, boolean object is false,  will return false
     * @return bool
     */
    public static boolean isEmpty(Object obj) {
        boolean result = true;
        if (obj == null) {
            return true;
        }
        if (obj instanceof String) {
            result = (obj.toString().trim().length() == 0) || obj.toString().trim().equals("null");
        } else if (obj instanceof Collection) {
            result = ((Collection) obj).size() == 0;
        } else if (obj.getClass().isArray()) {
            result = Array.getLength(obj) == 0;
        } else {
            result = (obj.toString().trim().length() < 1);
        }
        return result;
    }

    /**
     * if there is any empty(null or length equal 0) object in args,  will return true
     *
     * @param args
     * @return bool
     */
    public static boolean anyEmpty(Object... args) {
        if (args == null) {
            return true;
        }
        for (Object val : args) {
            if (isEmpty(val)) {
                return true;
            }
        }
        return false;
    }

    /**
     * if there is any empty or boolean false object in args,  will return true
     *
     * @param args
     * @return bool
     */
    public static boolean anyEmptyOrFalse(Object... args) {
        if (args == null) {
            return true;
        }
        for (Object val : args) {
            if (isEmpty(val)) {
                return true;
            } else if (val instanceof Boolean) {
                return !(Boolean) val;
            }
        }
        return false;
    }


    public static String cookieStr(Map<String, Cookie> cookies) {
        List<String> cookieStr = new ArrayList<String>(3);
        for (String key : cookies.keySet()) {
            cookieStr.add(String.format("%s=%s", key, cookies.get(key).getValue()));
        }
        return Joiner.on(";").join(cookieStr);
    }


}
