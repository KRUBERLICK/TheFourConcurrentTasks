package producer_consumer;

import java.util.Random;

public class Consumer implements Runnable {

    private final int SLEEP_MIN = 200;
    private final int SLEEP_MAX = 400;
    private Random rand;
    private DataQueue queue;
    private Thread thread;

    public Consumer(DataQueue queue) {
        rand = new Random();
        this.queue = queue;
        thread = new Thread(this);
    }

    public void startThread() {
        thread.start();
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("Consumed " + queue.get());
            try {
                Thread.sleep(getRandTime());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private int getRandTime() {
        return SLEEP_MIN + rand.nextInt(SLEEP_MAX - SLEEP_MIN);
    }
}
