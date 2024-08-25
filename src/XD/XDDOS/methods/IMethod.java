package XD.XDDOS.methods;

import io.netty.channel.Channel;
import java.util.function.BiConsumer;

import XD.XDDOS.utils.proxy.ProxyLoader;

public interface IMethod extends BiConsumer<Channel, ProxyLoader.Proxy> {}
