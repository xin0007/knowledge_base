package scanner;

import java.util.Scanner;

public class Demo03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("输入一个整数");

        if(scanner.hasNextInt()){
            int a = scanner.nextInt();
            System.out.println(a);
        } else {
            System.out.println("不是一个整数");
        }

        scanner.close();
    }
}
