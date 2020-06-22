package net.xiaosaguo.study.se.thread.juc;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;

@SuppressWarnings("all")
class StockSupplier implements Supplier<Float> {

    @Override
    public Float get() {
        String url = "http://hq.sinajs.cn/list=sh000001";
        System.out.println("GET: " + url);
        try {
            String result = DownloadUtil.download(url);
            String[] ss = result.split(",");
            return Float.parseFloat(ss[3]);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

@SuppressWarnings("all")
public class Demo09CompletableFuture {
    public static void main(String[] args) {

        AtomicReference<Float> price = new AtomicReference<>(null);

        // CompletableFuture.supplyAsync(task) 创建异步多线程任务
        CompletableFuture<Float> getStockFuture = CompletableFuture.supplyAsync(new StockSupplier());

        // getStockFuture.thenAccept(new Consumer<Float>() {
        //     @Override
        //     public void accept(Float price) {
        //         System.out.println("Current price: " + price);
        //     }
        // });
        getStockFuture.thenAccept(result -> price.set(result));

        // getStockFuture.exceptionally(new Function<Throwable, Float>() {
        //     @Override
        //     public Float apply(Throwable t) {
        //         System.out.println("Error: " + t.getMessage());
        //         return Float.NaN;
        //     }
        // });
        getStockFuture.exceptionally(t -> {
            System.out.println("发生异常，异常信息: " + t.getMessage());
            price.set(Float.NaN);
            return Float.NaN;
        });

        System.out.println("当前价格: " + price);

        // CompletableFuture 默认使用 Executor，会在主线程关闭的时候关闭
        getStockFuture.join();
        System.out.println("Main 狗呆");
    }
}
