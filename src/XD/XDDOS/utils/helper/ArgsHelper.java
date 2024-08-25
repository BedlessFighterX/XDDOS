package XD.XDDOS.utils.helper;

import java.io.File;
import java.util.Scanner;

import XD.XDDOS.XDDOS;
import XD.XDDOS.utils.proxy.ProxyGen;
import XD.XDDOS.utils.proxy.ProxyLoader;

public class ArgsHelper {

    public static void UserFrndly(String[] args) throws Throwable {

        Scanner in = new Scanner(System.in);
        
        System.out.print("You server <IP:PORT>:\n" + XDDOS.GREEN_BOLD + ">> " + XDDOS.WHITE_BOLD);
        XDDOS.serverhp = in.nextLine();

        System.out.print("Server protocol (https://wiki.vg/Protocol_version_numbers):\n" + XDDOS.GREEN_BOLD + ">> "
                + XDDOS.WHITE_BOLD);
        XDDOS.protcolID = Integer.parseInt(in.nextLine());

        System.out.print("[" + XDDOS.RED_BOLD + "Methods" + XDDOS.WHITE_BOLD + "]" + "\n\n" + XDDOS.WHITE_BOLD +
                "[01]" + XDDOS.CYAN_BOLD + " BigHandshake\n" + XDDOS.WHITE_BOLD +
                "[02]" + XDDOS.CYAN_BOLD + " Bigpacket\n" + XDDOS.WHITE_BOLD +
                "[03]" + XDDOS.CYAN_BOLD + " Botjoiner\n" + XDDOS.WHITE_BOLD +
                "[04]" + XDDOS.CYAN_BOLD + " ChatSpam\n" + XDDOS.WHITE_BOLD +
                "[05]" + XDDOS.CYAN_BOLD + " ColorCrasher\n" + XDDOS.WHITE_BOLD +
                "[06]" + XDDOS.CYAN_BOLD + " CPUDowner\n" + XDDOS.WHITE_BOLD +
                "[07]" + XDDOS.CYAN_BOLD + " Doublejoin\n" + XDDOS.WHITE_BOLD +
                "[08]" + XDDOS.CYAN_BOLD + " EmptyNames\n" + XDDOS.WHITE_BOLD +
                "[09]" + XDDOS.CYAN_BOLD + " ExtremeJoin\n" + XDDOS.WHITE_BOLD +
                "[10]" + XDDOS.CYAN_BOLD + " ExtremeKiller\n" + XDDOS.WHITE_BOLD +
                "[11]" + XDDOS.CYAN_BOLD + " Handshake\n" + XDDOS.WHITE_BOLD +
                "[12]" + XDDOS.CYAN_BOLD + " InstantDowner\n" + XDDOS.WHITE_BOLD +
                "[13]" + XDDOS.CYAN_BOLD + " InvalidData\n" + XDDOS.WHITE_BOLD +
                "[14]" + XDDOS.CYAN_BOLD + " InvalidNames\n" + XDDOS.WHITE_BOLD +
                "[15]" + XDDOS.CYAN_BOLD + " InvalidSpoof\n" + XDDOS.WHITE_BOLD +
                "[16]" + XDDOS.CYAN_BOLD + " IPSpoofFFlood\n" + XDDOS.WHITE_BOLD +
                "[17]" + XDDOS.CYAN_BOLD + " Join\n" + XDDOS.WHITE_BOLD +
                "[18]" + XDDOS.CYAN_BOLD + " LegacyPing\n" + XDDOS.WHITE_BOLD +
                "[19]" + XDDOS.CYAN_BOLD + " LegitnameJoin\n" + XDDOS.WHITE_BOLD +
                "[20]" + XDDOS.CYAN_BOLD + " LocalHost\n" + XDDOS.WHITE_BOLD +
                "[21]" + XDDOS.CYAN_BOLD + " LongHost\n" + XDDOS.WHITE_BOLD +
                "[22]" + XDDOS.CYAN_BOLD + " LongNames\n" + XDDOS.WHITE_BOLD +
                "[23]" + XDDOS.CYAN_BOLD + " Memory\n" + XDDOS.WHITE_BOLD +
                "[24]" + XDDOS.CYAN_BOLD + " MOTD\n" + XDDOS.WHITE_BOLD +
                "[25]" + XDDOS.CYAN_BOLD + " nAntiBot\n" + XDDOS.WHITE_BOLD +
                "[26]" + XDDOS.CYAN_BOLD + " NettyDowner\n" + XDDOS.WHITE_BOLD +
                "[27]" + XDDOS.CYAN_BOLD + " Network\n" + XDDOS.WHITE_BOLD +
                "[28]" + XDDOS.CYAN_BOLD + " NewNullPing\n" + XDDOS.WHITE_BOLD +
                "[29]" + XDDOS.CYAN_BOLD + " NullPing\n" + XDDOS.WHITE_BOLD +
                "[30]" + XDDOS.CYAN_BOLD + " Ping\n" + XDDOS.WHITE_BOLD +
                "[31]" + XDDOS.CYAN_BOLD + " PingJoin\n" + XDDOS.WHITE_BOLD +
                "[32]" + XDDOS.CYAN_BOLD + " Query\n" + XDDOS.WHITE_BOLD +
                "[33]" + XDDOS.CYAN_BOLD + " Queue\n" + XDDOS.WHITE_BOLD +
                "[34]" + XDDOS.CYAN_BOLD + " QuitExceptions\n" + XDDOS.WHITE_BOLD +
                "[35]" + XDDOS.CYAN_BOLD + " Ram\n" + XDDOS.WHITE_BOLD +
                "[36]" + XDDOS.CYAN_BOLD + " RandomExceptions\n" + XDDOS.WHITE_BOLD +
                "[37]" + XDDOS.CYAN_BOLD + " RandomPacket\n" + XDDOS.WHITE_BOLD +
                "[38]" + XDDOS.CYAN_BOLD + " ServerFucker\n" + XDDOS.WHITE_BOLD +
                "[39]" + XDDOS.CYAN_BOLD + " Slapper\n" + XDDOS.WHITE_BOLD +
                "[40]" + XDDOS.CYAN_BOLD + " SmartBot\n" + XDDOS.WHITE_BOLD +
                "[41]" + XDDOS.CYAN_BOLD + " Spoof\n" + XDDOS.WHITE_BOLD +
                "[42]" + XDDOS.CYAN_BOLD + " TcpBypass\n" + XDDOS.WHITE_BOLD +
                "[43]" + XDDOS.CYAN_BOLD + " TcpHit\n" + XDDOS.WHITE_BOLD +
                "[44]" + XDDOS.CYAN_BOLD + " UltimateKiller\n" + XDDOS.WHITE_BOLD +
                "[45]" + XDDOS.CYAN_BOLD + " UltimateSmasher\n" + XDDOS.WHITE_BOLD +
                "[46]" + XDDOS.CYAN_BOLD + " UnexpectedPacket\n" + XDDOS.WHITE_BOLD +
                "[47]" + XDDOS.CYAN_BOLD + " WaterFallBypass\n" + XDDOS.WHITE_BOLD +
                "[48]" + XDDOS.CYAN_BOLD + " XDjoin\n" + XDDOS.WHITE_BOLD +
                "[49]" + XDDOS.CYAN_BOLD + " XDSpam\n" + XDDOS.WHITE_BOLD +
                "[50]" + XDDOS.CYAN_BOLD + " Aegis\n\n" + XDDOS.WHITE_BOLD +
                "[" + XDDOS.RED_BOLD + "Experimental Methods" + XDDOS.WHITE_BOLD + "]" + "\n\n" + XDDOS.WHITE_BOLD +
                "[01]" + XDDOS.CYAN_BOLD + " EmptyNames\n" + XDDOS.WHITE_BOLD +
                "[02]" + XDDOS.CYAN_BOLD + " UUIDCrash\n" + XDDOS.WHITE_BOLD +
                "[03]" + XDDOS.CYAN_BOLD + " BungeeDowner\n" + XDDOS.WHITE_BOLD +
                "[04]" + XDDOS.CYAN_BOLD + " BotRaid\n" + XDDOS.WHITE_BOLD +
                "[05]" + XDDOS.CYAN_BOLD + " TCPFlow\n" + XDDOS.WHITE_BOLD +
                "\n" +
                XDDOS.WHITE_BOLD + "[" + XDDOS.RED_BOLD + "INFO" + XDDOS.WHITE_BOLD
                + "] Default Method is \"NettyDowner\" and type full name of the Methods don't be LAZY\n"
                + XDDOS.WHITE_BOLD + "\n" +
                "TYPE NAME OF METHOD:" + XDDOS.GREEN_BOLD +
                "\n>> " + XDDOS.WHITE_BOLD);

        XDDOS.methodID = in.nextLine().toLowerCase();
        System.out.print("Time (secons):\n" + XDDOS.GREEN_BOLD + ">> " + XDDOS.WHITE_BOLD);
        XDDOS.duration = Integer.parseInt(in.nextLine());

        System.out.print("Enter netty Threads to use:\n" + XDDOS.GREEN_BOLD + ">> " + XDDOS.WHITE_BOLD);
        XDDOS.nettyThreads = Integer.parseInt(in.nextLine());
        System.out.print("Enter loop Threads to use:\n" + XDDOS.GREEN_BOLD + ">> " + XDDOS.WHITE_BOLD);
        XDDOS.loopThreads = Integer.parseInt(in.nextLine());
        in.close();
        System.out.println("\n" + XDDOS.GREEN_BOLD + "[" + XDDOS.RED_BOLD + "XDDOS" + XDDOS.GREEN_BOLD + "]"
                + XDDOS.WHITE_BOLD + " Starting...\n" + XDDOS.RESET);
        XDDOS.proxies = (new ProxyGen(new File("proxies.txt"))).load();
        XDDOS.run();
    }

    public static void OneLine(String[] args) throws Throwable {

        XDDOS.serverhp = args[0];
        XDDOS.protcolID = Integer.parseInt(args[1]);
        XDDOS.methodID = args[2].toLowerCase();
        XDDOS.duration = Integer.parseInt(args[3]);
        XDDOS.nettyThreads = Integer.parseInt(args[4]);
        XDDOS.loopThreads = Integer.parseInt(args[5]);
        if (args.length > 6) {
            if (args[6].equalsIgnoreCase("y"))
                XDDOS.proxies = (new ProxyGen(new File("proxies.txt"))).load();
            if (args[6].equalsIgnoreCase("n")) {
                if (args.length > 7) {
                    XDDOS.proxyFile = new File(args[7]);
                    XDDOS.proxies = new ProxyLoader(XDDOS.proxyFile);
                } else {
                    XDDOS.proxyFile = new File("proxies.txt");
                    XDDOS.proxies = new ProxyLoader(XDDOS.proxyFile);
                }
            }
        }
        XDDOS.run();

    }
}
