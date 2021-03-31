package jvm;

import java.lang.ref.WeakReference;

/*
* 无论内存够不够，都回收
*
* */
public class WeakReferenceDemo {
    public static void main(String[] args) {
        Object o1 = new Object();
        WeakReference<Object> softReference = new WeakReference<>(o1);

        System.out.println(o1);
        System.out.println(softReference.get());

        o1 = null;
        System.gc();
        System.out.println("================");

        System.out.println(o1);
        System.out.println(softReference.get());
    }
}
