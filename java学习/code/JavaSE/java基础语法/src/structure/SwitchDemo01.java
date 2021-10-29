package structure;

public class SwitchDemo01 {
    public static void main(String[] args) {
        char a = 'L';
        switch (a) {
            case 'A':
                System.out.println("优秀");
                break;
            case 'B':
                System.out.println("良好");
                break;
            case 'C':
                System.out.println("合格");
                break;
            default:
                System.out.println("未知");
        }
    }
}
