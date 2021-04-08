package juc.zerocopy;

import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SoftReferenceTest {
    public static void main(String[] args) throws InterruptedException {
        List<Reference> list = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {
            byte[] buffer = new byte[10 * 1024 * 1024];
            SoftReference softReference = new SoftReference(buffer);
            list.add(softReference);
        }

        System.gc();
        Thread.sleep(1000);

        Iterator<Reference> iterator = list.iterator();
        while (iterator.hasNext()) {
            Reference reference = iterator.next();
            System.out.println(reference.get());

        }
    }
}
