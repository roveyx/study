package net.xiaosaguo.study.se.clazz.enumeration;

@SuppressWarnings("all")
enum LevelHasMemberEnum {
    LOW("Low Level description", 30) {
        @Override
        public double getDistance() {
            return 30.0;
        }
    },
    MEDIUM("Medium Level description", 15) {
        @Override
        public double getDistance() {
            return 15.0;
        }
    },
    HIGH("High Level description", 7) {
        @Override
        public double getDistance() {
            return 7.0;
        }
    },
    URGENT("Urgent Level description", 1) {
        @Override
        public double getDistance() {
            return 1.0;
        }
    };

    // 成员属性
    private int levelValue;
    private String description;

    // 私有构造
    private LevelHasMemberEnum(String description, int levelValue) {
        this.description = description;
        this.levelValue = levelValue;
    }

    // 成员属性 getter/setter
    public int getLevelValue() {
        return levelValue;
    }

    @Override
    public String toString() {
        return this.description;
    }

    // 抽象方法，每个常量体中如果需要单独实现，则重写此方法
    public abstract double getDistance();
}

/**
 * description: 枚举类的常量可以配置成员属性和成员方法
 * 成员属性通过私有构造在定义时赋值
 * 成员方法为重写枚举类中的抽象方法
 *
 * @author xiaosaguo
 * @date 2020/05/17 00:56
 */
public class Demo3EnumCanHaveInfoAndMethodBody {
    public static void main(String[] args) {
        for (LevelHasMemberEnum level : LevelHasMemberEnum.values()) {
            String name = level.name();
            String desc = level.toString();
            int ordinal = level.ordinal();
            int levelValue = level.getLevelValue();
            double distance = level.getDistance();
            System.out.println("name=" + name + ",  description=" + desc
                    + ",  ordinal=" + ordinal + ", levelValue=" + levelValue
                    + ", distance=" + distance);
        }
    }
}