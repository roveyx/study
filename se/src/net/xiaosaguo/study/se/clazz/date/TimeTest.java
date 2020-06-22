package net.xiaosaguo.study.se.clazz.date;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

/**
 * description: 日期
 *
 * @author xiaosaguo
 * @date 2020/05/16
 */
public class TimeTest {

    public static void main(String[] args) {

        Instant now = Instant.now();
        System.out.println("Instant.now()                      \t" + now);
        // 时区偏移
        System.out.println("now.atZone(ZoneId.systemDefault()) \t" + now.atZone(ZoneId.systemDefault()));
        System.out.println("now.atOffset(ZoneOffset.ofHours(8))\t" + now.atOffset(ZoneOffset.ofHours(8)));

        Instant plus8Now = Instant.now().plusMillis(TimeUnit.HOURS.toMillis(8));
        System.out.println("plus8Now                           \t" + plus8Now);

        System.out.println("----------------------------------------------------");


        // 获取当前日期 format: yyyy-MM-dd
        LocalDate localDate = LocalDate.now();
        // 获取当前时间 format: HH:mm:ss
        LocalTime localTime = LocalTime.now();
        // 获取当前日期时间 format: yyyy-MM-dd HH:mm:ss
        LocalDateTime localDateTime = LocalDateTime.now();

        System.out.println("localDate\t" + localDate);
        System.out.println("localTime\t" + localTime);
        System.out.println("localDateTime\t" + localDateTime);

        System.out.println("----------------------------------------------------");


        LocalDate date = LocalDate.of(1985, 3, 5);
        System.out.println("LocalDate.of(1985, 3, 5)\t" + date);

        System.out.println("----------------------------------------------------");


        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateTimeStr = dateTimeFormatter.format(localDateTime);
        System.out.println("localDateTime-format\t" + dateTimeStr);

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String dateStr = dateFormatter.format(localDate);
        System.out.println("localDate-format    \t" + dateStr);

        System.out.println("----------------------------------------------------");


        System.out.println("System.currentTimeMillis()\t" + System.currentTimeMillis());
        System.out.println("System.nanoTime()         \t" + System.nanoTime());

        System.out.println("----------------------------------------------------");

        // 两个时间之间的时长
        Duration between = Duration.between(now, plus8Now);
        System.out.println(between);
        System.out.println(between.toMillis());

        System.out.println("----------------------------------------------------");


        Period be = Period.between(date, localDate);
        System.out.println(date);
        System.out.println(localDate);
        System.out.println(be.getYears());
        System.out.println(be.getMonths());
        System.out.println(be.getDays());

    }
}
