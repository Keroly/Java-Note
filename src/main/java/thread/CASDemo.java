package thread;

import java.util.concurrent.atomic.AtomicInteger;

/*
* CAS是什么？ => compare and swap(比较并交换)
*
*
*
* */
public class CASDemo {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);


        System.out.println(atomicInteger.compareAndSet(5, 10) + "\t" + atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(6, 15) + "\t" + atomicInteger.get());

        atomicInteger.getAndIncrement();
    }
}
