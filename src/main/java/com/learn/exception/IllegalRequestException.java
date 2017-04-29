package com.learn.exception;


/**
 * Created with IntelliJ IDEA.
 * User: liudi
 * Date: 17-4-29
 * Time: 下午5:18
 * To change this template use File | Settings | File Templates.
 */
public class IllegalRequestException extends LearnException {

    public IllegalRequestException() {
        super(ErrorMessage.PARAMETER_ERROR);
    }

    public IllegalRequestException(String s) {
        super(s);
    }
}
