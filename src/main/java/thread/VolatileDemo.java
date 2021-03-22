package thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class MyData {
    // 加了 volatile也不保证原子性
    volatile int number = 0;

    public void add() {
        this.number = 60;
    }

    // 想要原子性可以加 synchronized
    public void addPlus() {
        number++;
    }

    // 或者 AtomicInteger
    AtomicInteger atomicNumber = new AtomicInteger();
    public void addAtomic() {
        atomicNumber.getAndIncrement();
    }

}

/*
1. 验证 volatile的可见性
    1.1 假如 number变量之前没有 volatile关键字修饰, 修改对主线程不可见
    1.2 若有 volatile修饰，体现了可见性
2. 验证 volatile的原子性
    2.1 不可分割，完整性
    2.2 volatile不保证原子性
    2.3 解决原子性方法： synchronized 或者 AtomicInteger
 */
public class VolatileDemo {
    public static void main(String[] args) {
        MyData myData = new MyData();
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    myData.addPlus();
                    myData.addAtomic();
                }
            }, String.valueOf(i)).start();
        }

        while (Thread.activeCount() > 2) { // 一个 main线程  一个 gc进程
            Thread.yield();
        }

        System.out.println(Thread.currentThread().getName() + "\t finally :" + myData.number);
        System.out.println(Thread.currentThread().getName() + "\t atomic finally :" + myData.atomicNumber);
    }





    // volatile可以保证可见性
    private static void seeOkByVolatile() {
        MyData myData = new MyData();
        new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t come in");
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                myData.add();
                System.out.println(Thread.currentThread().getName() + "\t updated");
            },"A").start();

        // 在A线程结束之前，main线程把 number拷贝到自己的线程空间，除非在这里睡一会，不然只能加 volatile
        while (myData.number == 0) {
            // 等待 number被修改
        }
        System.out.println(Thread.currentThread().getName() + "\t success");
    }
}
