package producer_consumer;

import queue.IQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DataQueue implements IQueue {

    private List<Integer> dataList;
    private Lock lock;
    private Condition cond;

    public DataQueue() {
        dataList = new ArrayList<>();
        lock = new ReentrantLock();
        cond = lock.newCondition();
    }

    public void put(int value) {
        lock.lock();
        dataList.add(value);
        cond.signal();
        lock.unlock();
    }

    public int get() {
        lock.lock();

        while (dataList.size() < 1) {
            try {
                cond.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        int result = dataList.get(0);

        dataList.remove(0);

        lock.unlock();

        return result;

    }
}
