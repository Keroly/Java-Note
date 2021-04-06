package juc.ThreadLocal;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadID {
    private static final AtomicInteger nextId = new AtomicInteger(0);

    private static final ThreadLocal<Integer> threadID =
            new ThreadLocal<Integer>() {
                @Override
                protected Integer initialValue() {
                    return nextId.getAndIncrement();
                }
            };

    public static int get() {
        return threadID.get();
    }

    static class RunnableTask implements Runnable {
        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + ",分配的ID是： " + threadID.get());
                System.out.println(Thread.currentThread().getName() + ",分配的ID是： " + threadID.get());
            }finally {
                threadID.remove();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        RunnableTask task = new RunnableTask();
        Thread t1 = new Thread(task, "线程1");
        t1.start();
        TimeUnit.SECONDS.sleep(1);

        Thread t2 = new Thread(task, "线程2");
        t2.start();
        TimeUnit.SECONDS.sleep(1);

        Thread t3 = new Thread(task, "线程3");
        t3.start();
        TimeUnit.SECONDS.sleep(1);
    }
}
