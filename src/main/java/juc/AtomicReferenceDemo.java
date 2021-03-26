package juc;

import java.util.concurrent.atomic.AtomicReference;

class User {
    String username;
    int age;
    public User(String username, int age) {
        this.username = username;
        this.age = age;
    }

}

// 理解原子引用
public class AtomicReferenceDemo {
    public static void main(String[] args) {
        User z3 = new User("z3", 23);
        User l4 = new User("l4", 24);

        AtomicReference<User> atomicReference = new AtomicReference<>();
        atomicReference.set(z3);

        System.out.println(atomicReference.compareAndSet(z3, l4) + "\t" + atomicReference.get().age);
        System.out.println(atomicReference.compareAndSet(z3, l4) + "\t" + atomicReference.get().age);

    }
}
