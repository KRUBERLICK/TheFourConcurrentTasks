package readers_writers_2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class BookStorage {

    private List<Integer> bookList;
    private int readCount;
    private int writeCount;

    private Semaphore readMutex;
    private Semaphore writeMutex;
    private Semaphore readTry;
    private Semaphore resource;

    public BookStorage() {
        bookList = new ArrayList<>();
        readCount = writeCount = 0;

        readMutex = new Semaphore(1);
        writeMutex = new Semaphore(1);
        readTry = new Semaphore(1);
        resource = new Semaphore(1);
    }

    public void read() {
        try {
            readTry.acquire();
            readMutex.acquire();
            readCount++;

            if (readCount == 1) {
                resource.acquire();
            }

            readMutex.release();
            readTry.release();

            System.out.println(Thread.currentThread().getName() + " is reading");

            Thread.sleep(800);

            readMutex.acquire();
            readCount--;
            if (readCount == 0) {
                resource.release();
            }

            readMutex.release();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void write(int value) {
        try {
            writeMutex.acquire();
            writeCount++;
            if (writeCount == 1) {
                readTry.acquire();
            }
            writeMutex.release();

            resource.acquire();

            System.out.println(Thread.currentThread().getName() + " is writing");

            bookList.add(value);

            Thread.sleep(500);

            resource.release();

            writeMutex.acquire();
            writeCount--;
            if (writeCount == 0) {
                readTry.release();
            }
            writeMutex.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
