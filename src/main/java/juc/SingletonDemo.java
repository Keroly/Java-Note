package juc;

public class SingletonDemo {

    // 饿汉式
    // private static SingletonDemo instance = new SingletonDemo();
    private static volatile SingletonDemo instance = null; // 双重校验

    private SingletonDemo() {
        System.out.println(Thread.currentThread().getName() + "\t 构造方法");
    }

/*
    // 懒汉式
    public static SingletonDemo getInstance() {
        if (instance == null) {
            instance = new SingletonDemo();
        }
        return instance;
    }
*/
    // synchronized可以解决，但是没必要锁这么多代码，太重了，降低并发性
    // DCL (Double Check lOCK) 双重校验锁
    public static SingletonDemo getInstance() {
        if (instance == null) {
            synchronized (SingletonDemo.class) {
                if (instance == null) {
                    instance = new SingletonDemo();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
/*        // 单线程
        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
 */
        // 多线程
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                SingletonDemo.getInstance();
            }, String.valueOf(i)).start();
        }
    }
}
