package oop.Demo04;

public class Student {
    private String name;
    private int age;
    private char gender;

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if(age>120){
            this.age = 3;
        }else{
            this.age = age;
        }
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }
}
