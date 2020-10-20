package com.upuphub.trochilidae.web.server;

import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.util.AsciiString;

import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * @author shuang.kou
 * @createTime 2020年09月26日 09:44:00
 **/
public class HttpResponse {
    private static final AsciiString CONTENT_TYPE = AsciiString.cached("Content-Type");
    private static final AsciiString CONTENT_LENGTH = AsciiString.cached("Content-Length");
    //private static final JacksonSerializer JSON_SERIALIZER;

    static {
       // JSON_SERIALIZER = new JacksonSerializer();
    }

    public static FullHttpResponse ok(Object o) {
        //byte[] content = JSON_SERIALIZER.serialize(o);
        byte[] content = null;
        FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(content));
        response.headers().set(CONTENT_TYPE, "application/json");
        response.headers().setInt(CONTENT_LENGTH, response.content().readableBytes());
        return response;
    }

//    public static FullHttpResponse internalServerError(String url, String message) {
//        ErrorResponse errorResponse = new ErrorResponse(INTERNAL_SERVER_ERROR.code(), INTERNAL_SERVER_ERROR.reasonPhrase(), message, url);
//        byte[] content = JSON_SERIALIZER.serialize(errorResponse);
//        FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, INTERNAL_SERVER_ERROR, Unpooled.wrappedBuffer(content));
//        response.headers().set(CONTENT_TYPE, "application/json");
//        response.headers().setInt(CONTENT_LENGTH, response.content().readableBytes());
//        return response;
//    }
}
