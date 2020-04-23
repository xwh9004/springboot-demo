package com.example.data.entity;

/**
 * <p><b>Description:</b>
 * TODO
 * <p><b>Company:</b>
 *
 * @author created by Jesse Hsu at 14:56 on 2020/4/23
 * @version V0.1
 * @classNmae Person
 */
public class Person {
        private String id;
        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getId() {
            return id;
        }
        public String getName() {
            return name;
        }
        public int getAge() {
            return age;
        }

        @Override
        public String toString() {
            return "Person [id=" + id + ", name=" + name + ", age=" + age + "]";
        }
}
