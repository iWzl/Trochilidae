package com.upuphub.trochilidae.web.exception;

import com.upuphub.trochilidae.core.exception.TrochilidaeRuntimeException;

/**
 * 请求的处理器映射没有找到
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2020-11-04 10:43
 **/
public class RequestMappingNotFindException extends TrochilidaeRuntimeException {
    public RequestMappingNotFindException(String message) {
        super(message);
    }
}
