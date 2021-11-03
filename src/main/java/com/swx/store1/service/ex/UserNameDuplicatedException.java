package com.swx.store1.service.ex;

/**
 * Author: Admin
 * Date: 2021/11/1 11:30
 * FileName: UserNameDuplicatedException
 * Description:
 */
public class UserNameDuplicatedException extends ServiceException{
    public UserNameDuplicatedException() {
        super();
    }

    public UserNameDuplicatedException(String message) {
        super(message);
    }

    public UserNameDuplicatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNameDuplicatedException(Throwable cause) {
        super(cause);
    }

    protected UserNameDuplicatedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
