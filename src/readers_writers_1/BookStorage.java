package readers_writers_1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class BookStorage {

    private List<Integer> bookList;
    private Semaphore readerSem;
    private Semaphore writerSem;
    private int readCount;

    public BookStorage() {
        bookList = new ArrayList<>();
        readerSem = new Semaphore(1);
        writerSem = new Semaphore(1);
        readCount = 0;
    }

    public void write(int value) {
        try {
            writerSem.acquire();
            System.out.println(Thread.currentThread().getName() + " is writing");
            bookList.add(value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            writerSem.release();
        }
    }

    public void read() {
        try {
            readerSem.acquire();
            readCount++;
            if (readCount == 1) {
                writerSem.acquire();
            }
            readerSem.release();

            System.out.println(Thread.currentThread().getName() + " is reading");

            readerSem.acquire();
            readCount--;
            if (readCount == 0) {
                writerSem.release();
            }
            readerSem.release();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
