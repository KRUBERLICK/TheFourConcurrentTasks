package dining_philosophers;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Philosopher implements Runnable {

    private ChopStick ch1;
    private ChopStick ch2;

    private Thread thread;

    private final int SLEEP_MIN = 400;
    private final int SLEEP_MAX = 800;
    private Random rand;

    public Philosopher(int number, ChopStick ch1, ChopStick ch2) {
        this.ch1 = ch1;
        this.ch2 = ch2;
        rand = new Random();
        thread = new Thread(this, "Philosopher " + number);
    }

    public void startThread() {
        thread.start();
    }

    @Override
    public void run() {
        while (true) {
            System.out.println(Thread.currentThread().getName() + " is thinking");
            try {
                Thread.sleep(getRandTime());
                doEat();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void doEat() {
        if (ch1.pickUp()) {
            if (ch2.pickUp()) {
                System.out.println(Thread.currentThread().getName() + " is eating");
                try {
                    Thread.sleep(getRandTime());
                    ch1.putDown();
                    ch2.putDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                ch1.putDown();
                doEat();
            }
        } else {
            doEat();
        }
    }

    private int getRandTime() {
        return SLEEP_MIN + rand.nextInt(SLEEP_MAX - SLEEP_MIN);
    }
}
