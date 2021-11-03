package com.swx.store1.service.ex;

/**
 * Author: Admin
 * Date: 2021/11/3 12:16
 * FileName: UserNameNotExist
 * Description:
 */
public class UserNameNotExistException extends ServiceException{
    public UserNameNotExistException() {
        super();
    }

    public UserNameNotExistException(String message) {
        super(message);
    }

    public UserNameNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNameNotExistException(Throwable cause) {
        super(cause);
    }

    protected UserNameNotExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}


