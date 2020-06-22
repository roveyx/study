package net.xiaosaguo.study.se.thread.juc;

import java.net.URLEncoder;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

class Price {
    final String code;
    final float price;

    Price(String code, float price) {
        this.code = code;
        this.price = price;
    }
}

@SuppressWarnings("all")
class StockLookupSupplier implements Supplier<String> {
    String name;

    public StockLookupSupplier(String name) {
        this.name = name;
    }

    @Override
    public String get() {
        System.out.println("lookup: " + name);
        try {
            String url = "http://suggest3.sinajs.cn/suggest/type=11,12&key=" + URLEncoder.encode(name, "UTF-8");
            String result = DownloadUtil.download(url);
            String[] ss = result.split(",");
            return ss[3];
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

@SuppressWarnings("all")
public class Demo10CompletableFutureSequence {

    public static void main(String[] args) {
        String name = "上证指数";
        CompletableFuture<String> getStockCodeFuture = CompletableFuture.supplyAsync(new StockLookupSupplier(name));
        // CompletableFuture<Price> getStockPriceFuture = getStockCodeFuture.thenApplyAsync(new Function<String, Price>() {
        //     @Override
        //     public Price apply(String code) {
        //         System.out.println("got code: " + code);
        //         try {
        //             String url = "http://hq.sinajs.cn/list=" + code;
        //             String result = DownloadUtil.download(url);
        //             String[] ss = result.split(",");
        //             return new Price(code, Float.parseFloat(ss[3]));
        //         } catch (Exception e) {
        //             throw new RuntimeException(e);
        //         }
        //     }
        // });
        CompletableFuture<Price> getStockPriceFuture = getStockCodeFuture.thenApplyAsync(code -> {
            System.out.println("got code: " + code);
            try {
                String url = "http://hq.sinajs.cn/list=" + code;
                String result = DownloadUtil.download(url);
                String[] ss = result.split(",");
                return new Price(code, Float.parseFloat(ss[3]));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        // getStockPriceFuture.thenAccept(new Consumer<Price>() {
        //     @Override
        //     public void accept(Price p) {
        //         System.out.println(p.code + ": " + p.price);
        //     }
        // });
        getStockPriceFuture.thenAccept(p -> System.out.println(p.code + ": " + p.price));
        getStockPriceFuture.join();
    }

}
