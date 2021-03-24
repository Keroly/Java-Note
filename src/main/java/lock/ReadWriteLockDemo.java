package lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyCache {
    private volatile Map<String, Object> map = new HashMap<>();
    private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

    public void put(String key, Object value) {
        rwLock.writeLock().lock();

        try {
            System.out.println(Thread.currentThread().getName() + "\t 正在写入" + key);
            TimeUnit.MILLISECONDS.sleep(300);
            map.put(key, value);
            System.out.println(Thread.currentThread().getName()+ "\t 写入完成");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            rwLock.writeLock().unlock();
        }

    }

    public void get(String key) {
        rwLock.readLock().lock();

        try {
            System.out.println(Thread.currentThread().getName() + "\t 正在读取" + key);
            TimeUnit.MILLISECONDS.sleep(300);
            System.out.println(Thread.currentThread().getName()+ "\t 读取完成：" + map.get(key));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            rwLock.readLock().unlock();
        }

    }
}

/*
* 多个线程同时读一个资源类没有任何问题，所以为了满足并发量，读取共享资源应该同时进行
* 但是，如果有一个线程想去写共享资源，就不应该再有其他线程可以对该资源进行读写
*
* 读 - 读 能共存
* 读 - 写 不能共存
* 写 - 写 不能共存
*
* 写操作：原子 + 独占， 整个过程必须是一个完整的统一体，中间不许被分割，被打断
* */

public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        for (int i = 0; i < 5; i++) {
            final int temp = i;
            new Thread(() -> {
                myCache.put(temp + "", temp + "");
            }, String.valueOf(i)).start();
        }

        for (int i = 0; i < 5; i++) {
            final int temp = i;
            new Thread(() -> {
                myCache.get(temp + "");
            }, String.valueOf(i)).start();
        }
    }
}
