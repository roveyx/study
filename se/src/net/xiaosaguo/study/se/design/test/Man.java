package net.xiaosaguo.study.se.design.test;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Setter
@Getter
@ToString
public class Man {
    private String name;
    private Integer age;
    private String gender;
    private LocalDate birthday;


    public static final class ManBuilder {
        private Man man;

        private ManBuilder() {
            man = new Man();
        }

        public static ManBuilder aMan() {
            return new ManBuilder();
        }

        public ManBuilder name(String name) {
            man.setName(name);
            return this;
        }

        public ManBuilder age(Integer age) {
            man.setAge(age);
            return this;
        }

        public ManBuilder gender(String gender) {
            man.setGender(gender);
            return this;
        }

        public ManBuilder birthday(LocalDate birthday) {
            man.setBirthday(birthday);
            return this;
        }

        public Man build() {
            return man;
        }
    }

    public static void main(String[] args) {
        Man wang = ManBuilder.aMan().age(19).build();
        System.out.println(wang);
    }
}
