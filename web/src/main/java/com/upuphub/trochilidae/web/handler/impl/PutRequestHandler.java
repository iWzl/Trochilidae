package com.upuphub.trochilidae.web.handler.impl;

import com.upuphub.trochilidae.web.common.entity.RequestMappingDetail;
import com.upuphub.trochilidae.web.common.lang.HttpMethod;
import com.upuphub.trochilidae.web.common.util.UrlUtil;
import com.upuphub.trochilidae.web.factory.RouteMethodMapper;
import com.upuphub.trochilidae.web.handler.RequestHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;

/**
 * Get请求的处理器
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2020-11-03 23:17
 **/
public class PutRequestHandler implements RequestHandler {
    @Override
    public FullHttpResponse handle(FullHttpRequest fullHttpRequest) {
        String requestUri = fullHttpRequest.uri();
        // get http request path
        String requestPath = UrlUtil.getRequestPath(requestUri);
        // get target request Mapping detail
        RequestMappingDetail requestMappingDetail = RouteMethodMapper.getRequestMappingDetail(requestPath, HttpMethod.PUT);
        return run(fullHttpRequest,requestMappingDetail);
    }
}
