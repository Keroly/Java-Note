package jvm;
/*
* GC Roots的对象们有：
* 1. 虚拟机栈（栈帧中的本地变量表）中引用的对象
* 2. 方法区中的类静态属性引用的对象
* 3. 方法去中常量引用的对象
* 4. 本地方栈中JNI(Native方法)中引用的对象
*
* */


public class GCRootDemo {

    private byte[] bytes = new byte[100 * 1024 * 1024];

    // 2. private static GCRootDemo2 t2;
    // 3. private static final GCRootDemo3 t3 = new GCRootDemo3();

    public static void m1() {
        // 1.
        GCRootDemo t1 = new GCRootDemo();
        System.gc();
        System.out.println("第一次GC");
    }


    public static void main(String[] args) {
        m1();
    }
}
