package array;

public class ArrayDemo01 {
    public static void main(String[] args) {
        int[] nums; // 1. 声明一个数组
        nums = new int[10]; // 2. 创建一个数组

        // 3. 给数组元素赋值
        for (int i = 0; i < nums.length; i++) {
            nums[i] = i;
        }
        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }

    }
}
