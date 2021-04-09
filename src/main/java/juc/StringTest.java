package juc;

public class StringTest {
    public static void main(String[] args) {
        String a = "a";
        String b = new String("a");
        System.out.println(a == b); // false

        System.out.println("--------------------------------------");

        String str1 = new String("hello");
        String str2 = str1.intern();    // 先是去常量池查找是否有匹配对象，没有则创建并返回，有则直接返回
        String str3 = "hello";
        System.out.println(str1 == str2);   // false
        System.out.println(str1 == str3);   // false
        System.out.println(str2 == str3);   // true

        String m = "a";
        String n = "b";
        String h = "ab";
        String x = "a" + "b";
        String y = m + n;
        String z = new String("ab");

        System.out.println(x == y); // false , y调用StringBuilder的toString()方法在堆中创建一个String对象
        System.out.println(x == z); // false, x 直接拼接放进常量池
        System.out.println(y == z); // false

        System.out.println(x == h); // true
        System.out.println(y == h); // false
        System.out.println(z == h); // false

    }
}
