package com.upuphub.trochilidae.web.server;

import com.upuphub.trochilidae.core.factory.ConfigurationFactory;
import com.upuphub.trochilidae.web.common.constant.SystemConstants;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 基于Netty的Http服务器
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2020-11-03 22:51
 **/
public class HttpServer {
    private final Logger logger = LoggerFactory.getLogger(HttpServer.class);

    private static final int DEFAULT_PORT = ConfigurationFactory.getDefaultConfig().getInt("trochilidae.web.port");


    public void start() {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            TrochilidaeChannelInitializer trochilidaeChannelInitializer = new TrochilidaeChannelInitializer();
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    // TCP默认开启了 Nagle 算法，该算法的作用是尽可能的发送大数据快，减少网络传输。TCP_NODELAY 参数的作用就是控制是否启用 Nagle 算法。
                    .childOption(ChannelOption.TCP_NODELAY, true)
                    // 是否开启 TCP 底层心跳机制
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    //表示系统用于临时存放已完成三次握手的请求的队列的最大长度,如果连接建立频繁，服务器处理创建新连接较慢，可以适当调大这个参数
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .handler(new LoggingHandler(LogLevel.DEBUG))
                    .childHandler(trochilidaeChannelInitializer);
            Channel ch = b.bind(DEFAULT_PORT).sync().channel();
            logger.info(SystemConstants.LOG_PORT_BANNER, DEFAULT_PORT);
            ch.closeFuture().sync();
        } catch (InterruptedException e) {
            logger.error("Trochilidae service startup abnormal", e);
        } finally {
            logger.error("Trochilidae service shutdown bossGroup and workerGroup");
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
