package jvm;

/*
*   -Xms10m -Xmx10m -XX:+PrintGCDetails
*
* */

import java.util.Random;

public class JVMNote {

    public static void main(String[] args) {

//        long maxMemory = Runtime.getRuntime().maxMemory();
//        long totalMemory = Runtime.getRuntime().totalMemory();
//        System.out.println("-Xms:" + (totalMemory / (double)1024 / 1024) + "MB");
//        System.out.println("-Xmx:" + (maxMemory / (double)1024 / 1024) + "MB");

//        byte[] bytes = new byte[40 * 4024 * 1024];

        String str = "aaaaaaa";
        while (true) {
            str += str + new Random().nextInt(88888888) + new Random().nextInt(999999999);
        }


    }
}
