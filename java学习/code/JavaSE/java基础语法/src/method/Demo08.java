package method;

public class Demo08 {
    public static void main(String[] args) {
        ChangeName changeName = new ChangeName();
        System.out.println(changeName.name);

        Demo08.fillName(changeName);

        System.out.println(changeName.name);

    }

    public static void fillName(ChangeName name){
        name.name = "Changed";
    }


}

class ChangeName{
    String name;
}
