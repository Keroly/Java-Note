package jvm;

/*
* -Xms10m -Xmx10m
*
* */

import java.util.Random;

public class OutOfMemoryErrorDemo {
    public static void main(String[] args) {
        String str = "aaaa";

        while(true) {
            str += str + new Random().nextInt(11111111) + new Random().nextInt(222222222);
            str.intern();
        }
    }
}
