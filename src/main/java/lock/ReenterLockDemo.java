package lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Phone implements Runnable{
    public synchronized void sendMS() throws Exception {
        System.out.println(Thread.currentThread().getName() + "\t send MS");
        sendML();
    }
    public synchronized void sendML() throws Exception {
        System.out.println(Thread.currentThread().getName() + "\t send ML");
    }

    //==========================================================================================

    Lock lock = new ReentrantLock();

    @Override
    public void run() {
        get();
    }

    public void get() {
        lock.lock();
        lock.lock();
        System.out.println(Thread.currentThread().getName() + "\t get()");
        set();
        lock.unlock();
        lock.unlock();
    }

    private void set() {
        lock.lock();
        System.out.println(Thread.currentThread().getName() + "\t set()");
        lock.unlock();
    }

}

public class ReenterLockDemo {
    public static void main(String[] args) throws InterruptedException {
        Phone phone = new Phone();
        new Thread(() -> {
            try {
                phone.sendMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "T1").start();

        new Thread(() -> {
            try {
                phone.sendMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "T2").start();

        TimeUnit.SECONDS.sleep(1);

        Thread T3 = new Thread(phone, "t3");
        Thread T4 = new Thread(phone, "t4");

        T3.start();
        T4.start();
    }
}
