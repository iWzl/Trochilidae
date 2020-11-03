package com.upuphub.trochilidae.web.server;

import com.upuphub.trochilidae.web.common.util.UrlUtil;
import com.upuphub.trochilidae.web.factory.RequestHandlerFactory;
import com.upuphub.trochilidae.web.handler.RequestHandler;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpUtil;
import io.netty.util.AsciiString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Http Server Handler
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2020-10-19 16:29
 **/
public class HttpServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {
    private static final String FAVICON_ICO = "/favicon.ico";
    private static final AsciiString CONNECTION = AsciiString.cached("Connection");
    private static final AsciiString KEEP_ALIVE = AsciiString.cached("keep-alive");

    private static final Logger logger = LoggerFactory.getLogger(HttpServerHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, FullHttpRequest fullHttpRequest) throws Exception {
        logger.debug("Handle http request:{}", fullHttpRequest);
        String uri = fullHttpRequest.uri();
        if (uri.equals(FAVICON_ICO)) {
            return;
        }
        RequestHandler requestHandler = RequestHandlerFactory.get(fullHttpRequest.method());
        Object result;
        FullHttpResponse response;
        try {
            result = requestHandler.handle(fullHttpRequest);
            response = HttpResponse.build(result,"");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            String requestPath = UrlUtil.getRequestPath(fullHttpRequest.uri());
            //response = HttpResponse.internalServerError(requestPath, e.toString());
            response = HttpResponse.build("result","");
        }
        boolean keepAlive = HttpUtil.isKeepAlive(fullHttpRequest);
        if (!keepAlive) {
            channelHandlerContext.write(response).addListener(ChannelFutureListener.CLOSE);
        } else {
            response.headers().set(CONNECTION, KEEP_ALIVE);
            channelHandlerContext.write(response);
        }
    }
}
