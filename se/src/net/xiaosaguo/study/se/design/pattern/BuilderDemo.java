package net.xiaosaguo.study.se.design.pattern;

import lombok.Setter;

/**
 * description: 建造者模式（Builder）详解
 * <p>
 * 模式的定义与特点
 * 建造者（Builder）模式的定义：指将一个复杂对象的构造与它的表示分离，使同样的构建过程可以创建不同的表示，这样的设计模式被称为建造者模式。
 * 它是将一个复杂的对象分解为多个简单的对象，然后一步一步构建而成。它将变与不变相分离，即产品的组成部分是不变的，但每一部分是可以灵活选择的。
 * <p>
 * 该模式的主要优点如下：
 * 各个具体的建造者相互独立，有利于系统的扩展。
 * 客户端不必知道产品内部组成的细节，便于控制细节风险。
 * <p>
 * 其缺点如下：
 * 产品的组成部分必须相同，这限制了其使用范围。
 * 如果产品的内部变化复杂，该模式会增加很多的建造者类。
 * <p>
 * 建造者（Builder）模式和工厂模式的关注点不同：建造者模式注重零部件的组装过程，而工厂方法模式更注重零部件的创建过程，但两者可以结合使用。
 * <p>
 * 模式的结构与实现
 * 建造者（Builder）模式由产品、抽象建造者、具体建造者、指挥者等 4 个要素构成，现在我们来分析其基本结构和实现方法。
 * <p>
 * 1. 模式的结构
 * 建造者（Builder）模式的主要角色如下。
 * 产品角色（Product）：它是包含多个组成部件的复杂对象，由具体建造者来创建其各个滅部件。
 * 抽象建造者（Builder）：它是一个包含创建产品各个子部件的抽象方法的接口，通常还包含一个返回复杂产品的方法 getResult()。
 * 具体建造者(Concrete Builder）：实现 Builder 接口，完成复杂产品的各个部件的具体创建方法。
 * 指挥者（Director）：它调用建造者对象中的部件构造与装配方法完成复杂对象的创建，在指挥者中不涉及具体产品的信息。
 * <p>
 * 建造者（Builder）模式在应用过程中可以根据需要改变，如果创建的产品种类只有一种，只需要一个具体建造者，这时可以省略掉抽象建造者，甚至可以省略掉指挥者角色。
 *
 * @author xiaosaguo
 * @date 2020/07/17
 */
public class BuilderDemo {
}


/**
 * description: 产品角色：包含多个组成部件的复杂对象。
 *
 * @author xiaosaguo
 * @date 2020/07/17 22:02
 */
@Setter
class Product {
    private String partA;
    private String partB;
    private String partC;

    public void show() {
        //显示产品的特性
    }
}

/**
 * description: 抽象建造者：包含创建产品各个子部件的抽象方法。
 *
 * @author xiaosaguo
 * @date 2020/07/17 22:02
 */
abstract class Builder {
    /**
     * 创建产品对象
     */
    protected Product product = new Product();

    public abstract void buildPartA();

    public abstract void buildPartB();

    public abstract void buildPartC();

    /**
     * 返回产品对象
     */
    public Product getResult() {
        return product;
    }
}

/**
 * description: 具体建造者：实现了抽象建造者接口。
 *
 * @author xiaosaguo
 * @date 2020/07/17 22:03
 */
class ConcreteBuilder extends Builder {
    @Override
    public void buildPartA() {
        product.setPartA("建造 PartA");
    }

    @Override
    public void buildPartB() {
        product.setPartA("建造 PartB");
    }

    @Override
    public void buildPartC() {
        product.setPartA("建造 PartC");
    }
}

/**
 * description: 指挥者：调用建造者中的方法完成复杂对象的创建。
 *
 * @author xiaosaguo
 * @date 2020/07/17 22:04
 */
class Director {
    private Builder builder;

    public Director(Builder builder) {
        this.builder = builder;
    }

    /**
     * 产品构建与组装方法
     */
    public Product construct() {
        builder.buildPartA();
        builder.buildPartB();
        builder.buildPartC();
        return builder.getResult();
    }
}


/**
 * description:  客户类。
 *
 * @author xiaosaguo
 * @date 2020/07/17 22:06
 */
class Client {
    public static void main(String[] args) {
        Builder builder = new ConcreteBuilder();
        Director director = new Director(builder);
        Product product = director.construct();
        product.show();
    }
}