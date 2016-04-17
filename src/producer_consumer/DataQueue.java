package producer_consumer;

import queue.IQueue;

import java.util.ArrayList;
import java.util.List;

public class DataQueue implements IQueue {

    private List<Integer> dataList;

    public DataQueue() {
        dataList = new ArrayList<>();
    }

    public void put(int value) {
        dataList.add(value);
    }

    public int get() {
        int result = dataList.get(0);

        dataList.remove(0);

        return result;
    }
}
