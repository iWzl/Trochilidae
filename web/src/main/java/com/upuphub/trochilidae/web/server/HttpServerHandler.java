package com.upuphub.trochilidae.web.server;

import com.upuphub.trochilidae.web.common.lang.HttpMediaType;
import com.upuphub.trochilidae.web.common.util.UrlUtil;
import com.upuphub.trochilidae.web.exception.RequestMappingNotFindException;
import com.upuphub.trochilidae.web.factory.FullHttpResponseFactory;
import com.upuphub.trochilidae.web.factory.RequestHandlerFactory;
import com.upuphub.trochilidae.web.handler.RequestHandler;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Http Server Handler
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2020-10-19 16:29
 **/
public class HttpServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {
    private static final Logger logger = LoggerFactory.getLogger(HttpServerHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, FullHttpRequest fullHttpRequest) {
        long startTime = System.currentTimeMillis();
        String uri = fullHttpRequest.uri();
        if (uri.equals(HttpMediaType.FAVICON_ICO)) {
            return;
        }
        RequestHandler requestHandler = RequestHandlerFactory.get(fullHttpRequest.method());
        FullHttpResponse response;
        try {
            response = requestHandler.handle(fullHttpRequest);
        } catch (RequestMappingNotFindException e) {
            response = FullHttpResponseFactory.internalServerUrlNotFindError(e.getMessage());
            logger.error("handler request uri not find {}",e.getMessage());
        }catch (Exception e){
            response = FullHttpResponseFactory.internalServerError(e);
            logger.error("handler request error {}",e.getMessage(),e);
        }
        boolean keepAlive = HttpUtil.isKeepAlive(fullHttpRequest);
        if (!keepAlive) {
            channelHandlerContext.write(response).addListener(ChannelFutureListener.CLOSE);
        } else {
            response.headers().set(HttpMediaType.CONNECTION,HttpMediaType.KEEP_ALIVE);
            channelHandlerContext.write(response);
        }
        if(logger.isDebugEnabled()){
            logger.debug("handler request finished uri [{}] use [{}]ms",UrlUtil.getRequestPath(uri),System.currentTimeMillis() - startTime);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        logger.error("handler request exception caught {}",cause.getMessage(),cause);
        ctx.close();
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

}
