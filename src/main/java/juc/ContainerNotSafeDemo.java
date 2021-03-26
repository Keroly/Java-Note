package juc;

/*
* 集合类 不安全的问题
*   ArrayList   // add方法没有加锁
*   HashSet     //
*   HashMap     //
* */

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class ContainerNotSafeDemo {
    public static void main(String[] args) {
        Map<String, String> map = new ConcurrentHashMap<>();
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0,8));
                System.out.println(map);
            }, String.valueOf(i)).start();
        }
    }

    private static void setNotSafe() {
        Set<String> set = new CopyOnWriteArraySet<>();
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(set);
            }, String.valueOf(i)).start();
        }
    }

    private static void listNotSafe() {
        // List<String> list = new ArrayList<>();

        // List<String> list = Collections.synchronizedList(new ArrayList<>());

        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }

        /*
        * 1. 故障现象
        *   java.util.ConcurrentModificationException
        * 2. 导致原因
        *   并发争抢修改导致，参考花名册签字场景
        * 3. 解决方案
        *   3.1 new Vector<>();  降低并发
        *   3.2 Collections.synchronizedList(new ArrayList<>());
        *   3.3 new CopyOnWriteArrayList<>();
        * 4. 优化建议
        *
        * */
    }
}
