package juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class AQSDemo {

    public static void main(String[] args) {

        ReentrantLock lock = new ReentrantLock();

        // A 顾客是第一个顾客，受理窗口没人，可直接办理
        new Thread(() -> {
            lock.lock();
            System.out.println("---A come in");
            try {
                TimeUnit.SECONDS.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.unlock();
        }, "A").start();

        // B 顾客是第二个顾客，受理窗口只有一个（只有一个线程池持有锁），B只能等待
        new Thread(() -> {
            lock.lock();
            System.out.println("---B come in");

            lock.unlock();
        }, "B").start();

        // C 顾客是第三个顾客，受理窗口只有一个（只有一个线程池持有锁），C只能等待
        new Thread(() -> {
            lock.lock();
            System.out.println("---B come in");

            lock.unlock();
        }, "C").start();
    }

}
