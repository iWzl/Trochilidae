package com.upuphub.trochilidae.web.common.http;

/**
 * 服务器异常回包
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2020-11-05 18:06
 **/
public class InternalServerErrorRsp implements HttpResponse{
    private String message;
    private Throwable throwable;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }
}
