package com.study.netty.thirdexample;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class MyChatClient6 {
    public static void main(String[] args) throws Exception {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class)
                    .handler(new MyChatClientInitializer());

            Channel channel = bootstrap.connect("localhost", 8899).sync().channel();
//            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int i = 0;
            for (; ; ) {
                Thread.sleep(5L);
                channel.writeAndFlush("说了的快捷方式来得及发上来的科技发生了地方了深刻的减肥了深刻的减肥塑料袋咖啡聚少离多快捷方式懒得看减肥塑料袋咖啡教室里打开房间塑料袋咖啡聚少离多会计分录速度快放假塑料袋咖啡聚少离多会计分录SDK就分手了打开房间塑料袋咖啡聚少离多会计分录速度快放假塑料袋咖啡机塑料袋咖啡聚少离多快捷方式懒得看就分手了打开房间塑料袋咖啡教室里快递费聚少离多扣几分塑料袋咖啡聚少离多快捷方式懒得看减肥" + i++ + "\r\n");
            }
        } finally {
            eventLoopGroup.shutdownGracefully();
        }
    }
}
