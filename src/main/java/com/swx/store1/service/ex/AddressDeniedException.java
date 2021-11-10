package com.swx.store1.service.ex;

/**
 * Author: Admin
 * Date: 2021/11/8 17:59
 * FileName: AddressDeniedException
 * Description:
 */
public class AddressDeniedException extends ServiceException{
    public AddressDeniedException() {
        super();
    }

    public AddressDeniedException(String message) {
        super(message);
    }

    public AddressDeniedException(String message, Throwable cause) {
        super(message, cause);
    }

    public AddressDeniedException(Throwable cause) {
        super(cause);
    }

    protected AddressDeniedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
