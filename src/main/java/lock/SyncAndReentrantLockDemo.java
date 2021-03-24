package lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
* 多线程之间按顺序调用，实现A->B->C三个线程启动，要求如下:
* AA打印5次，BB打印10次，CC打印15次
* 紧接着
* AA打印5次，BB打印10次，CC打印15次
* ....
* 来10轮
*
* */

class ShareResource {
    private int number = 1; // A 1 B 2 C 3
    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    public void print5() throws InterruptedException {
        lock.lock();

        while (number != 1) {
            c1.await();
        }

        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + "\t " + number);
        }

        number = 2;
        c2.signal();

        lock.unlock();
    }

    public void print10() throws InterruptedException {
        lock.lock();

        while (number != 2) {
            c2.await();
        }

        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + "\t " + number);
        }

        number = 3;
        c3.signal();

        lock.unlock();
    }

    public void print15() throws InterruptedException {
        lock.lock();

        while (number != 3) {
            c3.await();
        }

        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + "\t " + number);
        }

        number = 1;
        c1.signal();
        lock.unlock();
    }

}

public class SyncAndReentrantLockDemo {
    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    shareResource.print5();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    shareResource.print10();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    shareResource.print15();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();
    }
}
