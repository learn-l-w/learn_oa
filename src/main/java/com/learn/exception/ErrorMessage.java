package com.learn.exception;

/**
 * Created with IntelliJ IDEA.
 * User: liudi
 * Date: 17/4/29
 * Time: 下午5:52
 */
public class ErrorMessage {

    //--400
    public static final String PARAMETER_TYPE_ERROR = "400|必填字段类型错误";
    public static final String PARAMETER_NOT_EXIST = "400|必填字段不存在";
    public static final String PARAMETER_OPTIONAL_ERROR = "400|可选字段值错误";
    public static final String PARAMETER_ERROR = "400|请求参数错误";
    public static final String PARAMETER_OUT_OF_RANGE = "400|参数值超过范围";
    public static final String PARAMETER_ILLEGAL_EXT = "400|不合法的文件扩展名";
    public static final String PERMISSION_DENIED = "403|没有相关权限";
    public static final String TEXT_ILLEGAL = "403|文本内容非法";
    public static final String REQUEST_IP_LIMIT = "403|请求IP受限";
    public static final String RESPONSE_NULL_DATA = "404|请求内容不存在";
    public static final String RESPONSE_NULL_FILE = "404|文件不存在";

    public static String fitErrorMessage(int code, String message) {
        return code + "|" + message;
    }
}
