package readers_writers_1;

import com.sun.tools.internal.jxc.SchemaGenerator;

import java.util.Random;

public class Reader implements Runnable {

    private final int SLEEP_MIN = 1300;
    private final int SLEEP_MAX = 1400;
    private BookStorage storage;
    private Random rand;

    public Reader(BookStorage storage) {
        this.storage = storage;
        rand = new Random();
    }

    public void startThread(int number) {
        Thread thread = new Thread(this, "Reader" + number);

        thread.start();
    }

    @Override
    public void run() {
        //while (true) {
            storage.read();
            try {
                Thread.sleep(getRandTime());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        //}
    }

    private int getRandTime() {
        return SLEEP_MIN + rand.nextInt(SLEEP_MAX - SLEEP_MIN);
    }
}
