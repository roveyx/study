package net.xiaosaguo.study.se.clazz.enumeration;

@SuppressWarnings("all")
enum Level2Enum {
    LOW, MEDIUM, HIGH, URGENT;
}

/**
 * description: 枚举常量的比较，三种方式
 * <p>
 * 1. 使用 Enum 类的 compareTo() 方法
 * 2. 使用 Enum 类的 equals() 方法
 * 3. 使用 == 运算符
 *
 * @author xiaosaguo
 * @date 2020/05/17 01:11
 */
@SuppressWarnings("all")
public class Demo4EnumCompare {
    public static void main(String[] args) {
        Level2Enum s1 = Level2Enum.LOW;
        Level2Enum s2 = Level2Enum.URGENT;

        // 使用 Enum 类的 compareTo() 方法
        // s1.compareTo(s2) returns s1.ordinal() - s2.ordinal()
        int diff = s1.compareTo(s2);
        System.out.println(diff);
        assert diff == -3;

        // 使用 Enum 类的 equals() 方法
        System.out.println(s1.equals(s1));
        System.out.println(s1.equals(s2));

        // 使用 == 运算符
        System.out.println(s1 == s1);
        System.out.println(s1 == s2);
    }
}