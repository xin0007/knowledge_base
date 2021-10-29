package base;

public class Demo08 {
    // main方法

    // 实例变量
    // 从属于对象；可以不初始化。如果不自行初始化，不同类型会有不同的默认值： 0, 0.0, false null
    String name;
    int age;

    static boolean salary;

    public static void main(String[] args) {

        //局部变量，必须声明和初始化值
        int i = 10;
        System.out.println(i);

        // 实例变量
        Demo08 demo08 = new Demo08();
        System.out.println(demo08.age);
        System.out.println(demo08.name);


        // 类变量
        System.out.println(salary);
    }


    //其他方法
    public void add(){


    }
}
