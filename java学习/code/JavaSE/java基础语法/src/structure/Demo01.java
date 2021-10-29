package structure;

import java.util.Scanner;

public class Demo01 {
    public static void main(String[] args) {
        int score = 49;
        if (score >= 60) {
            System.out.println("及格");
        } else if (score > 50) {
            System.out.println("还能救救");
        } else {
            System.out.println("就不了了");
        }
    }
}
