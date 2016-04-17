package producer_consumer;

import java.util.Random;

public class Producer implements Runnable {

    private final int SLEEP_MIN = 500;
    private final int SLEEP_MAX = 700;
    private Random rand;
    private DataQueue queue;
    private Thread thread;

    public Producer(DataQueue queue) {
        this.queue = queue;
        rand = new Random();
        thread = new Thread(this);
    }

    public void startThread() {
        thread.start();
    }

    @Override
    public void run() {
        int i = 1;
        while(true) {
            queue.put(i);
            System.out.println("Produced " + i++);
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
