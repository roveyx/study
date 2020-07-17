package net.xiaosaguo.study.se.design.test;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Setter
@Getter
@ToString
public class Worker {
    private String name;
    private Integer age;
    private String gender;
    private LocalDate birthday;

    Worker(String name, Integer age, String gender, LocalDate birthday) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.birthday = birthday;
    }

    public static Worker.WorkerBuilder builder() {
        return new Worker.WorkerBuilder();
    }

    public static class WorkerBuilder {
        private String name;
        private Integer age;
        private String gender;
        private LocalDate birthday;

        WorkerBuilder() {
        }

        public Worker.WorkerBuilder name(String name) {
            this.name = name;
            return this;
        }

        public Worker.WorkerBuilder age(Integer age) {
            this.age = age;
            return this;
        }

        public Worker.WorkerBuilder gender(String gender) {
            this.gender = gender;
            return this;
        }

        public Worker.WorkerBuilder birthday(LocalDate birthday) {
            this.birthday = birthday;
            return this;
        }

        public Worker build() {
            return new Worker(this.name, this.age, this.gender, this.birthday);
        }

        @Override
        public String toString() {
            return "Worker.WorkerBuilder(" +
                    "name=" + this.name + ", " +
                    "age=" + this.age + ", " +
                    "gender=" + this.gender + ", " +
                    "birthday=" + this.birthday +
                    ")";
        }
    }

    public static void main(String[] args) {
        Worker rose = Worker.builder()
                .name("rose")
                .age(18)
                .gender("女")
                .birthday(LocalDate.of(1995, 8, 1))
                .build();
        System.out.println(rose);
    }
}
