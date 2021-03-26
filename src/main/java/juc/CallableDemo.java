package juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class MyThread1 extends Thread {
    @Override
    public void run() {

    }
}

class MyThread2 implements Runnable {
    @Override
    public void run() {

    }
}

class MyThread3 implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("Callable");
        return 1024;
    }
}

/*
*
*
* */


public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // FutureTask(Callable<V> callable)
        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread3());
        FutureTask<Integer> futureTask2 = new FutureTask<>(new MyThread3());

        new Thread(futureTask, "t1").start();
        new Thread(futureTask2, "t2").start();

        while (!futureTask.isDone()) {

        }



        System.out.println(futureTask.get()); // 要求获得Callable结果，如果还没结果，则会阻塞，所以一般放在最后。
    }
}
