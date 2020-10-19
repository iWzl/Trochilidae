package com.upuphub.trochilidae.web.handler;

import io.netty.handler.codec.http.FullHttpRequest;

/**
 * 请求处理器
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2020-10-19 16:38
 **/
public interface RequestHandler {
    /**
     * Http请求的处理
     *
     * @param fullHttpRequest Http请求
     * @return 处理的返回结果
     */
    Object handle(FullHttpRequest fullHttpRequest);
}
