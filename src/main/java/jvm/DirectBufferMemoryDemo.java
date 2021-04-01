package jvm;

/*
* -XX:MaxDirectMemorySize=5m
*
* */

import java.nio.ByteBuffer;

public class DirectBufferMemoryDemo {
    public static void main(String[] args) {

        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(10 * 1024 * 1024);

    }
}
