package jvm;

// 循环引用
public class RefCountGC {
    private byte[] bigsize = new byte[2 * 1024 * 1024]; // 这个属性是为了占用内存
    Object instance = null;

    public static void main(String[] args) {
        RefCountGC object1= new RefCountGC();
        RefCountGC object2= new RefCountGC();
        object1.instance = object2;
        object2.instance = object1;
        object1 = null;
        object2 = null;

        System.gc();    // 不是立刻启动
    }
}
