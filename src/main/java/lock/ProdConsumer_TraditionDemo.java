package lock;

/*
*   一个初始值为零的变量，两个线程对其交替操作，一个加 1一个减 1， 五轮
*
*   1. 线程 操作（方法） 资源类
*   2. 判断 干活 通知
*   3. 防止虚假唤醒机制
*
* */

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareData { // 资源类
    private int num = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment() throws Exception{
        lock.lock();

        // 1 判断
        while (num != 0) {
            // 等待， 不能生产
            condition.await();
        }
        // 2 干活
        num++;
        System.out.println(Thread.currentThread().getName() + "\t" + num);
        condition.signalAll();

        lock.unlock();
    }

    public void decrement() throws Exception{
        lock.lock();

        // 1 判断
        while (num == 0) {
            // 等待， 不能消费
            condition.await();
        }
        // 2 干活
        num--;
        System.out.println(Thread.currentThread().getName() + "\t" + num);
        condition.signalAll();

        lock.unlock();
    }
}

public class ProdConsumer_TraditionDemo {
    public static void main(String[] args) {
        ShareData shareData = new ShareData();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    shareData.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "T1").start();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    shareData.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "T2").start();
    }
}
