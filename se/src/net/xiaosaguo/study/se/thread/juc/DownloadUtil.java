package net.xiaosaguo.study.se.thread.juc;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@SuppressWarnings("all")
public class DownloadUtil {

    public static String download(String url) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
        conn.setDoOutput(false);
        conn.setAllowUserInteraction(false);
        conn.setConnectTimeout(3000);
        conn.setReadTimeout(3000);
        conn.connect();
        ByteArrayOutputStream output = new ByteArrayOutputStream(10240);
        try (InputStream input = conn.getInputStream()) {
            int n;
            byte[] buffer = new byte[2048];
            while ((n = input.read(buffer)) != (-1)) {
                output.write(buffer, 0, n);
            }
        }
        conn.disconnect();
        return new String(output.toByteArray(), StandardCharsets.UTF_8);
    }
}
