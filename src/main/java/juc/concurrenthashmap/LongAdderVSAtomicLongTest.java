package juc.concurrenthashmap;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

public class LongAdderVSAtomicLongTest {
    private static void testLongAdder(final int threadCount, final int times) throws InterruptedException {
        LongAdder longAdder = new LongAdder();
        List<Thread> list = new ArrayList<>();
        for (int i = 0; i < threadCount; i++) {
            list.add(new Thread(() -> {
                for (int j = 0; j < times; j++) {
                    longAdder.increment();
                }
            }));
        }
        for (Thread thread: list) {
            thread.start();
        }

        for (Thread thread: list) {
            thread.join();
        }
    }

    private static void testAtomicLong(final int threadCount, final int times) throws InterruptedException {
        AtomicLong atomicLong = new AtomicLong();
        List<Thread> list = new ArrayList<>();
        for (int i = 0; i < threadCount; i++) {
            list.add(new Thread(() -> {
                for (int j = 0; j < times; j++) {
                    atomicLong.incrementAndGet();
                }
            }));
        }
        for (Thread thread: list) {
            thread.start();
        }

        for (Thread thread: list) {
            thread.join();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        testAtomicLongVSLongAdder(1);
        testAtomicLongVSLongAdder(10);
        testAtomicLongVSLongAdder(20);
        testAtomicLongVSLongAdder(40);
        testAtomicLongVSLongAdder(80);
    }

    private static void testAtomicLongVSLongAdder(final int threadCount) throws InterruptedException {
        System.out.println("threadCount: " + threadCount + ",times: " + 10000000);
        long start = System.currentTimeMillis();
        testLongAdder(threadCount, 10000000);
        System.out.println("LongAdder elapse: " + (System.currentTimeMillis() - start) + "ms");

        long start2 = System.currentTimeMillis();
        testAtomicLong(threadCount, 10000000);
        System.out.println("LongAdder elapse: " + (System.currentTimeMillis() - start2) + "ms");
    }

}
