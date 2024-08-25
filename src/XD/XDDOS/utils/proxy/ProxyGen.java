package XD.XDDOS.utils.proxy;

import XD.XDDOS.XDDOS;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class ProxyGen {

    private final File proxyFile;

    public ProxyGen(File proxyFile) {
        System.out.println(XDDOS.GREEN_BOLD+"["+XDDOS.RED_BOLD+"XDDOS"+XDDOS.GREEN_BOLD+"] "+XDDOS.WHITE_BOLD+" Parsing proxy...");
        this.proxyFile = proxyFile;
        File urls = new File("urls.txt");
        List<String> proxies = new CopyOnWriteArrayList<>();
        if(urls.exists()){
            try {
                Path p = urls.toPath();
                List<String> readAllLines = Files.readAllLines(p);
                for (int i = 0; i < readAllLines.size(); i++) {
                    String link = readAllLines.get(i);
                    try {
                        Document scrapedproxies = Jsoup.connect(link).get();
                        proxies.addAll(Arrays.stream(scrapedproxies.text().split(" ")).distinct().collect(Collectors.toList()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            try {
                urls.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("put proxy links in urls.txt to scrape");
            System.exit(0);
        }
        proxies = new CopyOnWriteArrayList<>(new HashSet<>(proxies));

        try {
            this.proxyFile.createNewFile();

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.proxyFile))) {
                for (String proxy : proxies) {
                    writer.write(proxy + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ProxyLoader load() {
        return new ProxyLoader(proxyFile);
    }

}
