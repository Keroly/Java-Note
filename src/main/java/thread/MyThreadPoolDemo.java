package thread;

import java.util.concurrent.*;

public class MyThreadPoolDemo {
    public static void main(String[] args) throws InterruptedException {    // 自定义线程池
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                5,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy()); // 不同的拒绝策略
        for (int i = 0; i < 10; i++) {
            threadPool.execute(() -> {
                System.out.println(Thread.currentThread().getName() + "\t 办理业务");
            });
            // TimeUnit.SECONDS.sleep(1);
        }
        threadPool.shutdown();
    }

    private static void threadPoolInit() {
        // ExecutorService threadPool = Executors.newFixedThreadPool(5);  // 一池 5个处理线程
        // ExecutorService threadPool = Executors.newSingleThreadExecutor();   // 一池 1个线程
        ExecutorService threadPool = Executors.newCachedThreadPool();   // 一池 N个线程
        // 模拟十个任务来请求
        for (int i = 0; i < 10; i++) {
            threadPool.execute(() -> {
                System.out.println(Thread.currentThread().getName() + "\t 办理业务");
            });

        }
        threadPool.shutdown();
    }
}
