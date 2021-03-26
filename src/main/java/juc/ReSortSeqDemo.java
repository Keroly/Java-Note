package juc;

public class ReSortSeqDemo {
    // 应加 volatile
    int a = 0;
    boolean flag = true;

    public void method01() {
        a = 1;  // 语句 1
        flag = true;    // 语句 2
    }

    // 多线程环境中线程交替执行，由于编译器优化重排的存在
    // 两个线程中使用的变量能否保证一致性是无法确定的，也无法预测
    public void method2() {
        if (flag) {
            a += 5; // 语句 3
            System.out.println(a);  //  5 或 6
        }
    }
}
