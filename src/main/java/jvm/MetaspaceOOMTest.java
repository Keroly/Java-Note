package jvm;

/*
* -XX:MetaspaceSize=8m -XX:MaxMetaspaceSize=8m
 *
* */

public class MetaspaceOOMTest {

    static class OOMTest{

    }

    public static void main(String[] args) {
        int i = 0; // 计数多少次后发生

        try {
            while (true) {
                i++;
                // 生成很多静态类
            }
        }catch (Exception e) {
            System.out.println("**********************" + i);
            e.printStackTrace();
        }
    }
}
