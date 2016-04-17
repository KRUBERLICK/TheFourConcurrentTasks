package readers_writers_2;

import java.util.Random;

public class Generator implements Runnable {

    private BookStorage storage;
    private final int SLEEP_MIN = 900;
    private final int SLEEP_MAX = 1200;
    private Random rand;
    private Thread thread;

    public Generator() {
        storage = new BookStorage();
        rand = new Random();
    }

    public void startGeneraton() {
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        int i = 1;
        while (true) {
            Writer writer = new Writer(storage);
            Reader reader = new Reader(storage);

            writer.startThread(i);
            reader.startThread(i++);

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
