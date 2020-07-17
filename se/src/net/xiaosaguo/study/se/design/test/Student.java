package net.xiaosaguo.study.se.design.test;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Setter
@Getter
@ToString
public class Student {
    private String name;
    private Integer age;
    private String gender;
    private LocalDate birthday;


    public static final class StudentBuilder {
        private String name;
        private Integer age;
        private String gender;
        private LocalDate birthday;

        private StudentBuilder() {
        }

        public static StudentBuilder builder() {
            return new StudentBuilder();
        }

        public StudentBuilder name(String name) {
            this.name = name;
            return this;
        }

        public StudentBuilder age(Integer age) {
            this.age = age;
            return this;
        }

        public StudentBuilder gender(String gender) {
            this.gender = gender;
            return this;
        }

        public StudentBuilder birthday(LocalDate birthday) {
            this.birthday = birthday;
            return this;
        }

        public Student build() {
            Student student = new Student();
            student.name = this.name;
            student.gender = this.gender;
            student.birthday = this.birthday;
            student.age = this.age;
            return student;
        }
    }

    public static void main(String[] args) {
        Student rose = Student.StudentBuilder.builder()
                .name("rose")
                .age(18)
                .gender("女")
                .birthday(LocalDate.of(1995, 8, 1))
                .build();
        System.out.println(rose);
    }
}