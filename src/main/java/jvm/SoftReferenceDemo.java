package jvm;

import java.lang.ref.SoftReference;
/*
* 调整虚拟机参数
* -Xms5m -Xmx5m -XX:+PrintGCDetails
*
* */

public class SoftReferenceDemo {

    public static void main(String[] args) {
        Object o1 = new Object();
        SoftReference<Object> softReference = new SoftReference<>(o1);

        o1 = null;

        System.out.println(o1);
        System.out.println(softReference.get());

        System.out.println("-----------------------");

        //
        try {
            byte[] bytes = new byte[30 * 1024 * 1024];
        }catch (Exception e) {

        }finally {
            System.out.println(o1);
            System.out.println(softReference.get());
        }

    }
}
