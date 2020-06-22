package net.xiaosaguo.study.se.clazz.constructor;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Closeable;

/**
 * description: final 测试
 * <p>
 * <p>
 * final关键字可以在以下三个上下文中使用：
 * 变量声明
 * 类声明
 * 方法声明
 * <p>
 * <p>
 * final 变量
 * 如果一个变量被声明为final，它只能被赋值一次。最终变量的值在设置后不能修改。
 * 变量声明包括局部变量的声明，方法/构造函数的形式参数，实例变量和类变量。
 * <p>
 * <p>
 * 我们可以只设置一次final变量的值。
 * 有两种方法来初始化final变量:
 * 在声明时予以初始化。
 * 将其初始化延迟到稍后的时间。
 * 但是，我们必须在第一次读取最终变量之前初始化它。
 * <p>
 * final局部变量
 * 你可以声明一个局部变量final。如果将局部变量声明为空的最终变量，则必须在使用前初始化它。
 * <p>
 * final参数
 * 我们可以声明一个参数final。当调用方法或构造函数时，参数将使用实际参数的值自动初始化。
 * 因此，您不能更改方法或构造函数体内的最终形式参数的值。
 * <p>
 * final实例变量
 * 我们可以声明一个实例变量final和blank final。
 * 空白最终实例变量必须初始化一次，并且只有在调用类的任何构造函数时才初始化一次。
 * <p>
 * final类变量
 * 我们可以声明一个类变量final和blank final。我们必须在其中一个静态初始化器中初始化一个空的最终类变量。
 * <p>
 * final引用变量
 * 引用变量存储对象的引用。最终引用变量意味着，一旦引用一个对象（或null），它就不能被修改以引用另一个对象。
 *
 * @author xiaosaguo
 * @date 2020/06/23
 */
@SuppressWarnings("all")
class UserContext implements Closeable {

    static final ThreadLocal<User> USER_CONTEXT = new ThreadLocal<>();

    public static User getCurrentUser() {
        return USER_CONTEXT.get();
    }

    public UserContext(User user) {
        USER_CONTEXT.set(user);
    }

    @Override
    public void close() {
        USER_CONTEXT.remove();
    }
}

@NoArgsConstructor
@AllArgsConstructor
@Data
class User {
    String name;
    int level;
}

@SuppressWarnings("all")
@Data
public class Final {

    final User user;

    // public Final() {
    //     User user = new User();
    // }

    public Final(User user) {
        this.user = user;
    }

    public static void main(String[] args) {
        User u = new User("some", 1);
        Final f = new Final(u);

        System.out.println(f.user);
        System.out.println(f.user.level);
        System.out.println(u.level);
        System.out.println(f.getUser());
        System.out.println(f.getUser().level);
        System.out.println(f.getUser().getLevel());
        System.out.println(u.getLevel());
    }

}
