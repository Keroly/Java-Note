package jvm;

public class MyObject {
    public static void main(String[] args) {
        MyObject myObject = new MyObject();
        System.out.println(myObject.getClass().getClassLoader().getParent().getParent()); // java.
        System.out.println(myObject.getClass().getClassLoader().getParent());   // javax.
        System.out.println(myObject.getClass().getClassLoader());
        System.out.println("-------------------------------------------");

        Object object = new Object();
        System.out.println(object.getClass().getClassLoader());
    }
}
