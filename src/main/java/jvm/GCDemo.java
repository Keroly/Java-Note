package jvm;

/*
*  1. 2. 3. 都是复制 + 标记整理
* 1. SerialGC + SerialOldGC
* -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseSerialGC   (DefNew + Tenured)
* 2. ParNewGC + SerialOldGC (不推荐)
* -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParNewGC   (ParNew + Tenured)
* 3. ParallelGC + ParallelOldGC (jdk1.8)
* -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParallelGC  (PSYoungGen + ParOldGen)
*
*   复制 + 标记清除 + 标记整理
* 4. ParNewGC + ConcMarkSweepGC + SerialOldGC
* -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseConcMarkSweepGC    (ParNew + concurrent mark-sweep)
*
* 5.
* -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseG1GC
*
* */

import java.util.Random;

public class GCDemo {
    public static void main(String[] args) {
        String str = "aaaa";

        try {
            while(true) {
                str += str + new Random().nextInt(11111111) + new Random().nextInt(222222222);
                str.intern();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
