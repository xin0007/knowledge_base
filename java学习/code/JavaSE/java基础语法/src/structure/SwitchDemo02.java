package structure;

public class SwitchDemo02 {
    public static void main(String[] args) {
        String a = "大天使";

        switch (a) {
            case "大天使":
                System.out.println("是真的天使");
                break;
            case "二天使":
                System.out.println("是真的二天使");
                break;
            case "三天使":
                System.out.println("是真的三天使");
                break;
            default:
                System.out.println("不是天使");
        }
    }
}
