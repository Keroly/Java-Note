package lock;

/*
* ArrayBlockingQueue :是一个基于数组结构的有界阻塞队列。按 FIFO原则对元素进行排序
* LinkedBlockingQueue : 是一个基于链表结构的有界阻塞队列。按 FIFO原则对元素进行排序。吞吐量通常高于 ArrayBlockingQueue
* SynchronousQueue : 一个不存储元素的阻塞队列。 每个插入操作必须等到另一个线程调用移除操作，否则插入操作一直处于阻塞状态，吞吐量通常
*
* 1. 队列
*
* 2. 阻塞队列
*   2.1 阻塞队列有没有好的一面
*
*   2.2 不得不阻塞，你如何管理
*
* */

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.offer("a", 2, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("a", 2, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("a", 2, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("a", 2, TimeUnit.SECONDS));
    }
}
