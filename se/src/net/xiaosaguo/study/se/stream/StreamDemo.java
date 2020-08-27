package net.xiaosaguo.study.se.stream;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * description: Collectors.groupingBy() 方法 demo
 *
 * @author xiaosaguo
 * @date 2020/08/17
 */
public class StreamDemo {

    public static final List<User> USER_LIST = Arrays.asList(
            new User("apple", 10, new BigDecimal("9.99")),
            new User("banana", 20, new BigDecimal("19.99")),
            new User("orange", 10, new BigDecimal("29.99")),
            new User("watermelon", 10, new BigDecimal("29.99")),
            new User("papaya", 20, new BigDecimal("9.99")),
            new User("apple", 10, new BigDecimal("9.99")),
            new User("banana", 10, new BigDecimal("19.99")),
            new User("apple", 20, new BigDecimal("9.99"))
    );

    public static void main(String[] args) {
        m2();
    }

    /**
     * Function.identity()     ：代替 v -> v
     * Collectors.counting()   ：根据分组计数
     */
    public static void m1() {
        //3 apple, 2 banana, others 1
        List<String> items = Arrays.asList("apple", "apple", "banana", "apple", "orange", "banana", "papaya");
        Map<String, Long> result = items.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        // out：{papaya=1, orange=1, banana=2, apple=3}
        System.out.println(result);
    }

    public static void m2() {
        //3 apple, 2 banana, others 1
        List<String> items = Arrays.asList("apple", "apple", "banana", "apple", "orange", "banana", "papaya");
        Map<String, Long> result = items.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        // out：{papaya=1, orange=1, banana=2, apple=3}
        System.out.println(result);

        Map<String, Long> finalMap = new LinkedHashMap<>();
        //reversed 反排序
        result.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .forEachOrdered(e -> finalMap.put(e.getKey(), e.getValue()));
        // out：{apple=3, banana=2, papaya=1, orange=1}
        System.out.println(finalMap);
    }

    public static void m3() {

        // 计算名字出现的次数
        Map<String, Long> counting = USER_LIST.stream()
                .collect(Collectors.groupingBy(User::getName, Collectors.counting()));
        System.out.println(counting);
        // 计算每个人金额是多少
        Map<String, Integer> sum = USER_LIST.stream()
                .collect(Collectors.groupingBy(User::getName, Collectors.summingInt(User::getPrice)));
        System.out.println(sum);
    }

    public static void m4() {

        //group by Salary
        Map<BigDecimal, List<User>> groupByPriceMap = USER_LIST.stream()
                .collect(Collectors.groupingBy(User::getSalary));
        System.out.println(groupByPriceMap);
        // group by Salary, uses 'mapping' to convert List<Item> to Set<String>
        Map<BigDecimal, Set<String>> result = USER_LIST.stream()
                .collect(
                        Collectors.groupingBy(User::getSalary, Collectors.mapping(User::getName, Collectors.toSet()))
                );
        System.out.println(result);
    }


}

@Data
@AllArgsConstructor
class User {
    private String name;
    private int price;
    private BigDecimal salary;
}