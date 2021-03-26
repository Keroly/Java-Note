package lock;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class MyResource {
    private volatile boolean Flag = true; // 默认开启，进行生产+消费
    private AtomicInteger atomicInteger = new AtomicInteger();

    BlockingQueue<String> blockingQueue = null;
    public MyResource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }

    public void myProd() throws InterruptedException {
        String data = null;
        while (Flag) {
            data = atomicInteger.incrementAndGet() + "";
            boolean retValue = blockingQueue.offer(data, 2L, TimeUnit.SECONDS);

            if (retValue) {
                System.out.println(Thread.currentThread().getName()+ "\t 插入队列" + data + "成功");
            }else {
                System.out.println(Thread.currentThread().getName()+ "\t 插入队列" + data + "失败");
            }
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(Thread.currentThread().getName()+ "\t 停止生产");
    }

    public void myConsumer() throws Exception {
        String data = null;
        while (Flag) {
            data = blockingQueue.poll(2L, TimeUnit.SECONDS);
            if (null == data || data.equalsIgnoreCase("")) {
                Flag = false;
                System.out.println(Thread.currentThread().getName()+ "\t 超过2秒没有取到商品，消费结束");
                return;
            }
            System.out.println(Thread.currentThread().getName()+ "\t 取出" + data + "成功");
        }
    }

    public void stop() {
        Flag = false;
        System.out.println("叫停生产");
    }

}

/*
*
*
* */

public class ProdConsumer_BlockQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        MyResource myResource = new MyResource(new ArrayBlockingQueue<>(10));

        new Thread(() -> {
            try {
                myResource.myProd();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Prod").start();

        new Thread(() -> {
            try {
                myResource.myConsumer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "Consumer").start();

        TimeUnit.SECONDS.sleep(5);
        System.out.println();

        myResource.stop();
    }
}
