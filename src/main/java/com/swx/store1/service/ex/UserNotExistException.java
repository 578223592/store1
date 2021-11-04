package com.swx.store1.service.ex;

/**
 * Author: Admin
 * Date: 2021/11/3 12:16
 * FileName: UserNameNotExist
 * Description:
 */
public class UserNotExistException extends ServiceException{
    public UserNotExistException() {
        super();
    }

    public UserNotExistException(String message) {
        super(message);
    }

    public UserNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotExistException(Throwable cause) {
        super(cause);
    }

    protected UserNotExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}


