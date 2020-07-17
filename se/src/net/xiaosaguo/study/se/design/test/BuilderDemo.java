package net.xiaosaguo.study.se.design.test;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

/**
 * description: 建造者模式
 *
 * @author xiaosaguo
 * @date 2020/07/17
 */
public class BuilderDemo {
    public static void main(String[] args) {
        Person bob = Person.builder()
                .name("bob")
                .age(18)
                .gender("男")
                .birthday(LocalDate.of(1993, 4, 16))
                .build();
        System.out.println(bob.toString());
    }
}


@Builder
@Setter
@Getter
@ToString
class Person {
    private String name;
    private Integer age;
    private String gender;
    private LocalDate birthday;
}



