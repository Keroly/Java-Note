package lock;

import java.util.concurrent.TimeUnit;

class HoldLockThread implements Runnable {

    private String A;
    private String B;

    public HoldLockThread(String a, String b) {
        A = a;
        B = b;
    }

    @Override
    public void run() {
        synchronized (A) {
            System.out.println(Thread.currentThread().getName() + "\t 自己持有：" + A + "\t 尝试获得：" + B);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (B) {
                System.out.println(Thread.currentThread().getName() + "\t 自己持有：" + B + "\t 尝试获得：" + A);
            }

        }
    }
}

/*
*   jps -l
*   jstack 1234
*
* */

public class DeadLockDemo {
    public static void main(String[] args) {
        String A = "lockA";
        String B = "lockB";

        new Thread(new HoldLockThread(A, B), "Thread1").start();
        new Thread(new HoldLockThread(B, A), "Thread1").start();

    }
}
