package net.xiaosaguo.study.se.thread;

import lombok.AllArgsConstructor;


@AllArgsConstructor
class User {
    String name;
    int level;
}


@SuppressWarnings("all")
class UserContext implements AutoCloseable {
    // 全局唯一静态变量:
    static final ThreadLocal<User> context = new ThreadLocal<>();

    // 初始化ThreadLocal的User:
    public UserContext(User user) {
        context.set(user);
    }

    // 获取当前线程的ThreadLocal User:
    public static User getCurrentUser() {
        return context.get();
    }

    // 移除ThreadLocal关联的User:
    @Override
    public void close() {
        context.remove();
    }
}

@SuppressWarnings("all")
class ProcessThread extends Thread {
    User user;

    ProcessThread(User user) {
        this.user = user;
    }

    @Override
    public void run() {
        try (UserContext ctx = new UserContext(user)) {
            // step 1:
            new Greeting().hello();
            // step 2:
            Level.checkLevel();
            // step 3:
            // ...
        }
    }
}

@SuppressWarnings("all")
class Greeting {
    void hello() {
        User user = UserContext.getCurrentUser();
        System.out.println("Hello, " + user.name + "!");
    }
}

@SuppressWarnings("all")
class Level {
    static void checkLevel() {
        User user = UserContext.getCurrentUser();
        if (user.level > 100) {
            System.out.println(user.name + " is a VIP user.");
        } else {
            System.out.println(user.name + " is a registered user.");
        }
    }
}

@SuppressWarnings("all")
public class Demo09ThreadLocal {
    public static void main(String[] args) throws Exception {
        Thread t1 = new ProcessThread(new User("Bob", 120));
        Thread t2 = new ProcessThread(new User("Alice", 98));
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("Main end");
    }
}
