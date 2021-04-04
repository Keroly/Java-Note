package juc.threadpool;

import java.util.concurrent.*;

public class MyThreadPool {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        // executorService.submit();
        FutureTask futureTask = new FutureTask(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                return null;
            }
        });

        // submit可以接受Callable和Runnable
        Future future = executorService.submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                return null;
            }
        });


    }
}
