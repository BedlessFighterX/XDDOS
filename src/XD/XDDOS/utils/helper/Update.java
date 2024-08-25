package XD.XDDOS.utils.helper;

import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import XD.XDDOS.XDDOS;

public class Update {
    private final static String link = "https://github.com/AnAverageBeing/XDDOS/raw/master/XDDOS.jar";
    public static void updateJAR() {
        System.out.println(XDDOS.GREEN_BOLD+"Downloading file from GitHub");
        try {
           InputStream inputstream = new URL(link).openStream();
           Files.copy(inputstream, Paths.get("XDDOS.jar"), StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(XDDOS.GREEN_BOLD+"Download file");
        System.out.println(XDDOS.RED_BOLD + "Restart XDDOS to use new version" + XDDOS.RESET);
        System.exit(0);
    }
}
