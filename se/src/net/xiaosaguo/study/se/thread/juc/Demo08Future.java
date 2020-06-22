package net.xiaosaguo.study.se.thread.juc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * description: Runnable 与 Callable区别
 * <p>
 * 相同点：
 * 两者都是接口；
 * 两者都可用来编写多线程程序；
 * 两者都需要调用Thread.start()启动线程；
 * <p>
 * 不同点：
 * 两者最大的不同点是：实现Callable接口的任务线程能返回执行结果；而实现Runnable接口的任务线程不能返回结果；
 * Callable接口的call()方法允许抛出异常；而Runnable接口的run()方法的异常只能在内部消化，不能继续上抛；
 * <p>
 * 注意点：
 * Callable接口支持返回执行结果，此时需要调用FutureTask.get()方法实现，此方法会阻塞主线程直到获取‘将来’结果；
 * 当不调用此方法时，主线程不会阻塞！
 *
 * @author xiaosaguo
 * @date 2020/06/23 04:38
 */
class DownloadTask implements Callable<String> {
    String url;

    public DownloadTask(String url) {
        this.url = url;
    }

    @Override
    public String call() throws Exception {
        System.out.println("Start download " + url + "...");
        URLConnection conn = new URL(this.url).openConnection();
        conn.connect();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
            String s = null;
            StringBuilder sb = new StringBuilder();
            while ((s = reader.readLine()) != null) {
                sb.append(s).append("\n");
            }
            return sb.toString();
        }
    }
}

@SuppressWarnings("all")
public class Demo08Future {
    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        DownloadTask task = new DownloadTask("https://www.baidu.com/");
        Future<String> future = executor.submit(task);
        String html = future.get();
        System.out.println(html);
        executor.shutdown();
    }
}
