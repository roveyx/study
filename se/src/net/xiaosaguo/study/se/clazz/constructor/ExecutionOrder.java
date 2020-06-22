package net.xiaosaguo.study.se.clazz.constructor;


/**
 * description: 非静态代码块、静态块、构造器的执行顺序
 * <p>
 * out:
 * 父类静态块
 * 子类静态代码块
 * 父类非静态代码块
 * 父类构造器
 * 子类非静态代码块
 * 子类构造器
 * <p>
 * 总结：
 * 父类 > 子类
 * 静态块 > 非静态块 > 构造器
 *
 * @author xiaosaguo
 * @date 2020/06/23 03:04
 */
public class ExecutionOrder {
    public static void main(String[] args) {
        Son son = new Son();
    }
}


class Parent {
    {
        System.out.println("父类非静态代码块");
    }

    static {
        System.out.println("父类静态块");
    }

    public Parent() {
        System.out.println("父类构造器");
    }
}


class Son extends Parent {
    public Son() {
        System.out.println("子类构造器");
    }

    static {
        System.out.println("子类静态代码块");
    }

    {
        System.out.println("子类非静态代码块");
    }
}