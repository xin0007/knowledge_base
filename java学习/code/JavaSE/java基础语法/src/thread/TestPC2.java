package thread;

public class TestPC2 {
    public static void main(String[] args) {
        Shoe shoe = new Shoe();
        new StinkyF(shoe).start();
        new Sniff(shoe).start();

    }
}


// produce
class StinkyF extends Thread{
    Shoe shoe;

    // 构造器构造，为了能传参
    public StinkyF(Shoe shoe) {
        this.shoe = shoe;
    }

    // produce

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("this is the "+i+"th shoe");
            shoe.stink(new Feet(i));
        }
    }
}

// consume
class Sniff extends Thread{

    Shoe shoe;

    public Sniff(Shoe shoe) {
        this.shoe = shoe;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("Consumed the "+shoe.sniff().id+"th shoe");
        }
    }
}

class Feet {
    int id;

    public Feet(int id) {
        this.id = id;
    }
}

class Shoe {
    // set how many feet can one shoe produce
    Feet[] feet = new Feet[10];

    // shoe counter
    int count = 0;

    // put feet in shoe
    // producer and consumer all use feet, so they need to be synchronized
    /*
    function:
    producer: 1. produce put in container 2. if full wait 3. ask consumer to consume
    consumer: 1. consume 2. wait for consume 3. notify producer to produce
     */

    public synchronized void stink (Feet foot){

        // if full wait
        if(count == feet.length){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // put in container
        feet[count] = foot;
        // produce
        count++;


        // ask consumer to consumer
        this.notifyAll();
    }

    public synchronized Feet sniff(){


        if(count == 0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // consume;
        count--;
        // take it from container
        Feet foot = feet[count];

        // notify producer to produce
        this.notifyAll();

        return foot;
    }
}