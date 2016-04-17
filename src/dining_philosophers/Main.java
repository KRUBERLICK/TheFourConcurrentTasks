package dining_philosophers;

public class Main {

    public static void main(String[] args) {
        ChopStick[] chopSticks = new ChopStick[5];
        Philosopher[] philosophers = new Philosopher[5];

        for (int i = 0; i < chopSticks.length; i++) {
            chopSticks[i] = new ChopStick();
        }

        philosophers[0] = new Philosopher(1, chopSticks[0], chopSticks[1]);
        philosophers[1] = new Philosopher(2, chopSticks[1], chopSticks[2]);
        philosophers[2] = new Philosopher(3, chopSticks[2], chopSticks[3]);
        philosophers[3] = new Philosopher(4, chopSticks[3], chopSticks[4]);
        philosophers[4] = new Philosopher(5, chopSticks[4], chopSticks[0]);

        for (Philosopher el: philosophers) {
            el.startThread();
        }
    }
}
