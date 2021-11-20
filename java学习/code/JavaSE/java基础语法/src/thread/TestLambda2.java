package thread;


public class TestLambda2 {


    public static void main(String[] args) {

        ILove iLove = (tt,ss)->{
            System.out.println(tt+ss);
        };

        iLove.iLove("aaa", "bbb");

    }

}

interface ILove{
    void iLove(String aaa, String bbb);
}



