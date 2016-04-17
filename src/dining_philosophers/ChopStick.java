package dining_philosophers;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class ChopStick {

    private Semaphore sem;
    private Random rand;
    private final int SLEEP_MIN = 500;
    private final int SLEEP_MAX = 700;

    public ChopStick() {
        sem = new Semaphore(1);
        rand = new Random();
    }

    public boolean pickUp() {
        try {
            return sem.tryAcquire(1, getRandomTime(), TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            return false;
        }
    }

    public void putDown() {
        sem.release();
    }

    private int getRandomTime() {
        return SLEEP_MIN + rand.nextInt(SLEEP_MAX - SLEEP_MIN);
    }
}
