package juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class LockSupportDemo {

    static Object objectLock = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread a = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t come in");
            LockSupport.park(); // 被阻塞。。等待通知放行，需要许可证
            System.out.println(Thread.currentThread().getName() + "\t 被唤醒");
        }, "A");
        a.start();

        TimeUnit.SECONDS.sleep(3);

        new Thread(() -> {
            LockSupport.unpark(a);
            System.out.println(Thread.currentThread().getName() + "\t 发出通知了");
        }, "B").start();

    }
}
