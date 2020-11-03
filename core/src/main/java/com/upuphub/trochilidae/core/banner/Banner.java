package com.upuphub.trochilidae.core.banner;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Banner
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2020-11-03 23:59
 **/
public class Banner {
    public static final String CUSTOM_BANNER_NAME = "banner.txt";

    public static void print() {
        URL url = Thread.currentThread().getContextClassLoader().getResource(CUSTOM_BANNER_NAME);
        if (url != null) {
            try {
                Path path = Paths.get(url.toURI());
                Files.lines(path).forEach(System.out::println);
            } catch (URISyntaxException | IOException ignore) { }
        } else {
            System.out.println(
                    "                      .--.    \n" +
                    "                    .\"  o \\__ \n" +
                    "                 _.-\"    ,(  `\n" +
                    "             _.-\"      ,;;|   \n" +
                    "        _.-=\" _,\"    ,,;;;'   \n" +
                    "    .-\"`_.-\"``-..,,;;;;:'     \n" +
                    "    `\"'`          `\\`\\        \n" +
                    "                   /^\\\\\\      Trochilidae 1.0-SNAPSHOT Power by Leo");
        }
    }
}
