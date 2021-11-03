package com.swx.store1.service.ex;

/**
 * Author: Admin
 * Date: 2021/11/1 11:28
 * FileName: ServiceException
 * Description:service层exception的基类
 * 用法：一般throws new ServiceException("想报错的信息xxxx");
 */
public class ServiceException extends RuntimeException{
    public ServiceException() {
        super();
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    protected ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
