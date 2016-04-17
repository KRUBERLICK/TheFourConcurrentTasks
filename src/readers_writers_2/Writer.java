package readers_writers_2;

import java.util.Random;

public class Writer implements Runnable {

    private BookStorage storage;
    private final int SLEEP_MIN = 500;
    private final int SLEEP_MAX = 700;
    private Random rand;

    public Writer(BookStorage storage) {
        this.storage = storage;
        rand = new Random();
    }

    public void startThread(int number) {
        Thread thread = new Thread(this, "Writer " + number);

        thread.start();
    }

    @Override
    public void run() {
        int i = 1;
        //while (true) {
        storage.write(i++);
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
