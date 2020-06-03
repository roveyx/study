package net.xiaosaguo.study.se.clazz.enumeration;


import java.util.EnumSet;

/**
 * description: 枚举类可以实现接口
 *
 * @author xiaosaguo
 * @date 2020/05/16 18:59
 */
@SuppressWarnings("all")
public class Demo2EnumCanImplementInterface {
    public static void main(String[] args) {
        // .values() 获取枚举类中的常量数组
        for (Command cmd : LevelEnum.values()) {
            cmd.execute();
            /*
             * 默认情况下，JVM 是关闭断言的。因此如果想使用断言调试程序，需要手动打开断言功能。
             * 在命令行模式下运行Java程序时可增加参数 -enableassertions 或者 -ea 打开断言。
             * 可通过 -disableassertions 或者 -da 关闭断言(默认值,可有可无)。
             */
            assert cmd instanceof Command;
            assert cmd instanceof LevelEnum;
            LevelEnum le = (LevelEnum) cmd;
            // 枚举常量名称
            System.out.println("name: " + le.name());
            // 枚举常量序数，从 0 开始
            System.out.println("ordinal: " + le.ordinal());
            assert le instanceof Command;
            assert le instanceof LevelEnum;
            // valueOf(name),name 大小写敏感
            assert (LevelEnum.valueOf("LOW") == LevelEnum.values()[0]);
            le.execute();
            le.testEnumMethod();
            System.out.println();
        }

        // 使用枚举集合类获取枚举类中的所有常量
        EnumSet<LevelEnum> allLevels = EnumSet.allOf(LevelEnum.class);
        print(allLevels);
        System.out.println();
        // 用枚举集合工具类根据序数获取两个常量之间的所有常量，包含两端自身
        EnumSet<LevelEnum> l = EnumSet.range(LevelEnum.LOW, LevelEnum.HIGH);
        print(l);
    }

    public static void print(EnumSet<LevelEnum> levels) {
        for (LevelEnum le : levels) {
            System.out.println(le);
        }
    }
}


interface Command {
    /**
     * description: 接口中定义的方法
     *
     * @author xiaosaguo
     * @date 2020/05/16 19:42
     */
    void execute();
}

/**
 * description: 枚举类实现接口，每一个枚举值都相当于一个实例
 * <p>
 * 枚举类型是实现单例模式的最佳实现。
 *
 * @author xiaosaguo
 * @date 2020/05/16 19:45
 */
enum LevelEnum implements Command {
    /**
     * {@code LOW}  低级
     */
    LOW {
        @Override
        public void execute() {
            System.out.println("LOW execute...");
        }

        /**
         * member method
         */
        @Override
        public void testEnumMethod() {
            System.out.println("LOW test method...");
        }
    },
    /**
     * {@code MEDIUM}  中级
     */
    MEDIUM {
        @Override
        public void execute() {
            System.out.println("MEDIUM execute...");
        }
    },
    /**
     * {@code HIGH}  高级
     */
    HIGH {
        @Override
        public void execute() {
            System.out.println("HIGH execute...");
        }
    },
    /**
     * {@code URGENT}  紧急
     */
    URGENT {
        @Override
        public void execute() {
            System.out.println("URGENT execute...");
        }
    };

    @Override
    public abstract void execute();

    /**
     * member method
     */
    public void testEnumMethod() {
        System.out.println("member test method");
    }
}