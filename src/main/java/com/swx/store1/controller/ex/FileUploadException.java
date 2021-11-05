package com.swx.store1.controller.ex;

/** 文件上传相关异常的基类
 * 为什么在controller层定义异常：因为文件上传的异常和其他的异常不同，其他异常都是又dao，service产生，
 * 相当于流程是从下往上的，因此在service层进行捕获处理
 * 而文件上传异常是反着的，文件上传异常是在controller层就可以捕获到的，那就在controller层处理
 * */
public class FileUploadException extends RuntimeException {
    public FileUploadException() {
        super();
    }

    public FileUploadException(String message) {
        super(message);
    }

    public FileUploadException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileUploadException(Throwable cause) {
        super(cause);
    }

    protected FileUploadException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
