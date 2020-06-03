package net.xiaosaguo.study.se.clazz.enumeration;

/**
 * description: 枚举类 Demo，性别
 * 这是一个最普通的枚举类，只有两个属性
 * <p>
 * Java 开发手册：枚举类名带上 Enum 后缀，枚举成员名称需要全大写，单词间用下划线隔开。
 * 枚举其实就是特殊的常量类，且构造方法被默认强制是私有。
 * 正例：枚举名字为 ProcessStatusEnum 的成员名称：SUCCESS / UNKNOWN_REASON。
 *
 * @author xiaosaguo
 * @date 2020/05/16 18:11
 */
public enum Demo1GenderEnum {
    /**
     * {@code MALE}   男性
     */
    MALE,
    /**
     * {@code FEMALE} 女性
     */
    FEMALE
}

@SuppressWarnings("all")
class EnumUsage {

    /**
     * description: 枚举类可以嵌套在其它类内部定义，嵌套的枚举类型是隐式静态的。
     * 嵌套的枚举类型可以使用任何访问修饰符（public，private，protected或package）修饰。
     * <p>
     * 因为枚举类型总是静态的，所以我们不能在方法体内声明一个局部枚举类型。
     * <p>
     * 枚举类型是实现单例模式的最佳实现。
     *
     * @author xiaosaguo
     * @date 2020/05/16 18:32
     */
    public enum DirectionEnum {
        /**
         * {@code EAST}  东
         */
        EAST,
        /**
         * {@code SOUTH} 南
         */
        SOUTH,
        /**
         * {@code WEST}  西
         */
        WEST,
        /**
         * {@code NORTH} 北
         */
        NORTH
    }

    public static void main(String[] args) {

        System.out.println(Demo1GenderEnum.MALE);
        System.out.println(Demo1GenderEnum.FEMALE);

        // 1. DirectionEnum direction; 枚举类可以这样声明.
        // 2. 枚举类型可以声明为null
        DirectionEnum direction = null;
        direction = DirectionEnum.EAST;
        switch (direction) {
            case SOUTH:
                System.out.println("South");
                break;
            /// case EAST:
            ///     System.out.println("East");
            ///     break;
            case WEST:
                System.out.println("West");
                break;
            case NORTH:
                System.out.println("North");
                break;
            default:
                System.out.println("East don‘t hurt");
        }
    }
}
