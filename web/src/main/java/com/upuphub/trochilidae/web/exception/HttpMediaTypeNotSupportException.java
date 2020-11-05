package com.upuphub.trochilidae.web.exception;

import com.upuphub.trochilidae.core.exception.TrochilidaeRuntimeException;

/**
 * 请求携带的正文内容的数据类型不支持
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2020-11-05 11:39
 **/
public class HttpMediaTypeNotSupportException extends TrochilidaeRuntimeException {
    public HttpMediaTypeNotSupportException(String message) {
        super(message);
    }
}
