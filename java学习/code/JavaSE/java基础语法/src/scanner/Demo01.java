package scanner;

import java.util.Scanner;

public class Demo01 {
    public static void main(String[] args) {

        // 创建一个扫描器对象，用于接收键盘数据
        Scanner scanner = new Scanner(System.in);

        System.out.println("开始输入:");
        // 判断用户有没有输入String
        if(scanner.hasNext()){
            //用next的方式去接收
            String str = scanner.next();
            System.out.println(str);
        }
        // 凡是IO流的类，不关闭都会一直占用资源
        scanner.close();
    }
}
