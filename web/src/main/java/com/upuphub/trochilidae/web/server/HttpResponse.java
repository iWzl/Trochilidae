package com.upuphub.trochilidae.web.server;

import com.upuphub.trochilidae.web.serializer.Serializer;
import com.upuphub.trochilidae.web.serializer.impl.JacksonSerializer;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.util.AsciiString;

import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * Json的序列化器
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2020-10-21 10:09
 **/
public class HttpResponse {
    private static final AsciiString CONTENT_TYPE = AsciiString.cached("Content-Type");
    private static final AsciiString CONTENT_LENGTH = AsciiString.cached("Content-Length");
    private static final Serializer SERIALIZER;

    static {
        SERIALIZER = new JacksonSerializer();
    }

    public static FullHttpResponse build(Object resultType,String contentType) {
        byte[] content = SERIALIZER.serializeToByteArray(resultType);
        FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(content));
        response.headers().set(CONTENT_TYPE, "".equals(contentType) ? "*/*":contentType);
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
