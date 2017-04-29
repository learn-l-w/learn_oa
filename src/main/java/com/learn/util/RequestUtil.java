package com.learn.util;


import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * User: liudi
 * Date: 17/4/29
 * Time: 下午5:55
 */
public class RequestUtil {

    public static final String HEADER_SOURCE = "Wrm-Source";
    public static final String HEADER_REMOTE_IP = "remote-ip";
    public static final String HEADER_UNIQUE_ID = "UNIQUE-ID";

    /**
     * 从request的header中获取RemoteIp
     *
     * @return
     */
    public static String getRemoteIp() {
        return getValueFromHeader(HEADER_REMOTE_IP);
    }

    private static String getValueFromHeader(String key) {
        String result = "";
        HttpServletRequest request = getHttpServletRequest();
        if (request == null) {
            return result;
        }
        result = request.getHeader(key) == null ? "" : request.getHeader(key);

        return result;
    }

    public static String getRequestUrl() {
        StringBuffer result = new StringBuffer();
        HttpServletRequest request = getHttpServletRequest();
        if (request == null) {
            return result.toString();
        }
        result = request.getRequestURL();
        String queryStr = request.getQueryString();
        if (org.apache.commons.lang3.StringUtils.isNotEmpty(queryStr)) {
            result.append("?");
            result.append(queryStr);
        }
        return result.toString();
    }

    public static String getHeaderSource() {

        return getValueFromHeader(HEADER_SOURCE);
    }

    public static String getRequestMethod() {

        HttpServletRequest request = getHttpServletRequest();
        if (request == null) {
            return "null";
        }
        return request.getMethod();
    }

    private static HttpServletRequest getHttpServletRequest() {
        ServletRequestAttributes servletRequestAttributes = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes());
        if (servletRequestAttributes == null) {
            return null;
        }
        return servletRequestAttributes.getRequest();
    }
}
