package net.xiaosaguo.study.se.oop.override;

import lombok.*;

import java.time.LocalDate;

/**
 * description: 测试 重写 @Override
 * <p>
 * 父类得有无参构造，否则子类在实例化时，无法实例化父类
 * <p>
 * 1、通过 super 调用基类构造方法，必须是子类构造方法中的第一个语句。
 * <p>
 * 2、构造方法用于对基类的初始化。当构造一个对象时，先调用构造函数对成员函数和成员变量进行初始化。
 * 子类继承了父类的成员函数和成员变量，若不进行调用，则不会对父类的初始化。
 * <p>
 * 3、当多个类实现同一接口（或派生自同一抽象类）时，针对这些类所创建的对象调用接口所定义的方法时，会分别调用相应的类的具体实现代码。
 * <p>
 * 4、在 “+” 运算中,当任何一个对象与一个 String 对象连接时，会隐式地调用其 toString()方法，默认情况下，
 * 此方法返回 “类名 @ + hashCode”。为了返回有意义的信息，子类可以重写 toString() 方法。
 * <p>
 * 5、当子类与父类拥有一样的方法，并且让一个父类变量引用一个子类对象时，
 * 到底调用哪个方法，由对象自己的 “真实” 类型所决定，这就是说：对象是子类型的，它就调用子类型的方法，是父类型的，它就调用父类型的方法。
 * 如果子类与父类有相同的字段，则子类中的字段会代替或隐藏父类的字段，子类方法中访问的是子类中的字段（而不是父类中的字段）。
 * 如果子类方法确实想访问父类中被隐藏的同名字段，可以用super关键字来访问它。
 * 如果子类被当作父类使用,则通过子类访问的字段是父类的。
 * 如果在多态，即 Parent child = new Child(),子类重载父类方法，则
 * - child.方法() 调用的是子类的方法，改变的子类变量数据，
 * - child.变量   显示的是父类数据，还是未改变的。
 * 因此，我们进行程序设计时应避免子类与父类同名的字段！
 * <p>
 * 6、在继承中，子类可以自动转换成父类，但父类转换成子类只有引用类型真正身份是子类才会转换成功，否则会失败。
 * <p>
 * 重写也叫覆写，修饰符、返回类型、参数就是要和父类一摸一样才叫覆写，意思是说各个地方都需源要完美的盖住，
 * 在面向接口编程中，重写是子类有自己的逻辑要实现，同时又不破坏已写好程序逻辑的方法。
 * 举个例子，在父类中是 public 的方法，如果子类中将其降低访问权限为 private，
 * 那么子类中重写以后的方法对于外部对象就不可访问了，这就破坏了继承的含义。
 * <p>
 * 子类拥有父类对象所有的属性和方法（包括私有属性和私有方法），但是父类中的私有属性和方法子类是无法访问，只是拥有。
 * 子类可以拥有自己属性和方法，即子类可以对父类进行扩展。
 * 子类可以用自己的方式实现父类的方法。（以后介绍）。
 * <p>
 * 参考：https://blog.csdn.net/mahao25/article/details/87989031
 *
 * @author xiaosaguo
 * @date 2020/05/30
 */
public class OverrideDemo {

    public static void main(String[] args) {
        // Parent parent = new Parent("parent", 28, LocalDate.of(1993, 4, 16), (byte) 1, true);
        // parent.show();
        // Child child = new Child("child", 1, LocalDate.now(), (byte) 0, true);
        // Child child = new Child("child", 1, LocalDate.now());
        // Child child = new Child((byte) 1, true);
        // child.show();
        // child.setName("child");
        // child.setAge(1);
        // child.setBirthday(LocalDate.now());
        // child.show();

        Parent parent1 = new Child(true);
        parent1.show();

    }
}


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
class Parent {

    public String name;

    Integer age;

    protected LocalDate birthday;

    private Byte gender;

    private boolean health;

    protected void show() {
        System.out.println("-->Parent::show()");
        System.out.println(this.toString());
    }
}

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString(callSuper = true)
class Child extends Parent {

    // public String name;

    // Integer age;

    // protected LocalDate birthday;

    /// private Byte gender;

    private boolean health;

    @Override
    public void show() {
        System.out.println("-->Child::show()");
        System.out.println("-->Child::super.toString()");
        System.out.println(super.toString());
        System.out.println("-->Child::this.toString()");
        System.out.println(this.toString());
    }
}