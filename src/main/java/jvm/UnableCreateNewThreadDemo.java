package jvm;

/*
* linux系统下 默认一个进程不超过1024个线程
* */

import java.util.concurrent.TimeUnit;

public class UnableCreateNewThreadDemo {
    public static void main(String[] args) {
        for (int i = 0;  ; i++) {
            System.out.println("------------------------" + i);
            new Thread(() -> {
                try {
                    TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, ""+ i).start();
        }
    }
}
