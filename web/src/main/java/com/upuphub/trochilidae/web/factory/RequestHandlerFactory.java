package com.upuphub.trochilidae.web.factory;

import com.upuphub.trochilidae.web.handler.RequestHandler;
import com.upuphub.trochilidae.web.handler.impl.DeleteRequestHandler;
import com.upuphub.trochilidae.web.handler.impl.GetRequestHandler;
import com.upuphub.trochilidae.web.handler.impl.PostRequestHandler;
import com.upuphub.trochilidae.web.handler.impl.PutRequestHandler;
import io.netty.handler.codec.http.HttpMethod;

import java.util.HashMap;
import java.util.Map;

/**
 * 请求处理器创建工厂
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2020-10-20 16:57
 **/
public class RequestHandlerFactory {
    private static final Map<HttpMethod,RequestHandler> REQUEST_HANDLERS = new HashMap<>();

    static{
        REQUEST_HANDLERS.put(HttpMethod.GET,new GetRequestHandler());
        REQUEST_HANDLERS.put(HttpMethod.POST,new PostRequestHandler());
        REQUEST_HANDLERS.put(HttpMethod.PUT,new PutRequestHandler());
        REQUEST_HANDLERS.put(HttpMethod.DELETE,new DeleteRequestHandler());
    }

    public static RequestHandler get(HttpMethod method) {
        return REQUEST_HANDLERS.get(method);
    }
}
