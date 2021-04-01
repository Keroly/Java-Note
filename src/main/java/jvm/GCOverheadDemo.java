package jvm;
/*
* 超过98%的时间GC，但回收了不到2%的堆内存
* -Xms10m -Xmx10m -XX:+PrintGCDetails
*
* */

import java.util.ArrayList;
import java.util.List;

public class GCOverheadDemo {
    public static void main(String[] args) {

        int i = 0;
        List<String> list = new ArrayList<>();

        try {
            while (true) {
                list.add(String.valueOf(++i).intern());
            }
        }catch (Exception e) {
            System.out.println(i);
            e.printStackTrace();
            throw e;
        }

    }
}
