package net.xiaosaguo.study.se.clazz.anonymous.classes;

/**
 * description: 匿名内部类 & lambda 表达式 & 函数式接口
 *
 * @author xiaosaguo
 * @date 2020/07/10
 */
public class AnonymousClassesTest {
    public static void main(String[] args) {
        Outer.method().show();
        Outer.method().show3();
        Inner.show4();

        new Outer().method2().show();
        new Outer().method3().show();
    }
}


@FunctionalInterface
interface Inner {

    /* 函数式接口：
     * 1. 使用 @FunctionalInterface 标记的接口
     * 2. 只能标记在只定义了单个抽象方法的接口上
     * 3. 接口中的 default 方法和静态方法不算是抽象方法
     */

    void show();

    // void show2();

    default void show3() {
        System.out.println("show3");
    }

    static void show4() {
        System.out.println("show4");
    }
}


class Outer {
    private int num0 = 0;
    public static int num1 = 10;
    private final int num2 = 20;
    public static final int num3 = 30;

    private int num11 = 11;

    public static Inner method() {
        /* 匿名内部类：
         * 1. 是局部内部类的简化形式
         * 2. 前提：存在一个类或接口（一般是由形参或返回值确定）
         * 3. 匿名内部类的格式：
         *      new 类名或接口名(){
         *          @Override
         *          访问修饰符 返回值 方法名(参数...){
         *              ...
         *          }
         *      }
         * 4. 本质：相当于 继承该类的一个子类的匿名对象、实现该接口的一个实现类的匿名对象
         * 5. 注意：局部内部类访问外部变量，外部变量必须是 final 的，不是匿名内部类
         *      原因是局部内部类是一个对象，在堆内存，释放时机（垃圾回收）不确定，而局部变量方法完成后就释放了，
         *      此时使用堆内对象，则无法找到局部变量，而使用 final 修改的变量，在编译后会直接替换成常量
         * 6. 匿名内部类的优势，没有引用，使用后就可以被垃圾回收了
         * 7. 注意：重写方法时，访问修饰符要大于等于父类的
         * 8. 类名.this 可以限定引用对象
         */
        int num4 = 40;
        return new Inner() {
            int num5 = 50;

            @Override
            public void show() {
                int num6 = 60;
                System.out.println("HelloWorld" + num1 + num3 + num4 + num5 + num6);
            }

            /// @Override
            /// public void show2() {
            ///     System.out.println("HelloWorld2");
            /// }
        };
    }

    public Inner method2() {
        /* lambda 表达式：
         * 1. 简化以匿名内部类形式实现函数式接口的使用（可以将匿名内部类语法简化为 lambda 表达式语法）
         * 2. 语法：
         *          (参数...) -> {方法体}
         *      无参数，无返回值
         *          () -> System.out.println("");
         *      有 1 个参数，无返回值: 参数或方法体为1个或1行时，括号可省略
         *          v -> System.out.println(v);
         *      有多个参数，无返回值
         *          (k,v) -> System.out.println(k+v);
         *      有返回值：
         *          () -> return "";
         *      方法体有多行
         *          (k,v) -> {
         *              System.out.println(k+v);
         *              return k + v;
         *          }
         * 3. 类型推断
         *      方法的参数类型，返回值类型，都是由函数式接口方法定义的，编译器可以自动推断出，即参数部分不用声明参数类型
         * 4. this 的区别：lambda 中没有 this，即 this 为类的 this
         *      区别查看 method2 和 method3
         */
        int num2 = -2;
        return () -> {
            // int num2 = 22;
            System.out.println("HelloWorld" + num0 + num1 + num2 + num3 + this.num2 + Outer.this.num2 + num11);
        };
    }

    public Inner method3() {
        int num7 = 70;
        int num2 = -2;// TODO 这个 num2 暂时不知道如何使用
        return new Inner() {
            int num2 = 22;// this.num2

            @Override
            public void show() {
                int num2 = 99;// num2
                System.out.println("HelloWorld" + num0 + num1 + num2 + num3 + this.num2 + Outer.this.num2 + num11 + num7);
            }
        };
    }
}

