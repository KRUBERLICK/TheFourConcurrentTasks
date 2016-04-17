package producer_consumer;

public class Main {

    public static void main(String[] args) {
        DataQueue queue = new DataQueue();
        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);

        producer.startThread();
        consumer.startThread();
    }
}
