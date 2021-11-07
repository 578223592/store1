package com.swx.store1.service.ex;

/**
 * Author: Admin
 * Date: 2021/11/7 19:05
 * FileName: AdressCountLimitException
 * Description:
 */
public class AddressCountLimitException extends ServiceException{
    public AddressCountLimitException() {
        super();
    }

    public AddressCountLimitException(String message) {
        super(message);
    }

    public AddressCountLimitException(String message, Throwable cause) {
        super(message, cause);
    }

    public AddressCountLimitException(Throwable cause) {
        super(cause);
    }

    protected AddressCountLimitException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
