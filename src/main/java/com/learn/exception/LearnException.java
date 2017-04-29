package com.learn.exception;

/**
 * Created with IntelliJ IDEA.
 * User: liudi
 * Date: 17/4/29
 * Time: 下午5:52
 */
public class LearnException extends RuntimeException {

    public LearnException() {
    }

    public LearnException(String message) {
        super(message);
    }

    public LearnException(String message, Throwable cause) {
        super(message, cause);
    }

    public LearnException(Throwable cause) {
        super(cause);
    }
}
