package thread;

public class TestLambda {

    public static void main(String[] args) {

        // 6. 用Lambda简化
        ILike like = () -> {
            System.out.println("55555");
        };

        like.lambda();
    }
}

// 1. 定义一个函数式接口
interface ILike{
    void lambda();
}

