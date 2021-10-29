package scanner;

import java.util.Scanner;

public class Demo04 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("开始输入");
        if (scanner.hasNextLine()){
            String str = scanner.nextLine();
            System.out.println(str);
        }

    }
}
