package oop;


import oop.Demo061.Person;
import oop.Demo061.Student;

public class Application {
    public static void main(String[] args) {
        Student student = new Student();
        student.run();

        Person s2 = new Student();
        ((Student) s2).run();

    }
}
